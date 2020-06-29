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
@Table(name = "R020")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@ToString
public class Provincia implements Serializable {


	@Id
	@Column(name = "CD_PROVINCIA", length = 2, unique = true)
	private String codigo;

	
	@Column(name = "DS_PROVINCIA", length = 20, unique = true)
	private String descripcion;
	

	/*
	 * 

  CREATE TABLE "DESALOCAL_LAN"."R020" 
   (	"CD_PROVINCIA" VARCHAR2(2 CHAR) NOT NULL ENABLE, 
	"DS_PROVINCIA" VARCHAR2(20 CHAR), 
	"SYSTEMID" VARCHAR2(128 CHAR), 
	"COMPANYID" VARCHAR2(128 CHAR), 
	"USERSTORE" VARCHAR2(128 CHAR), 
	"USERLASTUPDATE" VARCHAR2(128 CHAR), 
	"TIMESTORE" TIMESTAMP (6), 
	"TIMELASTUPDATE" TIMESTAMP (6), 
	"VALIDITYDATEFROM" TIMESTAMP (6), 
	"VALIDITYDATETO" TIMESTAMP (6), 
	 PRIMARY KEY ("CD_PROVINCIA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SIRC_LOCAL"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SIRC_LOCAL" ;

	 * 
	 * 
	 */

}
