package com.patrones;

import com.patrones.factory.Factory;
import com.patrones.factory.FactoryImpl;
import com.patrones.servicios.ServicioConsultaLibros;

public class PrincipalCrud {

	public static void main(String[] args) throws Exception {

		Factory fact = new FactoryImpl();
		fact.init("com.patrones");
		
//		//--
//		ServicioLibros servicioLibros = fact.create("servicioLibros");
//		ServicioAutor servicioAutores = fact.create("servicioAutores");
//		
//		//--libros
//		System.out.println("---------------------");
//		System.out.println("Libros");
//
//		servicioLibros.listarTodos()
//			.stream()
//			.forEach(System.out::println);
//		
//		//--autores
//		System.out.println("---------------------");
//		System.out.println("Autores");
//		servicioAutores.listarTodos()
//			.stream()
//			.forEach(System.out::println);
		
		//---------
		//--Lista de libros con el nombre de autor
//		var libros = servicioLibros.listarTodos();
//		
//		var newLibros = new ArrayList<LibroDto>();
//		for(var lib:libros) {
//			var autor = servicioAutores.buscarPorId(lib.autorId());
//			
//			LibroDto dto = LibroDto.builder()
//					.id(lib.id())
//					.titulo(lib.titulo())
//					.isbn(lib.isbn())
//					.autorId(lib.autorId())
//					.autorNombre(autor.getNombre())
//				.build();
//					
//			newLibros.add(dto);
//		}
		
		ServicioConsultaLibros servicioConsultaLibros = fact.create("servicioConsultaLibros");

		var libros = servicioConsultaLibros.listarLibros();
		
		libros.forEach(System.out::println);
		
	}

}
