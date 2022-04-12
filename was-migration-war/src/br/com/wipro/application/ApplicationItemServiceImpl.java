package br.com.wipro.application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.wipro.dao.HibernateConfigurator;
import br.com.wipro.dao.HibernateJpaConfigurator;
import br.com.wipro.dto.ApplicationItemDTO;
import br.com.wipro.mapper.ApplicationItemMapper;
import br.com.wipro.model.ApplicationItem;
import br.com.wipro.service.ApplicationItemService;

/**
 * Implementação do serviço responsável pelas ações da tela home-wasmigration.xhtml
 * @author Bruno Justino
 */
public class ApplicationItemServiceImpl implements ApplicationItemService, Serializable {

	/**
	 * Serial version UID
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
	public List<ApplicationItemDTO> listar() {
		try {
			entityManager = HibernateJpaConfigurator.getEntityManager();
			entityManager.getTransaction().begin();
			List<ApplicationItem> result = entityManager.createQuery("from ApplicationItem", ApplicationItem.class ).getResultList();
			entityManager.getTransaction().commit();
			entityManager.close();
			return new ApplicationItemMapper().convertListToDTO(result);
		}catch(Throwable error) {
			error.printStackTrace();
			return this.mockApplicationList();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cadastrar(ApplicationItemDTO item) {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new ApplicationItemMapper().convertDTOToModel(item)); 
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void editar(ApplicationItemDTO item) {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		ApplicationItem entity = entityManager.find(ApplicationItem.class,item.getIdNumber());
		entityManager.getTransaction().begin();
		entity = new ApplicationItemMapper().convertDTOToModel(item , entity);
		entityManager.merge(entity); 
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(ApplicationItemDTO item) {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		ApplicationItem entity = entityManager.find(ApplicationItem.class,item.getIdNumber());
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ApplicationItemDTO buscarPorId(Long idNumber) {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		ApplicationItem entity = entityManager.find(ApplicationItem.class, idNumber);
		entityManager.close();
		List<ApplicationItem> applicationItemList = new ArrayList<ApplicationItem>();
		applicationItemList.add(entity);
		List<ApplicationItemDTO> applicationItemDTOList = new ApplicationItemMapper().convertListToDTO(applicationItemList);
		return applicationItemDTOList.get(0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long countAll() {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(ai) from ApplicationItem ai", Long.class);
		Long resultado = query.getSingleResult();
		entityManager.close();
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long countStatusPendente() {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(ai) from ApplicationItem ai where ai.status = 'Pendente'", Long.class);
		Long resultado = query.getSingleResult();
		entityManager.close();
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long countStatusAndamento() {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(ai) from ApplicationItem ai where ai.status = 'Andamento'", Long.class);
		Long resultado = query.getSingleResult();
		entityManager.close();
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long countStatusFinalizado() {
		entityManager = HibernateJpaConfigurator.getEntityManager();
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(ai) from ApplicationItem ai where ai.status = 'Finalizado'", Long.class);
		Long resultado = query.getSingleResult();
		entityManager.close();
		return resultado;
	}
	
	/**
	 * Método (não utilizado na aplicação) para buscar os dados através da sessão do hibernate.
	 * @return listaDTO - List<ApplicationItemDTO> - lista de aplicações
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ApplicationItemDTO> listarWithSession() {
		List<ApplicationItemDTO> listaDTO = mockApplicationList();
		SessionFactory sessFact = HibernateConfigurator.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		session.beginTransaction();
		List lista = session.createQuery("from ApplicationItem").list();
		session.getTransaction().commit();
		session.close();
		listaDTO = new ApplicationItemMapper().convertListToDTO((List<ApplicationItem>) lista);
		return listaDTO;
	}
	
	/**
	 * Método utilizado para popular uma lista mockada de aplicações.
	 * @return lista - List<ApplicationItemDTO> - lista de DTO mockada
	 */
	private List<ApplicationItemDTO> mockApplicationList() {
		List<ApplicationItemDTO> lista = new ArrayList<ApplicationItemDTO>();
		for(int i=0; i<28; i++) {
			ApplicationItemDTO applicationItemDTO = new ApplicationItemDTO();
			applicationItemDTO.setAcessoStarteam("Sim");
			applicationItemDTO.setCentroCusto("cccc");
			applicationItemDTO.setDataPrevistaTermino(new Date());
			applicationItemDTO.setDisponibilizado("Sim");
			applicationItemDTO.setRamoCriado("Não");
			applicationItemDTO.setIdNumber(Long.valueOf(Integer.toString(i)));
			applicationItemDTO.setObservacao("Observação de teste.");
			applicationItemDTO.setObsRamo("Observação de teste do ramo.");
			applicationItemDTO.setStatus("Pendente");
			lista.add(applicationItemDTO);
		}
		return lista;
	}

}