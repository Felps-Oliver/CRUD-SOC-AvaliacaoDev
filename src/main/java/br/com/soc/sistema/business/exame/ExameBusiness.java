package br.com.soc.sistema.business.exame;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.ExameVo;

public class ExameBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameDao dao;
	
	public ExameBusiness() {
		this.dao = new ExameDao();
	}
	
	public List<ExameVo> trazerTodosOsExames(){
		return dao.findAllExames();
	}
	
	/* Busca exame pelo seu código */
	public ExameVo buscarExamePor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	/* Filtra exame com base na opção selecionada */
	public List<ExameVo> filtrarExames(ExameFilter filter){
		List<ExameVo> exames = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exames.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				exames.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return exames;
	}
	
	/* Inserção do exame */
	public void inserirExame(ExameVo exameVo) {
		try {
			if(exameVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome do exame nao pode ser em branco");
			
			dao.insertExame(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	/* Edição do exame */
	public void editarExame(ExameVo exameVo) {
		try {
			if (exameVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");

			dao.updateExame(exameVo);
			
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a edicao do registro");
		}
	}

	/* Deleção do exame */
	public void deletarExame(Integer rowid) {
		try {
			dao.deleteExame(rowid);
		}
		catch (SQLIntegrityConstraintViolationException e) {
			throw new BusinessException("Impossível excluir exame já realizado por um funcionário");
		}
		catch (BusinessException | SQLException e) {
			throw new BusinessException("Impossível excluir exame");
		}
	}
}
