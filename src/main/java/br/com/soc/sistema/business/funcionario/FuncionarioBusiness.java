package br.com.soc.sistema.business.funcionario;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.funcionario.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;
	
	public FuncionarioBusiness() {
		this.dao = new FuncionarioDao();
	}
	
	/* Busca por todos os funcionários cadastrados */
	public List<FuncionarioVo> trazerTodosOsFuncionarios(){
		return dao.findAllFuncionarios();
	}
	
	/* Busca funcionário com base em seu código */
	public FuncionarioVo buscarFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	/* Busca funcionário com base na opção selecionada */
	public List<FuncionarioVo> filtrarFuncionario(FuncionarioFilter filter){
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					funcionarios.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				funcionarios.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return funcionarios;
	}
	
	/* Inserção de funcionário */
	public void inserirFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.insertFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
	}
	
	/* Edição de funcionário */
	public void editarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if (funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");

			dao.updateFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
	}
	
	/* Deleção de funcionário */
	public void deletarFuncionario(Integer rowid) {
		try {
			dao.deleteFuncionario(rowid);
		}
		catch (BusinessException e) {
			throw new BusinessException("Impossível excluir exame");
		}
	}
	
}
