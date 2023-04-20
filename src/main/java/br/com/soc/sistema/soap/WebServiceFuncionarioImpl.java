package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.funcionario.FuncionarioBusiness;
import br.com.soc.sistema.vo.FuncionarioVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceFuncionario" )
public class WebServiceFuncionarioImpl implements WebServiceFuncionario {

	private FuncionarioBusiness business;
	
	public WebServiceFuncionarioImpl() {
		this.business = new FuncionarioBusiness();
	}
	
	@Override
	/* Consulta de funcionário */
	public String buscarFuncionario(String codigo) {		
		return business.buscarFuncionarioPor(codigo).toString();
	}

	@Override
	/* Inserção de funcionário */
	public void inserirFuncionario(String nome) {
		FuncionarioVo funcionarioVo = new FuncionarioVo("", nome);
		business.inserirFuncionario(funcionarioVo);
	}

	@Override
	/* Edição de funcionário */
	public void editarFuncionario(String rowid, String nome) {
		FuncionarioVo funcionarioVo = new FuncionarioVo(rowid, nome);
		business.editarFuncionario(funcionarioVo);
	}

	@Override
	/* Exclusão de funcionário */
	public String excluirFuncionario(String codigo) {
		business.deletarFuncionario(Integer.parseInt(codigo));
		return "Funcionario de código " + codigo + " excluído com sucesso";
	}
}
