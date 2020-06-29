package com.antrl.examples.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.antrl.examples.entity.parameter.Reparticion;
import com.antrl.examples.entity.parameter.UnidadOrganizacional;
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
@Table(name = "USUARIO")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@ToString
public class UsuarioSeguridad implements Serializable {

	private static final long serialVersionUID = 75637877835827898L;

	@Id
	@Column(name = "CD_USUARIO", length = 10, insertable = false, nullable = false, unique = true, updatable = false)
	private String id;

	@Column(name = "DS_NOMBRE", length = 100)
	private String nombre;

	@Column(name = "DS_APELLIDO", length = 100)
	private String apellido;

	@Column(name = "DS_TELEFONO", length = 100)
	private String telefono;

	@Column(name = "CD_CLAVE", length = 50)
	private String password;
	
	@Column(name = "CD_PERFIL", length = 15)
	private String cdPerfil;
	
	@Column(name = "DS_MAIL", length = 100)
	private String email;

	
	@Column(name = "CD_SECTOR1", length = 10, insertable = false,unique = true,updatable = false)
	private String cdOficina;
	
	
	
	@NotFound(action = NotFoundAction.IGNORE)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(
	        name = "CD_USUARIO",
	        referencedColumnName = "CD_USER",insertable = false,updatable = false
	    )
	private Usuario usuario;
	
	
	@NotFound(action = NotFoundAction.IGNORE)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CD_SECTOR1",insertable = false,updatable = false)
	private Reparticion reparticion;

	
	@NotFound(action = NotFoundAction.IGNORE)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CD_SECTOR1")
	private UnidadOrganizacional unidadOrganizacional;
	
	
	
	@NotFound(action = NotFoundAction.IGNORE)
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CD_USUARIO" , insertable = false, updatable = false)
	private UsuarioEntorno tipoEntorno;
	
	/*
	 * "CD_USUARIO" VARCHAR2(10 CHAR), "CD_PERFIL" VARCHAR2(15 CHAR), "DS_APELLIDO"
	 * VARCHAR2(100 CHAR), "DS_NOMBRE" VARCHAR2(100 CHAR), "CD_SECTOR1" VARCHAR2(10
	 * CHAR), "CD_CADUCIDAD" NUMBER, "CD_DURACION" NUMBER(5,0), "CD_ACTIVO" NUMBER,
	 * "DT_CADUCIDAD" TIMESTAMP (6), "CD_CLAVE" VARCHAR2(50 CHAR), "DS_TELEFONO"
	 * VARCHAR2(50 CHAR), "DS_INTERNO" VARCHAR2(20 CHAR), "DS_MAIL" VARCHAR2(100
	 * CHAR), "CD_USUARIO_ALTA" VARCHAR2(10 CHAR), "DT_ALTA" TIMESTAMP (6),
	 * "CD_USUARIO_ACTUAL" VARCHAR2(10 CHAR), "DT_ACTUAL" TIMESTAMP (6),
	 * "ID_CALENDAR" NUMBER(*,0),
	 */
}
