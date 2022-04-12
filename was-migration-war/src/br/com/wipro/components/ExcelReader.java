package br.com.wipro.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Classe utilizada para ler o arquivo excel.
 * 
 * @author brunoj - Bruno Alves Justino.
 */
@SuppressWarnings("resource")
public class ExcelReader {

	/**
	 * Representa o path + o nome do arquivo xls que deverá ser aberto.
	 */
	private String pathFileName;

	/**
	 * Representa a planilha após importada.
	 */
	private List<ArrayList<String>> planilha;

	/**
	 * Representa uma coluna.
	 */
	private ArrayList<String> colunas;

	/**
	 * Construtor declarado recebendo o path+nome do arquivo à ser aberto.
	 * 
	 * @param path
	 *            - String - path+nome do arquivo que deverá ser aberto.
	 * @throws IOException
	 */
	public ExcelReader(String path) throws IOException {
		this.pathFileName = path;
		if (this.xlsIsOldFormat()) {
			this.oldReader();
		} else {
			this.newReader();
		}
	}

	/**
	 * Construtor declarado recebendo o path+nome do arquivo à ser aberto.
	 * 
	 * @param path
	 *            - String - path+nome do arquivo que deverá ser aberto.
	 * @throws IOException
	 */
	public ExcelReader(File file) throws IOException {
		this.pathFileName = file.getName();
		if (this.xlsIsOldFormat()) {
			this.oldReader();
		} else {
			this.newReader();
		}
	}

	/**
	 * Utilizado para definir a extensão do arquivo que será aberto.
	 * 
	 * @return true - boolean - retorna true caso o formato do arquivo for xls.
	 */
	private boolean xlsIsOldFormat() {
		if (this.pathFileName.endsWith(".xls")) {
			return true;
		}
		return false;
	}

	/**
	 * Utilizado para ler arquivos com a extensão .xlsx
	 * @throws IOException 
	 */
	private void newReader() throws IOException {
		this.planilha = new ArrayList<ArrayList<String>>();
		FileInputStream arquivo = new FileInputStream(new File(this.pathFileName));
		XSSFWorkbook workbook = new XSSFWorkbook(arquivo);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			this.colunas = new ArrayList<String>();
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				try {
					this.colunas.add(cell.getStringCellValue());
				}catch(IllegalStateException e) {
					this.colunas.add(String.valueOf(cell.getNumericCellValue()));
				}
			}
			this.planilha.add(this.colunas);
		}
		arquivo.close();
	}

	/**
	 * Utilizado para ler arquivos com a extensão .xls
	 * 
	 * @throws IOException
	 */
	private void oldReader() throws IOException {
		this.planilha = new ArrayList<ArrayList<String>>();
		FileInputStream arquivo = new FileInputStream(new File(this.pathFileName));
		HSSFWorkbook workbook = new HSSFWorkbook(arquivo);
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			this.colunas = new ArrayList<String>();
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				try {
					this.colunas.add(cell.getStringCellValue());
				}catch(IllegalStateException e) {
					this.colunas.add(String.valueOf(cell.getNumericCellValue()));
				}
			}
			this.planilha.add(this.colunas);
		}
		arquivo.close();
	}

	/**
	 * Método utilizado para retornar a planilha no formato List<List<String>>.
	 * 
	 * @return planilha - List<ArrayList<String>> - representa a planilha importada
	 */
	public List<ArrayList<String>> getData() {
		for (int i = 0; i < this.planilha.size(); i++) {
			for (int x = 0; x < this.planilha.get(0).size(); x++) {
//				System.out.println(this.planilha.get(i).get(x) + "\t");
			}
		}
		return this.planilha;
	}

	/**
	 * Método get do atributo colunas.
	 * 
	 * @return colunas - List<String> - representa as linhas lidas da planilha.
	 */
	public List<String> getColunas() {
		return this.colunas;
	}

}