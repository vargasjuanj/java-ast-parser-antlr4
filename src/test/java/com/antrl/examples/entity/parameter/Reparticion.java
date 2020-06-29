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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "V009")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@ToString
public class Reparticion implements Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabla_numeros")
//	@TableGenerator(name = "tabla_numeros", table = "NUMEROS", pkColumnName = "tabla", valueColumnName = "numero", pkColumnValue = "v009", allocationSize = 10)
//	@Column(name = "ID", length = 4, insertable = true, nullable = false, unique = true, updatable = false)
//	private Long id;

	@Id
	@Column(name = "CODIGO", length = 10)
	private String codigo;

	@Column(name = "DESCRIPCION", length = 100)
	private String descripcion;

	@Column(name = "OFICINA", length = 100, unique = true)
	private String oficina;

	
	@ToString.Exclude
	//Buscar dependencia en miw upm tpv spring
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, orphanRemoval = false,  mappedBy = "reparticion" )
	private List<UsuarioSeguridad> ListUsuarioSeguridad;

	
	
	/*
	 * 
	 *  CREATE TABLE "DESALOCAL_LAN"."V009" 
   (	"ID" NUMBER(4,0), 
	"CODIGO" VARCHAR2(10 CHAR), 
	"DESCRIPCION" VARCHAR2(100 CHAR), 
	"OFICINA" VARCHAR2(100 CHAR), 
	"SYSTEMID" VARCHAR2(128 CHAR), 
	"COMPANYID" VARCHAR2(128 CHAR), 
	"USERSTORE" VARCHAR2(128 CHAR), 
	"USERLASTUPDATE" VARCHAR2(128 CHAR), 
	"TIMESTORE" TIMESTAMP (6), 
	"TIMELASTUPDATE" TIMESTAMP (6), 
	"VALIDITYDATEFROM" TIMESTAMP (6), 
	"VALIDITYDATETO" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SIRC_LOCAL" ;
	 * 
	 */

}
