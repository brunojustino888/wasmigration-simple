package br.com.wipro.dto;

import java.io.Serializable;

/**
 * DTO utilizado para os dados dos itens de FAQ
 * @author brunoj - Bruno Alves Justino - Wipro
 */
public class FaqItemDTO implements Serializable{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id do item
	 */
	private Long idNumber;
	
	/**
	 * Descrição do item de FAQ.
	 */
	private String description;
	
	/**
	 * Descrição do item de FAQ com quantidade de 50 caracteres.
	 */
	private String shortdescription;
	
	/**
	 * Título do item de FAQ.
	 */
	private String title;
	
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
	 * Método get do atributo shortdescription.
	 * @return shortdescription - String - representa uma pequena descrição de até 50 caracteres.
	 */
	public String getShortdescription() {
		return this.shortdescription;
	}
	
	/**
	 * Método set do atributo shortdescription.
	 * @param shortdescription - String - representa uma pequena descrição de até 50 caracteres.
	 */
	public void setShortdescription(final String shortdescription) {
		this.shortdescription = shortdescription;
	}
	
}