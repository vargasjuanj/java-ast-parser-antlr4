package com.antrl.examples.entity.parameter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.antrl.examples.entity.UsuarioSeguridad;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ORG_UNIT")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@ToString
public class UnidadOrganizacional implements Serializable {


	@Id
	@Column(name = "CD_ORG_UNIT", length = 10, unique = true)
	private String codigo;

	@Column(name = "DS_ORG_UNIT", length = 100)
	private String descripcion;

	@Column(name = "IS_EXTERNAL", length = 1)
	private Integer isExternal;

	@Column(name = "CD_MANAGER", length = 10)
	private String cdManager;

	@Column(name = "CD_SUPERIOR", length = 10)
	private String cdSuperior;

	@Column(name = "DS_EMAIL", length = 40)
	private String email;

	
	@ToString.Exclude
	//Buscar dependencia
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, orphanRemoval = false,  mappedBy = "unidadOrganizacional" )
	private List<UsuarioSeguridad> ListUsuarioSeguridad;

	/*
	 * 
	 * CREATE TABLE "DESALOCAL_LAN"."ORG_UNIT" ( "CD_ORG_UNIT" VARCHAR2(10 CHAR),
	 * "DS_ORG_UNIT" VARCHAR2(100 CHAR), "IS_EXTERNAL" NUMBER, "CD_MANAGER"
	 * VARCHAR2(10 CHAR), "CD_SUPERIOR" VARCHAR2(10 CHAR), "DS_EMAIL" VARCHAR2(40
	 * CHAR), "CD_USUARIO_STORE" VARCHAR2(10 CHAR), "DT_STORE" TIMESTAMP (6),
	 * "CD_USUARIO_UPDATE" VARCHAR2(10 CHAR), "DT_LAST_UPDATE" TIMESTAMP (6),
	 * "CD_CAN_REC_DOC" NUMBER, "ID_CALENDAR" NUMBER(*,0) ) SEGMENT CREATION
	 * IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
	 * STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
	 * PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE
	 * DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SIRC_LOCAL" ;
	 * 
	 * 
	 */

}
