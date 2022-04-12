package br.com.wipro.beans;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Bean responsável pela tela de downloads.
 * @author brunoj - Bruno Alves Justino - Wipro
 */
@Named
@ViewScoped
public class DownloadsBean implements Serializable {

	/**
	 * Serial verison UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lista com os nomes dos documentos disponíveis para download.
	 */
	private List<String> docsNameList;
	
	/**
	 * Representa o arquivo que será baixado pelo usuário.
	 */
	private StreamedContent file;
	
	/**
	 * Representa o arquivo que será baixado pelo usuário.
	 */
	private StreamedContent file1;
	
	/**
	 * Representa o arquivo que será baixado pelo usuário.
	 */
	private StreamedContent file2;
	
	/**
	 * Representa o arquivo que será baixado pelo usuário.
	 */
	private StreamedContent file3;
	
	/**
	 * Representa o arquivo que será baixado pelo usuário.
	 */
	private StreamedContent file4;

	/**
	 * Nome do arquivo que deverá ser baixado.
	 */
	private String fileName;

	/**
	 * Construtor inicializando a lista de documentos disponíveis para download.
	 */
	public DownloadsBean() {
		this.docsNameList = new ArrayList<String>();
		this.docsNameList.add("Armazenamento de jar's no StarTeam_v1.1.pdf");
		this.docsNameList.add("JAAS - Manual do usuário_v1.1.pdf");
		this.docsNameList.add("Laudo de migração WASv85_cccc_v1.1.pdf");
		this.docsNameList.add("Padrões de nomenclatura WASv85 intranet_v1.0.pdf");
		this.docsNameList.add("Procedimentos de migração WASv85 intranet_v1.3.pdf");
	}
	
	/**
	 * Método get do atributo docsNameList.
	 * @return docsNameList - List<String> - lista com nome dos documentos.
	 */
	public List<String> getDocsNameList() {
		return this.docsNameList;
	}

	/**
	 * Método set do atributo docsNameList.
	 * @param docsNameList - List<String> - lista com nome dos documentos.
	 */
	public void setDocsNameList(final List<String> docsNameList) {
		this.docsNameList = docsNameList;
	}
	
	/**
	 * Método get do atributo fileName.
	 * @return fileName - String - representa o nome do arquivo.
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Método set do atributo fileName.
	 * @param fileName - String - representa o nome do arquivo.
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Método get do documento que será disponibilizado para download
	 * @return file - StreamedContent - arquivo para download.
	 */
    public StreamedContent getFile() {
    	InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/Procedimentos de migração WASv85 intranet_v1.3.pdf");
        this.file = new DefaultStreamedContent(stream, "application/pdf", "Procedimentos de migração WASv85 intranet_v1.3.pdf");
        return this.file;
    }
    
	/**
	 * Método get do documento que será disponibilizado para download
	 * @return file1 - StreamedContent - arquivo para download.
	 */
    public StreamedContent getFile1() {
    	InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/Padrões de nomenclatura WASv85 intranet_v1.0.pdf");
        this.file1 = new DefaultStreamedContent(stream, "application/pdf", "Padrões de nomenclatura WASv85 intranet_v1.0.pdf");
        return this.file1;
    }
    
	/**
	 * Método get do documento que será disponibilizado para download
	 * @return file2 - StreamedContent - arquivo para download.
	 */
    public StreamedContent getFile2() {
    	InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/Laudo de migração WASv85_cccc_v1.1.docx");
        this.file2 = new DefaultStreamedContent(stream, "application/docx", "Laudo de migração WASv85_cccc_v1.1.docx");
        return this.file2;
    }
    
	/**
	 * Método get do documento que será disponibilizado para download
	 * @return file3 - StreamedContent - arquivo para download.
	 */
    public StreamedContent getFile3() {
    	InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/JAAS - Manual do usuário_v1.1.pdf");
        this.file3 = new DefaultStreamedContent(stream, "application/pdf", "JAAS - Manual do usuário_v1.1.pdf");
        return this.file3;
    }
    
	/**
	 * Método get do documento que será disponibilizado para download
	 * @return file4 - StreamedContent - arquivo para download.
	 */
    public StreamedContent getFile4() {
    	InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/Armazenamento de jar's no StarTeam_v1.1.pdf");
        this.file4 = new DefaultStreamedContent(stream, "application/pdf", "Armazenamento de jar's no StarTeam_v1.1.pdf");
        return this.file4;
    }
	
}