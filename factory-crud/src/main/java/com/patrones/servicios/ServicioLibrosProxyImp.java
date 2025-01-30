package com.patrones.servicios;

import java.util.List;

import com.patrones.db.LibroRec;
import com.patrones.factory.Factory;
import com.patrones.factory.Inicializar;

//@MiComponente(name = "servicioLibros", singleton = true)
public class ServicioLibrosProxyImp implements ServicioLibros, Inicializar {
	private ServicioLibrosImpl servicioOriginal = new ServicioLibrosImpl();
	
	@Override
	public void onInit(Factory factory) {
		servicioOriginal.dbConfig = factory.create("dbConfig");
	}
	
	@Override
	public void crear(LibroRec obj) {
		servicioOriginal.crear(obj);
	}

	@Override
	public LibroRec buscarPorId(Integer id) {
		return servicioOriginal.buscarPorId(id);
	}

	@Override
	public List<LibroRec> listarTodos() {
		
		System.out.println("Listando libros");
		
		var ret = servicioOriginal.listarTodos();
		
		System.out.println("fin - Listando libros");
		
		return ret;
	}
}
