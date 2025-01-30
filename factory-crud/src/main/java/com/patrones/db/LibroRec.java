package com.patrones.db;

public record LibroRec(Integer id, String titulo, String isbn, Integer autorId) {
	
	public static Builder builder() {
		return new Builder();
	}
	
	//--utilitaria
	public static class Builder {
		private Integer id;
		private String titulo;
		private String isbn;
		private Integer authorId;
		
		Builder() {
			
		}

		public Builder id(Integer id) {
			this.id = id;
			return this;
		}

		public Builder titulo(String titulo) {
			this.titulo = titulo;
			return this;
		}

		public Builder isbn(String isbn) {
			this.isbn = isbn;
			return this;
		}
		
		public Builder autorId(Integer autorId) {
			this.authorId = autorId;
			return this;
		}

		//factory
		public LibroRec build() {
			return new LibroRec(id, titulo, isbn, authorId);
		}
	}
	
	public static void main(String[] args) {
		LibroRec lb = LibroRec.builder()
			.titulo("titulo1")
			//.id(1)
			.isbn("isbn1")
			.build();
		
		System.out.println(lb);
		
//		//--
//		StringBuilder sb = new StringBuilder();
//		sb.append("hola")
//			.append("mundo");
//		
//		//--
//		Stream.of(1,2,3,4,5)
//			.filter(t->t%2==0)
//			.map(t->t*2)
//			.forEach(System.out::println);
	
	}
	
}

//final public class LibroRec {
//	final private Integer id;
//	final private String titulo;
//	final private String isbn;
//	
//	public LibroRec(Integer id, String titulo, String isbn) {
//		this.id = id;
//		this.titulo = titulo;
//		this.isbn = isbn;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public String getTitulo() {
//		return titulo;
//	}
//
//	public String getIsbn() {
//		return isbn;
//	}
//}
