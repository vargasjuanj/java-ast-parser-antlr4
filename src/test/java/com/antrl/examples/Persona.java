package com.antrl.examples;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
public abstract class Persona<A, B> extends Animal implements JpaMock<Uno, Dos>, JpaTest {

	@Column(name="id")
	int id;
	/*
	List<String> lista = new ArrayList();
	private String nombre;
	public static final int numero = 2;
	int edad = 120;

	public Persona() {

	}

	protected Persona(int one, Double two) {

	}
abstract  void prueba();
	public static final void mostrarNombre(String elNombre, int edad) {
	}

	private String caminar() {
		return "caminando";
	}

	int contar() {
		return 2;
	}

	protected Object escuchar(String mensaje) {
		return new Object();
	}

 */
}
