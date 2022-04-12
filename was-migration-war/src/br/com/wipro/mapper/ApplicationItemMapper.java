package br.com.wipro.mapper;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.wipro.dto.ApplicationItemDTO;
import br.com.wipro.enums.ApplicationItemEnum;
import br.com.wipro.model.ApplicationItem;

/**
 * Classe utilizada para transformar um determinado objeto em objetoDTO e também
 * o contrário.
 * 
 * @author Bruno Justino
 */
public class ApplicationItemMapper implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Converte uma lista de entidades em uma lista de DTO
	 * 
	 * @param entityList
	 *            - List<ApplicationItem> - lista de entidades
	 * @return dtoList - List<ApplicationItemDTO> - lista de DTO (Data Table Object)
	 */
	public List<ApplicationItemDTO> convertListToDTO(List<ApplicationItem> entityList) {
		List<ApplicationItemDTO> dtoList = new ArrayList<ApplicationItemDTO>();
		for (ApplicationItem auxObject : entityList) {
			ApplicationItemDTO dtoObject = new ApplicationItemDTO();
			dtoObject.setAcessoStarteam(auxObject.getAcessoStarteam() == true ? "Sim" : "Não");
			dtoObject.setCentroCusto(auxObject.getCentroCusto());
			if (auxObject.getDataPrevistaTermino() != null) {
				dtoObject.setDataPrevistaTermino(auxObject.getDataPrevistaTermino());
			}
			if (auxObject.getDataTermino() != null) {
				dtoObject.setDataTermino(auxObject.getDataTermino());
			}
			dtoObject.setDisponibilizado(auxObject.getDisponibilizado() == true ? "Sim" : "Não");
			dtoObject.setIdNumber(auxObject.getIdNumber());
			if (auxObject.getObservacao() == null) {
				dtoObject.setObservacao("Nenhuma observação à ser exibida.");
			} else {
				dtoObject.setObservacao(auxObject.getObservacao());
			}
			if (auxObject.getObsRamo() == null) {
				dtoObject.setObsRamo("Nenhuma observação à ser exibida.");
			} else {
				dtoObject.setObsRamo(auxObject.getObsRamo());
			}
			dtoObject.setRamoCriado(auxObject.getRamoCriado() == true ? "Sim" : "Não");
			dtoObject.setStatus(auxObject.getStatus().toString());
			dtoList.add(dtoObject);
		}
		return dtoList;
	}

	/**
	 * Transforma um determinada entidade em DTO.
	 * 
	 * @param item
	 *            - ApplicationItem - Entidade que deverá ser transformada em DTO.
	 * @return applicationItemDTO - DTO pronto para ser exibido em tela.
	 */
	public ApplicationItemDTO convertModelToDTO(ApplicationItem item) {
		ApplicationItemDTO applicationItemDTO = new ApplicationItemDTO();

		return applicationItemDTO;
	}

	/**
	 * Transforma um determinado DTO em uma entidade (utilizado para incluir novo
	 * aplicativo no sistema).
	 * 
	 * @param item
	 *            - ApplicationItemDTO - DTO que deverá ser transformado em
	 *            entidade.
	 * @return applicationItem - entidade pronta para inclusão.
	 * @throws ParseException
	 *             - caso de erro na conversão da data de término prevista
	 */
	public ApplicationItem convertDTOToModel(ApplicationItemDTO item) {
		ApplicationItem applicationItem = new ApplicationItem();
		applicationItem.setCentroCusto(item.getCentroCusto());
		applicationItem.setAtualizacao(new Date());
		applicationItem.setDataCriacao(new Date());
		applicationItem.setStatus(ApplicationItemEnum.valueOf(item.getStatus()));
		applicationItem.setUsuarioCriacao("brunojustino888");
		applicationItem.setUsuarioUltimaAtualizacao("brunojustino888");
		if (item.getAcessoStarteam().equals("Sim")) {
			applicationItem.setAcessoStarteam(true);
		} else {
			applicationItem.setAcessoStarteam(false);
		}
		if (item.getRamoCriado().equals("Sim")) {
			applicationItem.setRamoCriado(true);
		} else {
			applicationItem.setRamoCriado(false);
		}
		if (item.getDisponibilizado().equals("Sim")) {
			applicationItem.setDisponibilizado(true);
		} else {
			applicationItem.setDisponibilizado(false);
		}
		if (item.getDataPrevistaTermino() != null) {
			applicationItem.setDataPrevistaTermino(item.getDataPrevistaTermino());
		}
		if (item.getDataTermino() != null) {
			applicationItem.setDataTermino(item.getDataTermino());
		}

		applicationItem.setObservacao(item.getObservacao());
		applicationItem.setObsRamo(item.getObsRamo());

		return applicationItem;
	}

	/**
	 * Sobrecarga de método para transformar um determinado DTO em uma entidade
	 * (utilizado para incluir novo aplicativo no sistema). A sobrecarga se faz
	 * necessária para a edição de um determinado registro não duplicá-lo na base de
	 * dados.
	 * 
	 * @param item
	 *            - ApplicationItemDTO - DTO que deverá ser transformado em
	 *            entidade.
	 * @param applicationItem
	 *            - ApplicationItem - entidade que deverá ser persistida.
	 * @return applicationItem - entidade pronta para inclusão.
	 * @throws ParseException
	 *             - caso de erro na conversão da data de término prevista
	 */
	public ApplicationItem convertDTOToModel(ApplicationItemDTO item, ApplicationItem applicationItem) {
		applicationItem.setStatus(ApplicationItemEnum.valueOf(item.getStatus()));
		applicationItem.setCentroCusto(item.getCentroCusto());
		applicationItem.setAtualizacao(new Date());
		applicationItem.setUsuarioUltimaAtualizacao("brunojustino888");
		if (item.getAcessoStarteam().equals("Sim")) {
			applicationItem.setAcessoStarteam(true);
		} else {
			applicationItem.setAcessoStarteam(false);
		}
		if (item.getRamoCriado().equals("Sim")) {
			applicationItem.setRamoCriado(true);
		} else {
			applicationItem.setRamoCriado(false);
		}
		if (item.getDisponibilizado().equals("Sim")) {
			applicationItem.setDisponibilizado(true);
		} else {
			applicationItem.setDisponibilizado(false);
		}
		if (item.getDataPrevistaTermino() != null) {
			applicationItem.setDataPrevistaTermino(item.getDataPrevistaTermino());
		}
		if (item.getDataTermino() != null) {
			applicationItem.setDataTermino(item.getDataTermino());
		}
		applicationItem.setObservacao(item.getObservacao());
		applicationItem.setObsRamo(item.getObsRamo());
		return applicationItem;
	}

}