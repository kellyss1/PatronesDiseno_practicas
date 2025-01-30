package com.patrones.servicios;

import java.util.List;

import com.patrones.db.Autor;

public interface ServicioAutor {
	public void crear(Autor obj);			//C
	public Autor buscarPorId(Integer id);   //R
	public List<Autor> listarTodos();

}
