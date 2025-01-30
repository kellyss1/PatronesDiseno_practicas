package com.patrones.servicios;

import java.util.ArrayList;
import java.util.List;

import com.patrones.DbConfig;
import com.patrones.db.Autor;
import com.patrones.factory.Factory;
import com.patrones.factory.Inicializar;
import com.patrones.factory.anotaciones.MiComponente;
import com.patrones.factory.anotaciones.MiLogger;

@MiComponente(name="servicioAutores", singleton = true)
@MiLogger(unidadTiempo="microsegundos")
public class ServicioAutorImpl implements ServicioAutor, Inicializar {
	
	private DbConfig dbConfig;

	public void onInit(Factory factory) {
		this.dbConfig = factory.create("dbConfig");
	}

	@Override
	public Autor buscarPorId(Integer id) {
		try( var con = dbConfig.getConnection() ) {
			var pstmt = con.prepareStatement("select * from autor where id=?");
			
			pstmt.setInt(1, id);
			
			var rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return Autor.builder()
						.id( rs.getInt("id") )
						.nombre(rs.getString("nombre"))
					.build();
			}

			return null;
		} 
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public List<Autor> listarTodos() {
		try( var con = dbConfig.getConnection() ) {
			var pstmt = con.prepareStatement("select * from autor order by id");
			
			var rs = pstmt.executeQuery();
			
			var ret = new ArrayList<Autor>();
			
			while(rs.next()) {
				var libro = Autor.builder()
						 .id( rs.getInt("id") )
						.nombre( rs.getString("nombre") )
					.build();
				
				ret.add(libro);
			}

			return ret;
		} 
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	
	@Override
	public void crear(Autor obj) {
		try( var con = dbConfig.getConnection() ) {
			var pstmt = con.prepareStatement("insert into autor(id,nombre) values(?,?)");
			
			pstmt.setInt(1, obj.getId());
			pstmt.setString(2, obj.getNombre());
			
			pstmt.executeUpdate();
			
		} 
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
