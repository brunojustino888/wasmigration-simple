/**
 * 
 */
package br.com.wipro.model;

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

/**
 * Classe modelo para representar um determinado aplicativo.
 * @author Bruno Justino
 */
@Entity
@Table(name="FAQITEM")
public class FaqItem implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id do item
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long idNumber;
	
	/**
	 * Observação pertinente ao ramo do starteam
	 */
	@Column(name="TITLE")
	private String title;
	
	/**
	 * Observação pertinente ao ramo do starteam
	 */
	@Column(name="DESCRIPTION")
	private String description;
	
	/**
	 * Indicador da última atualização do registro
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ULTIMA_ATUALIZACAO")
	private Date atualizacao;
	
	/**
	 * Usuário da última atualização de registro
	 */
	@Column(name="USUARIO_ULTIMA_ATUALIZACAO")
	private String usuarioUltimaAtualizacao;
	
	/**
	 * Usuário de criação do registro
	 */
	@Column(name="USUARIO_CRIACAO")
	private String usuarioCriacao;
	
	/**
	 * Data da criação do registro
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CRIACAO")
	private Date dataCriacao;

	/**
	 * Método get do atributo
	 * @return idNumber - Long - chave primária. 
	 */
	public Long getIdNumber() {
		return this.idNumber;
	}

	/**
	 * Método set do atributo
	 * @param idNumber - Long - chave primária. 
	 */
	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}
	
	/**
	 * Método get do atributo title.
	 * @return title - String - Representa o título de um item de Faq
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Método set do atributo title.
	 * @param title - String - Representa o título de um item de Faq
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Método get do atributo description.
	 * @return description - String - Representa a descrição de um item de Faq
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Método set do atributo description.
	 * @param description - String - Representa a descrição de um item de Faq
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Método get do atributo atualizacao.
	 * @return atualizacao - Date - data da atualização do registro.
	 */
	public Date getAtualizacao() {
		return this.atualizacao;
	}

	/**
	 * Método set do atributo atualizacao.
	 * @param atualizacao - Date - data da atualização do registro.
	 */
	public void setAtualizacao(final Date atualizacao) {
		this.atualizacao = atualizacao;
	}

	/**
	 * Método get do atributo usuarioUltimaAtualizacao.
	 * @return usuarioUltimaAtualizacao - String - usuário que realizou a última atualização de registro.
	 */
	public String getUsuarioUltimaAtualizacao() {
		return this.usuarioUltimaAtualizacao;
	}

	/**
	 * Método set do atributo usuarioUltimaAtualizacao.
	 * @param usuarioUltimaAtualizacao - String - usuário que realizou a última atualização de registro.
	 */
	public void setUsuarioUltimaAtualizacao(final String usuarioUltimaAtualizacao) {
		this.usuarioUltimaAtualizacao = usuarioUltimaAtualizacao;
	}

	/**
	 * Método get do atributo usuarioCriacao.
	 * @return usuarioCriacao - String - usuário da criação do registro.
	 */
	public String getUsuarioCriacao() {
		return this.usuarioCriacao;
	}

	/**
	 * Método set do atributo usuarioCriacao.
	 * @param usuarioCriacao - String -  usuário da criação do registro.
	 */
	public void setUsuarioCriacao(final String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	/**
	 * Método get do atributo dataCriacao.
	 * @return dataCriacao - Date - data da criação do registro.
	 */
	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	/**
	 * Método set do atributo dataCriacao.
	 * @param dataCriacao - Date - data da criação do registro.
	 */
	public void setDataCriacao(final Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atualizacao == null) ? 0 : atualizacao.hashCode());
		result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((usuarioCriacao == null) ? 0 : usuarioCriacao.hashCode());
		result = prime * result + ((usuarioUltimaAtualizacao == null) ? 0 : usuarioUltimaAtualizacao.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FaqItem other = (FaqItem) obj;
		if (atualizacao == null) {
			if (other.atualizacao != null)
				return false;
		} else if (!atualizacao.equals(other.atualizacao))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (usuarioCriacao == null) {
			if (other.usuarioCriacao != null)
				return false;
		} else if (!usuarioCriacao.equals(other.usuarioCriacao))
			return false;
		if (usuarioUltimaAtualizacao == null) {
			if (other.usuarioUltimaAtualizacao != null)
				return false;
		} else if (!usuarioUltimaAtualizacao.equals(other.usuarioUltimaAtualizacao))
			return false;
		return true;
	}
	
}