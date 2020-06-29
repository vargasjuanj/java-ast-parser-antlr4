package com.antrl.examples.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.antrl.examples.entity.parameter.Departamento;
import com.antrl.examples.entity.parameter.Profesion;
import com.antrl.examples.entity.parameter.Provincia;
import com.antrl.examples.entity.parameter.UsuarioEntorno;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DIG_DOC_R33")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@ToString
public class Usuario implements Serializable {

	private static final long serialVersionUID = 75637877835827898L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabla_numeros")
	@TableGenerator(name = "tabla_numeros", table = "NUMEROS", pkColumnName = "tabla", valueColumnName = "numero", pkColumnValue = "r33", allocationSize = 10)
	@Column(name = "ID_FORMULARIO", length = 10, insertable = true, nullable = false, unique = true, updatable = false)
	private Long id;

	@Column(name = "NM_NOMBRE", length = 100)
	private String nombre;

	@Column(name = "NM_APELLIDO", length = 100)
	private String apellido;

	@Column(name = "NU_DOCUMENTO", length = 100)
	private String nroDocumento;

	@Column(name = "CD_CIRCUNSCRIPCION", length = 3)
	private String circunscripcion;
	
	@Column(name = "DS_MAIL", length = 100)
	private String email;
	
	@Column(name = "CD_DEPARTAMENTO", length = 5,insertable = false,unique = true,updatable = false)
	private String cdDepartamento;
	
	
	@Column(name = "CD_PROVINCIA", length = 10,insertable = false,unique = true,updatable = false)
	private String cdProvincia;
	
	
	
	@Column(name = "DS_TITULO", length = 100,insertable = false,unique = true,updatable = false)
	private String cdTitulo;
	
	
	@Column(name = "CD_USER", length = 20,insertable = false,unique = true,updatable = false)
	private String codigoUsuario;

	@ToString.Exclude
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = CascadeType.REFRESH)
	 @JoinColumns({
		    @JoinColumn(name="CD_USER", referencedColumnName="CD_USUARIO",nullable = true)
		  })
	private UsuarioSeguridad usuarioSeguridad;

	
	@NotFound(action = NotFoundAction.IGNORE)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CD_USER", insertable = false,updatable = false)
	private UsuarioEntorno tipoEntorno;
	
	
//	
	@NotFound(action = NotFoundAction.IGNORE)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CD_DEPARTAMENTO")
	private Departamento departamento;
//	
//	
//	
	@NotFound(action = NotFoundAction.IGNORE)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CD_PROVINCIA")
	private Provincia provincia;
//	
//	
//
	@NotFound(action = NotFoundAction.IGNORE)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "DS_TITULO")
	private Profesion profesion;


	private String caminar(int km){
		return "metros";
	}

	/*
	 * "ID_FORMULARIO" NUMBER(10,0) NOT NULL ENABLE, "TP_PERSONA_ADICIONAL"
	 * VARCHAR2(4 CHAR), "NM_NOMBRE" VARCHAR2(100 CHAR), "NM_APELLIDO" VARCHAR2(100
	 * CHAR), "TP_DOCUMENTO" VARCHAR2(3 CHAR), "NU_DOCUMENTO" VARCHAR2(20 CHAR),
	 * "NU_CUIL" NUMBER(11,0), "NU_REG_NOTARIAL" NUMBER(10,0), "TP_TIPO" VARCHAR2(3
	 * CHAR), "CD_PROV_REG_NOT" VARCHAR2(2 CHAR), "CD_CIRCUNSCRIPCION" VARCHAR2(3
	 * CHAR), "NU_MAT_PROFESIONAL" NUMBER(10,0), "NU_TEL_PROF" VARCHAR2(20 CHAR),
	 * "NU_TEL_PARTICULAR" VARCHAR2(50 CHAR), "DS_MAIL" VARCHAR2(100 CHAR),
	 * "NM_CARGO" VARCHAR2(30 CHAR), "CD_USER" VARCHAR2(20 CHAR), "CD_ESTADO"
	 * VARCHAR2(4 CHAR), "CD_DOMICILIO" NUMBER(10,0), "IMG_FIRMA_FOD" VARCHAR2(200
	 * CHAR), "CD_RETIRO" NUMBER(10,0), "DS_CALLE" VARCHAR2(120 CHAR), "CD_PERSONA"
	 * NUMBER(10,0), "CD_DATOS" NUMBER(10,0), "CD_NACIONALIDAD" VARCHAR2(5 CHAR),
	 * "CD_BARRIO" VARCHAR2(50 CHAR), "DS_LOTE" VARCHAR2(30 CHAR), "DS_MANZANA"
	 * VARCHAR2(30 CHAR), "CD_DEPARTAMENTO" VARCHAR2(5 CHAR), "CD_PROVINCIA"
	 * VARCHAR2(10 CHAR), "CD_LOCALIDAD" VARCHAR2(50 CHAR), "DS_DETALLES"
	 * VARCHAR2(1000 CHAR), "DS_NUMERO" NUMBER(4,0), "DS_CALLE_ENTRE" VARCHAR2(20
	 * CHAR), "NU_PISO" NUMBER(6,0), "NU_DEPARTAMENTO" NUMBER(6,0),
	 * "IN_SOLC_FIRMA_DIGITAL" VARCHAR2(10 CHAR), "CD_DEPARTAMENTO_SOLIC" VARCHAR2(5
	 * CHAR), "COD_AREA_TEL_CELULAR" NUMBER(5,0), "DS_OFICINA_PRINCIPAL"
	 * VARCHAR2(100 CHAR), "DS_CIRCUNSC" VARCHAR2(50 CHAR), "DS_C_OFICINA"
	 * VARCHAR2(100 CHAR), "DS_NRO_OFICINA" VARCHAR2(100 CHAR),
	 * "CD_OFICINA_PRINCIPAL" VARCHAR2(4 CHAR), "CD_C_OFICINA" VARCHAR2(8 CHAR),
	 * "CD_NRO_OFICINA" VARCHAR2(8 CHAR), "CD_CIRCUNSC" VARCHAR2(4 CHAR),
	 * "DS_CONVENIO" NUMBER(10,0), "DS_INICIALES" VARCHAR2(20 CHAR),
	 * "NRO_LEGAJO_LEY" NUMBER(10,0), "NRO_LEGAJO_PODER" NUMBER(10,0), "DS_TITULO"
	 * VARCHAR2(100 CHAR), "DS_NIVEL_ESTUDIO" VARCHAR2(1 CHAR),
	 * "CD_CIRCUNSCRIPCION_IN001" VARCHAR2(3 CHAR), "DT_POSESION" DATE,
	 * "DT_NACIMIENTO" DATE, "TP_BAJA" VARCHAR2(20 CHAR), "DT_BAJA" DATE,
	 * "CD_USER_LAST_UPDATE" VARCHAR2(10 CHAR), "DT_LAST_UPDATE" TIMESTAMP (6),
	 * "CD_USER_STORE" VARCHAR2(10 CHAR), "DT_STORE" TIMESTAMP (6),
	 */
}
