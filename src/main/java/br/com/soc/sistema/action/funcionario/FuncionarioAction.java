package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import br.com.soc.sistema.action.login.LoginAction;
import br.com.soc.sistema.business.funcionario.FuncionarioBusiness;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;

@SuppressWarnings("serial")
public class FuncionarioAction extends Action implements ServletRequestAware {
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	private LoginAction login = new LoginAction();
	
	protected HttpServletRequest servletRequest;

	/* Action para listagem dos funcionários cadastrados */
	public String todos() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		funcionarios.addAll(business.trazerTodosOsFuncionarios());	
		
		return SUCCESS;
	}
	
	/* Action para filtrar os funcionários */
	public String filtrar() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (filtrar.isNullOpcoesCombo())
			return REDIRECT;

		funcionarios = business.filtrarFuncionario(filtrar);

		return SUCCESS;
	}
	
	/* Action para inclusão de funcionário */
	public String novo() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if(funcionarioVo.getNome() == null)
			return INPUT;
		
		business.inserirFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	/* Action para edição de funcionário */
	public String editar() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if(funcionarioVo.getNome() == null)
			return EDIT;
		
		business.editarFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	/* Action para exclusão de funcionário */
	public String excluir() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}

		business.deletarFuncionario(Integer.parseInt(funcionarioVo.getRowid()));
		
		return REDIRECT;
	}
	
	public List<OpcoesComboBuscarFuncionarios> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarFuncionarios.values());
	}
	
	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
	}
}
