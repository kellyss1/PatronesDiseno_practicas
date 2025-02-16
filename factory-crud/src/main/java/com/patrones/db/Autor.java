package com.patrones.db;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Autor {
	private Integer id;
	private String nombre;
}
