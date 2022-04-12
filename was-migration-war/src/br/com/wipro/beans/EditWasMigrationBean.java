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
 * Back-end da tela home-wasmigration.xhtml
 * @author Bruno Justino
 */
@Named
@ViewScoped
public class EditWasMigrationBean implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Serviço responsável pelas ações da tela. 
	 */
	private ApplicationItemService applicationItemService;

	/**
	 * Representa o ID do projeto que sofrerá as alterações.
	 */
	private Long item;

	/**
	 * Representa o projeto que sofrerá as alterações em tela.
	 */
	private ApplicationItemDTO appItem;
	
	/**
	 * Usado para modificar o form na tela.
	 */
	private boolean calendarTermino = false;

	/**
	 * Utilizado para modificar o form na tela.
	 */
	private boolean calendarPrevisao = false;
	
	/**
	 * Representa o button com o valor de true ou false em tela.
	 */
	private boolean acessoStarteamButton;
	
	/**
	 * Representa o button com o valor de true ou false em tela.
	 */
	private boolean ramoCriadoButton;
	
	/**
	 * Representa o button com o valor de true ou false em tela.
	 */
	private boolean disponibilizadoButton;
	
	/**
	 * Construtor inicializando o serviço correspondente à tela e buscando a entidade que deverá ser transformada em DTO para edição. 
	 */
	public EditWasMigrationBean() {
		this.appItem = (ApplicationItemDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("editDTO");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("editDTO");
		System.out.println(this.appItem.getIdNumber());
		if(this.appItem.getStatus().equals("Finalizado")) {
			this.calendarTermino = true;
			this.calendarPrevisao = false;
		}else {
			this.calendarTermino = false;
			this.calendarPrevisao = true;
		}
		if(this.appItem.getAcessoStarteam().equals("Sim")) {
			this.acessoStarteamButton = true;
		}else {
			this.acessoStarteamButton = false;
		}
		if(this.appItem.getRamoCriado().equals("Sim")) {
			this.ramoCriadoButton = true;
		}else {
			this.ramoCriadoButton = false;
		}
		if(this.appItem.getDisponibilizado().equals("Sim")) {
			this.disponibilizadoButton = true;
		}else {
			this.disponibilizadoButton = false;
		}
	}

	/**
	 * Método utilizado para fazer a edição das informações do aplicativo em tela.
	 */
	public void editar() {
		if(this.acessoStarteamButton) {
			this.appItem.setAcessoStarteam("Sim");
		}else {
			this.appItem.setAcessoStarteam("Não");
		}
		if(this.ramoCriadoButton) {
			this.appItem.setRamoCriado("Sim");
		}else {
			this.appItem.setRamoCriado("Não");
		}
		if(this.disponibilizadoButton) {
			this.appItem.setDisponibilizado("Sim");
		}else {
			this.appItem.setDisponibilizado("Não");
		}
		try {
			this.applicationItemService = new ApplicationItemServiceImpl();
			this.applicationItemService.editar(appItem);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Operação realizada com sucesso!","As informações do projeto "+this.appItem.getCentroCusto()+" foram atualizadas."));
			Thread.sleep(3000);
		}catch(Throwable error) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Por favor contate o administrador do sistema.","Um erro ocorreu!\nPor favor contate o administrador do sistema."));
			error.printStackTrace();
		}
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
		for(ApplicationItemEnum auxl : ApplicationItemEnum.values()) {
			if(this.appItem.getStatus().equals(auxl.name())) {
				statusCombo.remove(auxl.toString());
				statusCombo.add(1,auxl.toString());
			}
		}
		return statusCombo;
	}
	
	/**
	 * Método utilizado para verificar o status da aplicação que será inclusa na tabela.
	 */
	public void verificaStatus() {
		if(this.appItem.getStatus().equals("Finalizado")){
			this.calendarTermino = true;
			this.calendarPrevisao = false;
		}else {
			this.calendarTermino = false;
			this.calendarPrevisao = true;
		}
	}
	
	/**
	 * Responsável pela navegação da tela home-wasmigration.xhtml para a edição de um determinado registro.
	 * @return edit-wasmigration.xhtml - String - nome da tela 
	 */
	public String redirectHome() {
		return "home-wasmigration.xhtml?faces-redirect=true";
	}
	
	/**
	 * Utilizado para bloquear os dias no calendário mostrado em tela.
	 * @return new Date() - Date - a data de hoje.
	 */ 
	public Date today() {
		return new Date();
	}
	
	/**
	 * Método get do atributo item
	 * @return item - Long - representa o ID do objeto que será editado.
	 */
	public Long getItem() {
		return this.item;
	}

	/**
	 * Método set do atributo item
	 * @param item - Long - representa o ID do objeto que será editado.
	 */
	public void setItem(final Long item) {
		this.item = item;
	}

	/**
	 * Método get do atributo item.
	 * @return item - ApplicationItemDTO - item que será editado.
	 */
	public ApplicationItemDTO getAppItem() {
		return this.appItem;
	}

	/**
	 * Método set do atributo item.
	 * @param item - ApplicationItemDTO - item que será editado.
	 */
	public void setAppItem(final ApplicationItemDTO appItem) {
		this.appItem = appItem;
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
	 * Método get do atributo acessoStarteamButton.
	 * @return acessoStarteamButton - boolean - representa se existe acesso no starteam.
	 */
	public boolean isAcessoStarteamButton() {
		return this.acessoStarteamButton;
	}

	/**
	 * Método set do atributo acessoStarteamButton.
	 * @param acessoStarteamButton - boolean - representa se existe acesso no starteam.
	 */
	public void setAcessoStarteamButton(final boolean acessoStarteamButton) {
		this.acessoStarteamButton = acessoStarteamButton;
	}

	/**
	 * Método get do atributo ramoCriadoButton.
	 * @return ramoCriadoButton - boolean - representa se existe um ramo criado.
	 */
	public boolean isRamoCriadoButton() {
		return this.ramoCriadoButton;
	}

	/**
	 * Método set do atributo ramoCriadoButton.
	 * @param ramoCriadoButton - boolean - representa se existe um ramo criado.
	 */
	public void setRamoCriadoButton(final boolean ramoCriadoButton) {
		this.ramoCriadoButton = ramoCriadoButton;
	}

	/**
	 * Método get do atributo disponibilizadoButton.
	 * @return disponibilizadoButton - boolean - representa se a aplicação foi disponibilizada.
	 */
	public boolean isDisponibilizadoButton() {
		return this.disponibilizadoButton;
	}

	/**
	 * Método set do atributo disponibilizadoButton.
	 * @param disponibilizadoButton - boolean - representa se a aplicação foi disponibilizada.
	 */
	public void setDisponibilizadoButton(final boolean disponibilizadoButton) {
		this.disponibilizadoButton = disponibilizadoButton;
	}
	
}