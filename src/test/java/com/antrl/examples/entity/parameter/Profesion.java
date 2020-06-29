package com.antrl.examples.entity.parameter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "V010")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@ToString
public class Profesion implements Serializable {


	@Id
	@Column(name = "ID", length = 5, unique = true)
	private Integer codigo;

	
	@Column(name = "CDPROF", length = 4, unique = true)
	private String codProfesion;
	
	@Column(name = "NOMBRE", length = 50, unique = true)
	private String descripcion;
	
	@Column(name = "REPARTICION", length = 30)
	private String reparticion;
	

	/*
	 * 
  CREATE TABLE "DESALOCAL_LAN"."" 
   (	"ID" NUMBER(5,0), 
	"CDPROF" VARCHAR2(4 CHAR), 
	"NOMBRE" VARCHAR2(50 CHAR), 
	"REPARTICION" VARCHAR2(30 CHAR), 
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
	 * 
	 */

}
