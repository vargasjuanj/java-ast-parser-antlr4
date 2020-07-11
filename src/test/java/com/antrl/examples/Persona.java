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

	private final static int one=1,two=2;
	String [] cadenas;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "DS_TITULO")
	protected Profesion profesion;

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
	public Persona(Profesion profesion) {

	}

	public static  void mostrarNombre() {
	}

	private String caminar(int kilometros) {
		return "caminando";
	}

/*
	static{
		int a=3;
		double b=2.0;
	}


	String uno1="1",dos2="2";
	int tres=3,cuatro=4,cinco=5;
	boolean verdad=true, mentira=false;
	String a=new String("2");


	private String  array[];
	private Domicilio [] arrayDomicilio;

	public List<String> lista;
	List<Domicilio> listaDomicilio;

protected Persona persona;



	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabla_numeros")
	@TableGenerator(name = "tabla_numeros", table = "NUMEROS", pkColumnName = "tabla", valueColumnName = "numero", pkColumnValue = "r33", allocationSize = 10)
	@Column(name = "ID_FORMULARIO", length = 10, insertable = true, nullable = false, unique = true, updatable = false)
	private static Long id=10l;

	@OneToOne
	@JoinColumn(name="one")
Uno uno;

@OneToMany
public List<Dos> dos= new ArrayList<>();



public Persona(){

}
	@Bean
	public void metodoAnotacion(){}



	private String nombre;

	public static final int numero = 2;

	int edad = 120;



	protected Persona(int one, Double two) {

	}


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
