package br.com.soc.sistema.action.ficha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import br.com.soc.sistema.action.login.LoginAction;
import br.com.soc.sistema.business.exame.ExameBusiness;
import br.com.soc.sistema.business.ficha.FichaBusiness;
import br.com.soc.sistema.business.funcionario.FuncionarioBusiness;
import br.com.soc.sistema.filter.FichaFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFichas;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FichaVo;
import br.com.soc.sistema.vo.FuncionarioVo;

@SuppressWarnings("serial")
public class FichaAction extends Action implements ServletRequestAware {
	private List<FichaVo> fichas = new ArrayList<>();
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private List<ExameVo> exames = new ArrayList<>();
	private FichaBusiness business = new FichaBusiness();
	private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
	private final ExameBusiness exameBusiness = new ExameBusiness();
	private FichaFilter filtrar = new FichaFilter();
	private FichaVo fichaVo = new FichaVo();
	private LoginAction login = new LoginAction();
	
	private ArrayList<String> opcaoFuncionario = new ArrayList<>();
	private ArrayList<String> opcaoExame = new ArrayList<>();

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
	/*
	public String novo() {
		
		if (!login.usuarioExisteCookie(servletRequest)) {
			return LOGIN;
		}
		
		if (fichaVo.getData_exame() == null)
			return INPUT;

		funcionarios = funcionarioBusiness.trazerTodosOsFuncionarios();
		exames = exameBusiness.trazerTodosOsExames();
		business.inserirFicha(fichaVo);

		return REDIRECT;
	}
	*/
	public String novo() {
		if (opcaoFuncionario.isEmpty()) {
			funcionarios = funcionarioBusiness.trazerTodosOsFuncionarios();
			exames = exameBusiness.trazerTodosOsExames();
			return INPUT;
		} else {
			fichaVo.setId_funcionario(opcaoFuncionario.get(0));
			fichaVo.setId_exame(opcaoExame.get(0));
			business.inserirFicha(fichaVo);
			return REDIRECT;
		}
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
	
	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}
	
	public ArrayList<String> getOpcaoFuncionario() {
		return opcaoFuncionario;
	}

	public void setOpcaoFuncionario(ArrayList<String> opcaoFuncionario) {
		this.opcaoFuncionario = opcaoFuncionario;
	}

	public ArrayList<String> getOpcaoExame() {
		return opcaoExame;
	}

	public void setOpcaoExame(ArrayList<String> opcaoExame) {
		this.opcaoExame = opcaoExame;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
	}

}
