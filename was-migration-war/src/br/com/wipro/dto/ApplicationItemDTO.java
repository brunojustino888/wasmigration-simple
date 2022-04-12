/**
 * 
 */
package br.com.wipro.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO para representar os projetos que serão mostrados na tabela da tela home-wasmigration.xhtml
 * @author Bruno Justino
 */
public class ApplicationItemDTO implements Serializable{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id do projeto
	 */
	private Long idNumber;
	
	/**
	 * Status do projeto
	 */
	private String status;
	
	/**
	 * Centro de custo do projeto
	 */
	private String centroCusto;
	
	/**
	 * Data prevista para a migração do projeto
	 */
	private Date dataPrevistaTermino;
	
	/**
	 * Data da migração do projeto
	 */
	private Date dataTermino;
	
	/**
	 * Observação do projeto
	 */ 
	private String observacao;
	
	/**
	 * Indicador de acesso ao projeto 
	 */
	private String acessoStarteam;
	
	/**
	 * Indicador para o ramo criado da migração
	 */
	private String ramoCriado;
	
	/**
	 * Observação pertinente ao ramo do starteam
	 */
	private String obsRamo;
	
	/**
	 * Indicador para a disponibilização de migração de um determinado projeto
	 */
	private String disponibilizado;

	public Long getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(final Long idNumber) {
		this.idNumber = idNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getCentroCusto() {
		return this.centroCusto;
	}

	public void setCentroCusto(final String centroCusto) {
		this.centroCusto = centroCusto;
	}


	public String getObservacao() {
		return this.observacao;
	}

	public void setDataPrevistaTermino(final Date dataPrevistaTermino2) {
		this.dataPrevistaTermino = dataPrevistaTermino2;
	}

	public Date getDataPrevistaTermino() {
		return this.dataPrevistaTermino;
	}

	public void setObservacao(final String observacao) {
		this.observacao = observacao;
	}

	public String getObsRamo() {
		return this.obsRamo;
	}

	public void setObsRamo(final String obsRamo) {
		this.obsRamo = obsRamo;
	}

	public String getAcessoStarteam() {
		return this.acessoStarteam;
	}

	public void setAcessoStarteam(final String acessoStarteam) {
		this.acessoStarteam = acessoStarteam;
	}

	public String getRamoCriado() {
		return this.ramoCriado;
	}

	public void setRamoCriado(final String ramoCriado) {
		this.ramoCriado = ramoCriado;
	}

	public String getDisponibilizado() {
		return this.disponibilizado;
	}

	public void setDisponibilizado(final String disponibilizado) {
		this.disponibilizado = disponibilizado;
	}

	public Date getDataTermino() {
		return this.dataTermino;
	}

	public void setDataTermino(final Date dataTermino) {
		this.dataTermino = dataTermino;
	}
	
}