package com.patrones.servicios;

import java.util.ArrayList;
import java.util.List;

import com.patrones.dtos.LibroDto;
import com.patrones.factory.Factory;
import com.patrones.factory.Inicializar;
import com.patrones.factory.anotaciones.MiComponente;
import com.patrones.factory.anotaciones.MiLogger;

@MiComponente(name="servicioConsultaLibros", singleton = false)
@MiLogger(unidadTiempo="segundos")
public class ServicioConsultaLibrosImpl implements ServicioConsultaLibros, Inicializar {
	private ServicioLibros servicioLibros;
	private ServicioAutor servicioAutores;
	
	public void onInit(Factory factory) {	
		this.servicioLibros = factory.create("servicioLibros");
		this.servicioAutores = factory.create("servicioAutores");
	}
	
	public List<LibroDto> listarLibros() {
		var libros = servicioLibros.listarTodos();
		
		var newLibros = new ArrayList<LibroDto>();
		
		for(var lib:libros) {
			var autor = servicioAutores.buscarPorId(lib.autorId());
			
			LibroDto dto = LibroDto.builder()
					.id(lib.id())
					.titulo(lib.titulo())
					.isbn(lib.isbn())
					.autorId(lib.autorId())
					.autorNombre(autor.getNombre())
				.build();
					
			newLibros.add(dto);
		}
		
		return newLibros;		
	}
}
