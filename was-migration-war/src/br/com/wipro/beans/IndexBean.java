package br.com.wipro.beans;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.wipro.application.ApplicationItemServiceImpl;
import br.com.wipro.dao.HibernateConfigurator;
import br.com.wipro.dao.HibernateJpaConfigurator;
import br.com.wipro.service.ApplicationItemService;

/**
 * Respons?vel por realizar um teste de conex?o com a base de dados no in?cio da
 * aplica??o.
 * 
 * @author Bruno Justino
 */
@Named
@RequestScoped
public class IndexBean implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Servi?o respons?vel pelas a??es da tela. 
	 */
	private ApplicationItemService applicationItemService;
	
	/**
	 * Representa a quantidade de aplicativos.
	 */
	private Long total;
	
	/**
	 * Representa a quantidade de aplicativos pendentes para migra??o.
	 */
	private Long pendentes;
	
	/**
	 * Representa a quantidade de aplicativos finalizados.
	 */
	private Long finalizados;
	
	/**
	 * Representa a quantidade de aplicativos em andamento.
	 */
	private Long andamento;
	
	/**
	 * Objeto do gr?fico inserido na view.
	 */
	private PieChartModel pieModel;
	
	/**
	 * Construtor inicializando gr?fico de status das aplica??es na view.
	 */
	public IndexBean() {
		this.applicationItemService = new ApplicationItemServiceImpl();
		this.andamento = this.applicationItemService.countStatusAndamento();            
		this.finalizados = this.applicationItemService.countStatusFinalizado();		
		this.pendentes = this.applicationItemService.countStatusPendente();
		this.total = this.applicationItemService.countAll();
		this.pieModel = new PieChartModel(); //1988==100%  
		double percentualPeso = 1988/this.total;
		this.pieModel.set("Em andamento", percentualPeso * this.andamento);  
		this.pieModel.set("Pendentes", percentualPeso * this.pendentes);
		this.pieModel.set("Finalizados", percentualPeso * this.finalizados);
		this.pieModel.setFill(false);
		this.pieModel.setShowDataLabels(true);
		this.pieModel.setLegendPosition("w");
	}
	
	/**
	 * Fornece uma sess?o com o hibernate ou um EntityManager.
	 * @return boolean - representa o resultado do teste de conex?o.
	 */
	public boolean test() {
		try {
			HibernateConfigurator.getSessionFactory().getCurrentSession();
			return true;
		} catch (Throwable error) {
			try {
				HibernateJpaConfigurator.getEntityManager();
				return true;
			} catch (Throwable erro) {
				erro.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * M?todo get do atributo total.
	 * @return total - Long - Representa a quantidade de aplicativos.
	 */
	public Long getTotal() {
		return this.total;
	}

	/**
	 * M?todo get do atributo andamento.
	 * @return pendentes - Long - Representa a quantidade de aplicativos pendentes na migra??o.
	 */
	public Long getPendentes() {
		return this.pendentes;
	}

	/**
	 * M?todo get do atributo andamento.
	 * @return finalizados - Long - Representa a quantidade de aplicativos finalizados na migra??o.
	 */
	public Long getFinalizados() {
		return this.finalizados;
	}

	/**
	 * M?todo get do atributo andamento.
	 * @return andamento - Long - Representa a quantidade de aplicativos em andamento na migra??o.
	 */
	public Long getAndamento() {
		return this.andamento;
	}
	
	/**
	 * M?todo get do atributo pieModel.
	 * @return pieModel - PieChartModel - gr?fico inserido na tela.
	 */
	public PieChartModel getPieModel() {
		return this.pieModel;
	}

	/**
	 * M?todo set do atributo pieModel.
	 * @param pieModel - PieChartModel - gr?fico inserido na tela.
	 */
	public void setPieModel(final PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
	
}