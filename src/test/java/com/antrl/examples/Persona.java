package com.antrl.examples;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Getter
@Setter
public abstract class Persona<A, B> extends Animal implements JpaMock<Uno, Dos>, JpaTest {

	private String  array[];
	private Domicilio [] arrayDomicilio;

	public List<String> lista;
	List<Domicilio> listaDomicilio;

protected Persona persona;




	/*
	@Bean
	public void metodoAnotacion(){

	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabla_numeros")
	@TableGenerator(name = "tabla_numeros", table = "NUMEROS", pkColumnName = "tabla", valueColumnName = "numero", pkColumnValue = "r33", allocationSize = 10)
	@Column(name = "ID_FORMULARIO", length = 10, insertable = true, nullable = false, unique = true, updatable = false)
	private Long id;

	public String algo;
	@Column(name="cam")
private String campo;
	@OneToOne
	@JoinColumn(name="one")
Uno uno;

@OneToMany
public List<Dos> dos;

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "post_tag",
			joinColumns = @JoinColumn(name = "post_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Domicilio> domicilios = new ArrayList<>();

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
