package com.antrl.examples.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity()
@Table(name = "LOG_DATA")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@ToString
public class LogData implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QT_MILLISECONDS", length = 20, insertable = true, nullable = false, unique = true, updatable = false)
	private String id;

	@Column(name = "TP_ORIGEN_LOG", length = 30)
	private String tpOrigenLog;

	@Column(name = "CD_ORIGEN_LOG", length = 100)
	private String cdOrigenLog;

	@Column(name = "TP_LOG", length = 20)
	private String tpLog;

	@Column(name = "DS_LOG")
	private String dsLog;
	
	@Column(name = "TP_ACCESS_LOG", length = 20)
	private String tpAccessLog;
	
	
	@Column(name = "DS_CLIENT_HOST", length = 20)
	private String dsClientHost;
	
	
	

	@Column(name = "CD_USUARIO_ACTUAL", length = 20,insertable = false,unique = true,updatable = false)
	private String codigoUsuarioActual;

//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
//	@ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = CascadeType.REFRESH)
//	 @JoinColumns({
//		    @JoinColumn(name="CD_USUARIO_ACTUAL", referencedColumnName="CD_USUARIO",nullable = true)
//		  })
//	private UsuarioSeguridad usuarioActual;
	
	
	@Column(name = "CD_USUARIO_ALTA", length = 20,insertable = false,unique = true,updatable = false)
	private String codigoUsuarioAlta;

//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
//	@ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = CascadeType.REFRESH)
//	 @JoinColumns({
//		    @JoinColumn(name="CD_USUARIO_ALTA", referencedColumnName="CD_USUARIO",nullable = true)
//		  })
//	private UsuarioSeguridad usuarioAlta;

	
	@Column(name = "DT_LOG")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtLog;
	
	@Column(name = "TM_LOG")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tmLog;
	
	@Column(name = "DT_ACTUAL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtActual;
	
	@Column(name = "DT_ALTA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAlta;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTpOrigenLog() {
		return tpOrigenLog;
	}

	public void setTpOrigenLog(String tpOrigenLog) {
		this.tpOrigenLog = tpOrigenLog;
	}

	public String getCdOrigenLog() {
		return cdOrigenLog;
	}

	public void setCdOrigenLog(String cdOrigenLog) {
		this.cdOrigenLog = cdOrigenLog;
	}

	public String getTpLog() {
		return tpLog;
	}

	public void setTpLog(String tpLog) {
		this.tpLog = tpLog;
	}

	public String getDsLog() {
		return dsLog;
	}

	public void setDsLog(String dsLog) {
		this.dsLog = dsLog;
	}

	public String getTpAccessLog() {
		return tpAccessLog;
	}

	public void setTpAccessLog(String tpAccessLog) {
		this.tpAccessLog = tpAccessLog;
	}

	public String getDsClientHost() {
		return dsClientHost;
	}

	public void setDsClientHost(String dsClientHost) {
		this.dsClientHost = dsClientHost;
	}

	public String getCodigoUsuarioActual() {
		return codigoUsuarioActual;
	}

	public void setCodigoUsuarioActual(String codigoUsuarioActual) {
		this.codigoUsuarioActual = codigoUsuarioActual;
	}


	public String getCodigoUsuarioAlta() {
		return codigoUsuarioAlta;
	}

	public void setCodigoUsuarioAlta(String codigoUsuarioAlta) {
		this.codigoUsuarioAlta = codigoUsuarioAlta;
	}


	public Date getDtLog() {
		return dtLog;
	}

	public void setDtLog(Date dtLog) {
		this.dtLog = dtLog;
	}

	public Date getTmLog() {
		return tmLog;
	}

	public void setTmLog(Date tmLog) {
		this.tmLog = tmLog;
	}

	public Date getDtActual() {
		return dtActual;
	}

	public void setDtActual(Date dtActual) {
		this.dtActual = dtActual;
	}

	public Date getDtAlta() {
		return dtAlta;
	}

	public void setDtAlta(Date dtAlta) {
		this.dtAlta = dtAlta;
	}

	
}
