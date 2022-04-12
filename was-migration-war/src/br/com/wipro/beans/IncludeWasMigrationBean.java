package br.com.wipro.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.com.wipro.application.ApplicationItemServiceImpl;
import br.com.wipro.dto.ApplicationItemDTO;
import br.com.wipro.enums.ApplicationItemEnum;
import br.com.wipro.service.ApplicationItemService;

/**
 * Back-end da tela include-wasmigration.xhtml
 * @author Bruno Justino
 */
@Named
@ViewScoped
public class IncludeWasMigrationBean implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Serviço responsável pelas ações da tela. 
	 */
	private ApplicationItemService applicationItemService;
	
	/**
	 * DTO do objeto selecionado na tabela da tela home-wasmigration.xhtml.
	 */
	private ApplicationItemDTO applicationItemDTO;
	
	/**
	 * Representa a data de término prevista coletada em tela
	 */
	private Date dataPrevistaTermino;
	
	/**
	 * Representa a data de término coletada em tela
	 */
	private Date dataTermino;
	
	/**
	 * Representa o status selecionado pelo usuário na tela
	 */
	private String statusSelected;
	
	/**
	 * Representa o centro de custo coletado em tela
	 */
	private String cccc;
	
	/**
	 * Representa se o ramo está criado para o aplicativo (coletado em tela).
	 */
	private boolean ramoCriado;
	
	/**
	 * Representa se o starteam concede acesso (coletado em tela).
	 */
	private boolean acessoRamo;
	
	/**
	 * Representa se o o aplicativo foi disponibilizado para migração (coletado em tela).
	 */
	private boolean disponibilizado;
	
	/**
	 * Representa a observação do ramo.
	 */
	private String obsRamo;
	
	/**
	 * Representa a observação coletada em tela.
	 */
	private String obs;

	/**
	 * Usado para modificar o form na tela.
	 */
	private boolean calendarTermino = false;

	/**
	 * Utilizado para modificar o form na tela.
	 */
	private boolean calendarPrevisao = true;
	
	/**
	 * Método utilizado para finalizar a inclusão de um novo registro.
	 */
	public void include() { 
		this.applicationItemDTO = new ApplicationItemDTO();
		this.applicationItemDTO.setAcessoStarteam(acessoRamo==true? "Sim": "Não");
		this.applicationItemDTO.setRamoCriado(ramoCriado==true? "Sim": "Não");
		this.applicationItemDTO.setDisponibilizado(disponibilizado==true? "Sim": "Não");
		this.applicationItemDTO.setCentroCusto(cccc);
		this.applicationItemDTO.setDataPrevistaTermino(dataPrevistaTermino);
		this.applicationItemDTO.setDataTermino(dataTermino);
		if(obs == null || "".equals(obs)) {
			this.applicationItemDTO.setObservacao("Nenhuma observação à ser exibida.");
		}else {
			this.applicationItemDTO.setObservacao(obs);
		}
		if(obsRamo == null || "".equals(obsRamo)) {
			this.applicationItemDTO.setObsRamo("Nenhuma observação à ser exibida.");
		}else {
			this.applicationItemDTO.setObsRamo(obsRamo);
		}
		this.applicationItemDTO.setStatus(statusSelected); 
		try {
			this.applicationItemService = new ApplicationItemServiceImpl();
			this.applicationItemService.cadastrar(this.applicationItemDTO);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Operação realizada com sucesso!","O projeto "+this.cccc+" foi cadastrado corretamente."));
		}catch(Throwable error) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Por favor contate o administrador do sistema.","Um erro ocorreu!\nPor favor contate o administrador do sistema."));
			error.printStackTrace();
		}
	}
	
	/**
	 * Limpa o formulário da tela de inclusão
	 */
	public void limparCampos() {
		this.acessoRamo = false;
		this.ramoCriado = false;
		this.disponibilizado = false;
		this.cccc = null;
		this.dataPrevistaTermino = null;
		this.dataTermino = null;
		this.obs = null;
		this.obsRamo = null;
		this.statusSelected = null;
	}
	
	/**
	 * Carrega o combo de status na tela.
	 * @return statusCombo - ArrayList<String> - status dos aplicativos.
	 */
	public List<String> loadComboStatus(){
		List<String> statusCombo = new ArrayList<String>();
		for(ApplicationItemEnum aux : ApplicationItemEnum.values()) {
			statusCombo.add(aux.toString());
		}
		return statusCombo;
	}
	
	/**
	 * Método utilizado para verificar o status da aplicação que será inclusa na tabela.
	 */
	public void verificaStatus() {
		if(this.statusSelected.equals("Finalizado")){
			this.calendarTermino = true;
			this.calendarPrevisao = false;
		}else {
			this.calendarTermino = false;
			this.calendarPrevisao = true;
		}
	}
	
	/**
	 * Método get do atributo cccc.
	 * @return cccc - String - centro de custo coletado em tela.
	 */
	public String getCccc() {
		return this.cccc;
	}

	/**
	 * Método set do atributo cccc.
	 * @param cccc - String - centro de custo coletado em tela.
	 */
	public void setCccc(final String cccc) {
		this.cccc = cccc;
	}

	/**
	 * Método get do atributo statusSelected.
	 * @return statusSelected - String - valor do status selecionado em tela.
	 */
	public String getStatusSelected() {
		return this.statusSelected;
	}

	/**
	 * Método set do atributo statusSelected.
	 * @param statusSelected - String - valor do status selecionado em tela.
	 */
	public void setStatusSelected(final String statusSelected) {
		this.statusSelected = statusSelected;
	}

	/**
	 * Método get do atributo ramoCriado.
	 * @return ramoCriado - boolean - representa se o ramo está criado.
	 */
	public boolean isRamoCriado() {
		return this.ramoCriado;
	}

	/**
	 * Método set do atributo ramoCriado.
	 * @param ramoCriado - boolean - representa se o ramo está criado.
	 */
	public void setRamoCriado(final boolean ramoCriado) {
		this.ramoCriado = ramoCriado;
	}

	/**
	 * Método get do atributo acessoRamo.
	 * @return acessoRamo - boolean - representa se possui acesso.
	 */
	public boolean isAcessoRamo() {
		return this.acessoRamo;
	}

	/**
	 * Método set do atributo acessoRamo.
	 * @param acessoRamo - boolean - representa se possui acesso.
	 */
	public void setAcessoRamo(final boolean acessoRamo) {
		this.acessoRamo = acessoRamo;
	}

	/**
	 * Método get do atributo disponibilizado.
	 * @return disponibilizado - boolean - representa se está disponível para migrar.
	 */
	public boolean isDisponibilizado() {
		return this.disponibilizado;
	}

	/**
	 * Método set do atributo disponibilizado.
	 * @param disponibilizado - boolean - representa se está disponível para migrar.
	 */
	public void setDisponibilizado(final boolean disponibilizado) {
		this.disponibilizado = disponibilizado;
	}

	/**
	 * Representa a data de término prevista
	 * @return dataPrevistaTermino - Date - data de término prevista.
	 */
	public Date getDataPrevistaTermino() {
		return this.dataPrevistaTermino;
	}
	
	/**
	 * Representa a data de término prevista
	 * @param dataPrevistaTermino - Date - data de término prevista.
	 */
	public void setDataPrevistaTermino(final Date dataPrevistaTermino) {
		this.dataPrevistaTermino = dataPrevistaTermino;
	}

	/**
	 * Método get do atributo obsRamo
	 * @return obsRamo - String observação do ramo coletada em tela.
	 */
	public String getObsRamo() {
		return this.obsRamo;
	}

	/**
	 * Método set do atributo obsRamo.
	 * @param obsRamo - String - oservação do ramo coletada em tela.
	 */
	public void setObsRamo(final String obsRamo) {
		this.obsRamo = obsRamo;
	}

	/**
	 * Método get do atributo
	 * @return obs - String - observação coletada em tela.
	 */
	public String getObs() {
		return this.obs;
	}

	/**
	 * Método set do atributo.
	 * @param obsRamo - String - observação coletada em tela.
	 */
	public void setObs(final String obs) {
		this.obs = obs;
	}

	/**
	 * Método get do atributo dataTermino.
	 * @return dataTermino - Date - representa o dia que foi finalizada a migração.
	 */
	public Date getDataTermino() {
		return this.dataTermino;
	}

	/**
	 * Método set do atributo dataTermino.
	 * @param dataTermino - Date - representa o dia que foi finalizada a migração.
	 */
	public void setDataTermino(final Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	/**
	 * Método get do atributo calendarTermino
	 * @return calendarTermino - boolean - representa se está selecionado no combo o status Finalizado.
	 */
	public boolean isCalendarTermino() {
		return this.calendarTermino;
	}

	/**
	 * Método set do atributo calendarTermino
	 * @param calendarTermino - boolean - representa se está selecionado no combo o status Finalizado.
	 */
	public void setCalendarTermino(final boolean calendarTermino) {
		this.calendarTermino = calendarTermino;
	}

	/**
	 * Método get do atributo calendarPrevisao
	 * @return calendarPrevisao - boolean - representa se está selecionado no combo o status Pendente ou Andamento.
	 */
	public boolean isCalendarPrevisao() {
		return this.calendarPrevisao;
	}

	/**
	 * Método set do atributo calendarPrevisao
	 * @param calendarPrevisao - boolean - representa se está selecionado no combo o status Pendente ou Andamento.
	 */
	public void setCalendarPrevisao(final boolean calendarPrevisao) {
		this.calendarPrevisao = calendarPrevisao;
	}
	
	/**
	 * Utilizado para bloquear os dias no calendário mostrado em tela.
	 * @return new Date() - Date - a data de hoje.
	 */ 
	public Date today() {
		return new Date();
	}
	
}