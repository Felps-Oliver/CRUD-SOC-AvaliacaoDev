package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.exame.ExameBusiness;
import br.com.soc.sistema.vo.ExameVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceExames" )
public class WebServiceExamesImpl implements WebServiceExames {

	private ExameBusiness business;
	
	public WebServiceExamesImpl() {
		this.business = new ExameBusiness();
	}
	
	@Override
	/* Consulta exame */
	public String buscarExame(String codigo) {		
		return business.buscarExamePor(codigo).toString();
	}

	@Override
	/* Inserção de exame */
	public void inserirExame(String nome) {
		ExameVo exameVo = new ExameVo("", nome);
		business.inserirExame(exameVo);
	}
	
	@Override
	/* Edição de exame */
	public void editarExame(String rowid, String nome) {
		ExameVo exameVo = new ExameVo(rowid, nome);
		business.editarExame(exameVo);
	}

	@Override
	/* Exclusão de exame */
	public String excluirExame(String codigo) {
		business.deletarExame(Integer.parseInt(codigo));
		return "Exame de código " + codigo + " excluído com sucesso";
	}
}
