/**
 * 
 */
package br.com.wipro.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

/**
 * Responsável por criar e escrever o conteúdo de um determinado arquivo Excel.
 * @author brunoj - Bruno Alves Justino
 */
public class ExcelWriter {
	
	/**
	 * Representa o path + o nome do arquivo xls que deverá ser aberto.
	 */
	private String pathFileName;
	
	/**
	 * Representa a planilha em arquivo.
	 */
	private UploadedFile uploadedFile;
	
	/**
	 * Construtor preparado para criar o arquivo Excel xls.
	 * @param uploaded - UploadedFile - arquivo provido de upload.
	 */
	public ExcelWriter(UploadedFile uploaded) {
		this.uploadedFile = uploaded;
		this.pathFileName = "C:\\tmp\\" + this.uploadedFile.getFileName();
		File file = null;
		try {
			file = new File(pathFileName);
			if(file.exists()) {
				file.delete();
			} 
			file = new File(this.pathFileName);
			file.createNewFile(); 
			if(this.xlsIsOldFormat()) {
				this.oldWriter(file);
			}else {
				this.newWriter(file);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Utilizado para criar uma planilha xls.
	 * @param file - File - arquivo.
	 */
	private void oldWriter(File file) {
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createSheet("Migração WAS v8.5");
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(this.uploadedFile.getContents());
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
               System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
               System.out.println("Erro na edição do arquivo!");
        }
	}

	/**
	 * Utilizado para criar uma planilha xlsx.
	 * @param file - File - arquivo.
	 */
	private void newWriter(File file) {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		workbook.createSheet("Migração WAS v8.5");
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(this.uploadedFile.getContents());
            workbook.setActiveSheet(0);
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
               System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
               System.out.println("Erro na edição do arquivo!");
        }
	}
	
	/**
	 * Utilizado para definir a extensão do arquivo que será aberto.
	 * @return true - boolean - retorna true caso o formato do arquivo for xls.
	 */
	private boolean xlsIsOldFormat() {
		if(this.uploadedFile.getFileName().endsWith(".xls")) {
			return true;
		}
		return false;
	}
	
}