package com.antrl.examples;

import java.util.ArrayList;
import java.util.List;

import com.antrl.examples.entity.parameter.Profesion;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Getter
@Setter
@Entity //Si es entidad en este caso recopila toda la información. Si es abstract se ignora segun las condiciones en el listener
public  final class Persona<A, B> extends Animal implements JpaMock<Uno, Dos>, JpaTest {
/*
	private String  array[];
	private Domicilio [] arrayDomicilio;

	public List<String> lista;
	List<Domicilio> listaDomicilio;

protected Persona persona;
*/

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabla_numeros")
	@TableGenerator(name = "tabla_numeros", table = "NUMEROS", pkColumnName = "tabla", valueColumnName = "numero", pkColumnValue = "r33", allocationSize = 10)
	@Column(name = "ID_FORMULARIO", length = 10, insertable = true, nullable = false, unique = true, updatable = false)
	private static Long id;

	@OneToOne
	@JoinColumn(name="one")
Uno uno;

@OneToMany
public List<Dos> dos;


	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "DS_TITULO")
	private Profesion profesion;

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "post_tag",
			joinColumns = @JoinColumn(name = "post_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Domicilio> domicilios = new ArrayList<>();

public Persona(){

}
	@Bean
	public void metodoAnotacion(){}

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
