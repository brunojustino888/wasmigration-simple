package br.com.wipro.application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.wipro.dao.HibernateJpaConfigurator;
import br.com.wipro.dto.FaqItemDTO;
import br.com.wipro.mapper.FaqItemMapper;
import br.com.wipro.model.FaqItem;
import br.com.wipro.service.FaqItemService;

/**
 * Implementação do serviço responsável pelos itens de FAQ.
 * @author brunoj - Bruno Justino - Wipro.
 */
public class FaqItemServiceImpl implements Serializable, FaqItemService {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Entity Manager
	 */
	private static EntityManager entityManager = HibernateJpaConfigurator.getEntityManager();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FaqItemDTO> listar() {
		List<FaqItemDTO> listaDTO = new ArrayList<FaqItemDTO>();
		try {
			entityManager = HibernateJpaConfigurator.getEntityManager();
			entityManager.getTransaction().begin();
			List<FaqItem> result = entityManager.createQuery("from FaqItem", FaqItem.class ).getResultList();
			entityManager.getTransaction().commit();
			entityManager.close();
			return new FaqItemMapper().convertListToDTO(result);
		}catch(Throwable error) {
			error.printStackTrace();
		}
		return listaDTO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(FaqItemDTO item) {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		FaqItem entity = entityManager.find(FaqItem.class,item.getIdNumber());
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void editar(FaqItemDTO item) {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		FaqItem entity = entityManager.find(FaqItem.class,item.getIdNumber());
		entityManager.getTransaction().begin();
		entity = new FaqItemMapper().convertDTOToModel(item,entity);
		entityManager.merge(entity); 
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void incluir(FaqItemDTO includeItem) {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		entityManager.getTransaction().begin();
		FaqItem entity = new FaqItem();
		entityManager.persist(new FaqItemMapper().convertDTOToModel(includeItem, entity)); 
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}