package br.com.wipro.beans;

import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.wipro.application.ApplicationItemServiceImpl;
import br.com.wipro.components.ExcelReader;
import br.com.wipro.components.ExcelWriter;
import br.com.wipro.dto.ApplicationItemDTO;
import br.com.wipro.service.ApplicationItemService;

/**
 * Classe utilizada para import de uma planilha excel.
 * 
 * @author brunoj - Bruno Alves Justino
 *
 */
@Named
@ViewScoped
public class UploadXlsBean implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Serviço responsável pelas ações da tela. 
	 */
	private ApplicationItemService applicationItemService;

	/**
	 * Atributo para esconder o botão de upload enquanto o arquivo não for carregado
	 * corretamente.
	 */
	private boolean uploadButtomVisible;

	/**
	 * Representa a planilha em arquivo.
	 */
	private UploadedFile file;
	
	/**
	 * Representa o arquivo de Excel modelo de importação que pode ser baixado
	 */
	private StreamedContent downloadFile;
	
	/**
	 * Representa a planilha importada no sistema.
	 */
	private List<ArrayList<String>> planilha;
	
	/**
	 * Representa a planilha importada no sistema em forma de DTO.
	 */
	private List<ApplicationItemDTO> planilhaDTO;
	
	/**
	 * Construtor inicializando o serviço responsável pela inserção em massa.
	 */
	public UploadXlsBean() {
		this.applicationItemService = new ApplicationItemServiceImpl();
	}
	
	/**
	 * Método responsável pelo controle do evento após upload do arquivo selecionado
	 * em tela
	 * 
	 * @param event
	 *            - FileUploadEvent - Evento de upload.
	 */
	public void handleFileUpload(FileUploadEvent event) {
		this.file = event.getFile();
		if(this.file!=null) {
			this.uploadButtomVisible = true;
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Nenhum arquivo foi carregado.", "Certifique-se que a planilha Excel está no padrão modelo."));
			this.uploadButtomVisible = false;
		}
	}
	
	/**
	 * Método utilizado para mostrar o arquivo que será importado na tela.
	 * @return Clique em finalizar para importar o arquivo [nomeDoarquivo] - String - nome do arquivo que será importado.
	 */
	public String messagePreUpload() {
		return "Clique em finalizar para importar o arquivo "+this.file.getFileName();
	}

	/**
	 * Utilizado para finalizar o upload do arquivo carregado na view.
	 */
	public void upload() {
		try {
			new ExcelWriter(this.file); 
			this.planilha =	new ExcelReader("C:\\tmp\\" + this.file.getFileName()).getData();
			this.createDTOList();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Operação realizada com sucesso!", "A planilha foi importada corretamente."));
		}catch (Exception e ) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"O arquivo não pode ser carregado.", "Certifique-se que se trata de uma planilha Excel igual o modelo de importação disponibilizado."));
		}finally {
		    this.file = null;
			this.uploadButtomVisible = false;
		}
	}

	/**
	 * Responsável por criar a lista de DTO que será inserida na base de dados.
	 * @throws ParseException 
	 */
	private void createDTOList() {
		this.planilhaDTO = new ArrayList<ApplicationItemDTO>();
		for(int i = 1; i < this.planilha.size(); i++) {
			int coluna = 0;
			ApplicationItemDTO applicationItemDTO = new ApplicationItemDTO();
			applicationItemDTO.setStatus(this.planilha.get(i).get(coluna++));
			applicationItemDTO.setCentroCusto(this.planilha.get(i).get(coluna++));
			try {
				applicationItemDTO.setDataPrevistaTermino(new SimpleDateFormat("dd/MM/yyyy").parse(this.planilha.get(i).get(coluna++)));
			} catch (Exception e) {
				applicationItemDTO.setDataPrevistaTermino(null);
			}
			try {
				applicationItemDTO.setDataTermino(new SimpleDateFormat("dd/MM/yyyy").parse(this.planilha.get(i).get(coluna++)));
			} catch (Exception e) {
				applicationItemDTO.setDataTermino(null);
			}
			applicationItemDTO.setObservacao(this.planilha.get(i).get(coluna++));
			applicationItemDTO.setAcessoStarteam(this.planilha.get(i).get(coluna++));
			applicationItemDTO.setRamoCriado(this.planilha.get(i).get(coluna++));
			applicationItemDTO.setObsRamo(this.planilha.get(i).get(coluna++));
			applicationItemDTO.setDisponibilizado(this.planilha.get(i).get(coluna++));
			this.planilhaDTO.add(applicationItemDTO);
			this.applicationItemService.cadastrar(applicationItemDTO);
		}
	}

	/**
	 * Método get do atributo file.
	 * 
	 * @return file - UploadedFile - Representa a planilha carregada
	 */
	public UploadedFile getFile() {
		return this.file;
	}

	/**
	 * Método set do atributo file.
	 * 
	 * @param file
	 *            - UploadedFile - Representa a planilha carregada
	 */
	public void setFile(final UploadedFile file) {
		this.file = file;
	}

	/**
	 * Método get do atributo uploadButtomVisible.
	 * 
	 * @return uploadButtomVisible - boolean - representa se o botão de upload está
	 *         visível.
	 */
	public boolean isUploadButtomVisible() {
		return this.uploadButtomVisible;
	}

	/**
	 * Método set do atributo uploadButtomVisible.
	 * 
	 * @param uploadButtomVisible
	 *            - boolean - representa se o botão de upload está visível.
	 */
	public void setUploadButtomVisible(final boolean uploadButtomVisible) {
		this.uploadButtomVisible = uploadButtomVisible;
	}

	/**
	 * Método get do atributo planilha.
	 * @return planilha - List<ArrayList<String>> - planilha importada.
	 */
	public List<ArrayList<String>> getPlanilha() {
		return this.planilha;
	}

	/**
	 * Método set do atributo planilha.
	 * @param planilha - List<ArrayList<String>> - planilha importada.
	 */
	public void setPlanilha(final List<ArrayList<String>> planilha) {
		this.planilha = planilha;
	}

	/**
	 * Método get do atributo downloadFile.
	 * @return downloadFile - StreamedContent - planilha modelo para download.
	 */
	public StreamedContent getDownloadFile() {
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/xlsx/ModeloXlsx.xlsx");
		downloadFile = new DefaultStreamedContent(stream, "application/xlsx" ,"downloaded_modelo.xlsx");
		return this.downloadFile;
	}
	
}