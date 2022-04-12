package br.com.wipro.service;

import java.util.List;

import br.com.wipro.dto.FaqItemDTO;

/**
 * Service responsável pelos itens de FAQ.
 * @author brunoj - Bruno Alves Justino - Wipro
 */
public interface FaqItemService {

	/**
	 * Utilizado para listar os itens de FAQ cadastrados na base de dados.
	 * @return List<FaqItemDTO> - lista de DTO's de itens de FAQ.
	 */
	List<FaqItemDTO> listar();

	/**
	 * Utilizado para exclusão de um determinado item de FAQ
	 * @param item - FaqItemDTO - DTO do item que será apagado.
	 */
	void excluir(FaqItemDTO item);

	/**
	 * Responsável pela edição de um determinado item de FAQ.
	 * @param item - FaqItemDTO - DTO do item que será modificado.
	 */
	void editar(FaqItemDTO item);

	/**
	 * Utilizado para incluir um determinado item de FAQ na base de dados.
	 * @param includeItem - FaqItemDTO - dto da inclusão.
	 */
	void incluir(FaqItemDTO includeItem);
	
}