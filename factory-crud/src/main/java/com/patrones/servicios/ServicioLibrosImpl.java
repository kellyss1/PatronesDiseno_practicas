package com.patrones.servicios;

import java.util.ArrayList;
import java.util.List;

import com.patrones.DbConfig;
import com.patrones.db.LibroRec;
import com.patrones.factory.Factory;
import com.patrones.factory.Inicializar;
import com.patrones.factory.anotaciones.MiComponente;
import com.patrones.factory.anotaciones.MiLogger;

@MiComponente(name = "servicioLibros", singleton = true)
@MiLogger(unidadTiempo="milisegundos")
public class ServicioLibrosImpl implements ServicioLibros, Inicializar {

	public DbConfig dbConfig;

	@Override
	public void onInit(Factory factory) {
		this.dbConfig = factory.create("dbConfig");
	}
	
	@Override
	public LibroRec buscarPorId(Integer id) {
		try( var con = dbConfig.getConnection() ) {
			var pstmt = con.prepareStatement("select * from libros where id=?");
			
			pstmt.setInt(1, id);
			
			var rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return LibroRec.builder()
						.id( rs.getInt("id") )
						.titulo( rs.getString("titulo") )
						.isbn( rs.getString("isbn") )
						.autorId(rs.getInt("autor_id"))
					.build();
			}

			return null;
		} 
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public List<LibroRec> listarTodos() {
		try( var con = dbConfig.getConnection() ) {
			var pstmt = con.prepareStatement("select * from libros order by id");
			
			var rs = pstmt.executeQuery();
			
			var ret = new ArrayList<LibroRec>();
			
			while(rs.next()) {
				var libro = LibroRec.builder()
						.id( rs.getInt("id") )
						.titulo( rs.getString("titulo") )
						.isbn( rs.getString("isbn") )
						.autorId(rs.getInt("autor_id"))
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
	public void crear(LibroRec obj) {
		try( var con = dbConfig.getConnection() ) {
			var pstmt = con.prepareStatement("insert into libros(id,titulo,isbn,autor_id) values(?,?,?,?,?)");
			
			pstmt.setInt(1, obj.id());
			pstmt.setString(2, obj.titulo());
			pstmt.setString(3, obj.isbn());
			pstmt.setInt(3, obj.autorId());
			
			pstmt.executeUpdate();
			
		} 
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
