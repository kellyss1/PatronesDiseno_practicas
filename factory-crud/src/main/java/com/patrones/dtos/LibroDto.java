package com.patrones.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class LibroDto {
	private Integer id;
	private String titulo;
	private String isbn;
	private Integer autorId;
	private String autorNombre;
}
