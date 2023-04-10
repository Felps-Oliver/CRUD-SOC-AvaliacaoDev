package br.com.soc.sistema.action.ficha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import br.com.soc.sistema.action.login.LoginAction;
import br.com.soc.sistema.business.ficha.FichaBusiness;
import br.com.soc.sistema.filter.FichaFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFichas;
import br.com.soc.sistema.vo.FichaVo;

@SuppressWarnings("serial")
public class FichaAction extends Action implements ServletRequestAware {
	private List<FichaVo> fichas = new ArrayList<>();
	private FichaBusiness business = new FichaBusiness();
	private FichaFilter filtrar = new FichaFilter();
	private FichaVo fichaVo = new FichaVo();
	private LoginAction login = new LoginAction();
	
	protected HttpServletRequest servletRequest;

	/* Action para Relatório de Exames Realizados no Período*/
	public String relatorioRealizadosPeriodo() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}

		if (fichaVo.getData_inicio() == null || fichaVo.getData_fim() == null)
			return REPORT;

		fichas.addAll(business.trazerFichasDoPeriodo(fichaVo));

		return REPORT;
	}

	/* Action para Relatório de TOP 5 Exames realizados no período filtrado */
	public String relatorioTOPRealizados() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (fichaVo.getData_inicio() == null || fichaVo.getData_fim() == null)
			return REPORT_TOPEXAMES;

		fichas = business.trazerTOP5ExamesDoPeriodo(fichaVo);

		return REPORT_TOPEXAMES;
	}

	/* Action para listar todas as fichas do período */
	public String todos() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		fichas = business.trazerTodasFichas();

		return SUCCESS;
	}
	
	/* Action para filtrar as fichas */
	public String filtrar() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (filtrar.isNullOpcoesCombo())
			return REDIRECT;

		fichas = business.filtrarFicha(filtrar);

		return SUCCESS;
	}

	/* Action para inclusão de ficha */
	
	public String novo() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (fichaVo.getData_exame() == null)
			return INPUT;

		business.inserirFicha(fichaVo);

		return REDIRECT;
	}

	/* Action para edição de ficha */
	public String editar() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (fichaVo.getData_exame() == null)
			return EDIT;

		business.editarFicha(fichaVo);

		return REDIRECT;
	}

	/* Action para exclusão de ficha */
	public String excluir() {

		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		business.deletarFicha(Integer.parseInt(fichaVo.getId_ficha()));

		return REDIRECT;
	}

	public List<OpcoesComboBuscarFichas> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarFichas.values());
	}

	public List<FichaVo> getFichas() {
		return fichas;
	}

	public void setFichas(List<FichaVo> fichas) {
		this.fichas = fichas;
	}

	public FichaFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FichaFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FichaVo getFichaVo() {
		return fichaVo;
	}

	public void setFichaVo(FichaVo fichaVo) {
		this.fichaVo = fichaVo;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
	}

}
