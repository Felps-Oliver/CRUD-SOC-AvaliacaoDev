package br.com.soc.sistema.action.exame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import br.com.soc.sistema.action.login.LoginAction;
import br.com.soc.sistema.business.exame.ExameBusiness;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.ExameVo;

@SuppressWarnings("serial")
public class ExameAction extends Action implements ServletRequestAware {
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness business = new ExameBusiness();
	private ExameFilter filtrar = new ExameFilter();
	private ExameVo exameVo = new ExameVo();
	private LoginAction login = new LoginAction();

	protected HttpServletRequest servletRequest;

	/* Action para retornar todos os exames cadastrados	 */
	public String todos() {

		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}

		exames.addAll(business.trazerTodosOsExames());

		return SUCCESS;
	}

	/* Action para filtrar os exames */
	public String filtrar() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (filtrar.isNullOpcoesCombo())
			return REDIRECT;

		exames = business.filtrarExames(filtrar);

		return SUCCESS;
	}

	/* Action para inclusão de exame */
	public String novo() {
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (exameVo.getNome() == null)
			return INPUT;

		business.inserirExame(exameVo);

		return REDIRECT;
	}

	/* Action para edição de exame */
	public String editar() {
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (exameVo.getNome() == null)
			return EDIT;

		business.editarExame(exameVo);

		return REDIRECT;
	}

	/* Action para excluir exame */
	public String excluir() {

		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}

		business.deletarExame(Integer.parseInt(exameVo.getRowid()));

		return REDIRECT;
	}
	
	public List<OpcoesComboBuscarExames> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}

	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public ExameFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
	}
}
