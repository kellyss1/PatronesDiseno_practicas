package com.patrones.servicios;

import java.util.List;

import com.patrones.db.LibroRec;

public interface ServicioLibros {
//	//--CRUD
//	
	public void crear(LibroRec obj); 			//C
//
	public LibroRec buscarPorId(Integer id);   //R
	public List<LibroRec> listarTodos();		//R
//	
//	public void actualizar(Libro obj); 		//U
//	
//	public void eliminarPorId(Integer id); 	//D
	
}
