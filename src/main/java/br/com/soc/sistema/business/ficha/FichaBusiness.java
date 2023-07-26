package br.com.soc.sistema.business.ficha;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.ficha.FichaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FichaFilter;
import br.com.soc.sistema.vo.FichaVo;

public class FichaBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FichaDao dao;
	
	public FichaBusiness() {
		this.dao = new FichaDao();
	}
	
	/* Busca por todas as fichas cadastradas */
	public List<FichaVo> trazerTodasFichas(){
		return dao.findAllFichas();
	}
	
	/* Busca pelas fichas realizadas no período filtrado */
	public List<FichaVo> trazerFichasDoPeriodo(FichaVo fichaVo){
		return dao.findFichasDoPeriodo(fichaVo);
	}
	
	/* Busca pelos TOP 5 exames realizados no período */
	public List<FichaVo> trazerTOP5ExamesDoPeriodo(FichaVo fichaVo){
		return dao.findTopExames(fichaVo);
	}
	
	/* Busca a ficha pelo seu código */
	public FichaVo buscarFichaPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	/* Filtra as fichas com base na opção selecionada */
	public List<FichaVo> filtrarFicha(FichaFilter filter){
		List<FichaVo> fichas = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID_FICHA:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					fichas.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
			
			case NM_EXAME:
				fichas.addAll(dao.findAllByNomeExame(filter.getValorBusca()));
			break;

			case NM_FUNCIONARIO:
				fichas.addAll(dao.findAllByNomeFuncionario(filter.getValorBusca()));
			break;
		}
		
		return fichas;
	}
	
	/* Inserção de ficha */
	public void inserirFicha(FichaVo fichaVo) {
		try {
			if(fichaVo.getId_exame().isEmpty())
				throw new IllegalArgumentException("Exame nao pode ser em branco");
			
			if(fichaVo.getId_funcionario().isEmpty())
				throw new IllegalArgumentException("Funcionario nao pode ser em branco");
			
			if(fichaVo.getData_exame().isEmpty())
				throw new IllegalArgumentException("Data do exame nao pode ser em branco");
			
			dao.insertFicha(fichaVo);
		} catch (SQLIntegrityConstraintViolationException sqlexception) {
			throw new BusinessException("Ja existe uma ficha cadastrada com estes dados");
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
	}
	
	/* Edição de ficha */
	public void editarFicha(FichaVo fichaVo) {
		try {
			if(fichaVo.getId_exame().isEmpty())
				throw new IllegalArgumentException("Exame nao pode ser em branco");
			
			if(fichaVo.getId_funcionario().isEmpty())
				throw new IllegalArgumentException("Funcionario nao pode ser em branco");
			
			if (fichaVo.getData_exame().isEmpty())
				throw new IllegalArgumentException("Data nao pode ser em branco");

			dao.updateFicha(fichaVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a edicao do registro");
		}
	}
	
	/* Deleção de ficha */
	public void deletarFicha(Integer id_ficha) {
		try {
			dao.deleteFicha(id_ficha);
		}
		catch (BusinessException e) {
			throw new BusinessException("Impossível excluir ficha realizado para o funcionario");
		}
	}
	
}
