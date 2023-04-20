package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.ficha.FichaBusiness;
import br.com.soc.sistema.vo.FichaVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceFicha" )
public class WebServiceFichaImpl implements WebServiceFicha {

	private FichaBusiness business;
	
	public WebServiceFichaImpl() {
		this.business = new FichaBusiness();
	}

	@Override
	/* Consulta de ficha */
	public String buscarFicha(String codigo) {
		return business.buscarFichaPor(codigo).toString();
	}

	@Override
	/* Inserção de ficha */
	public void inserirFicha(String id_funcionario, String id_exame, String data_exame) {
		FichaVo fichaVo = new FichaVo("", id_funcionario, id_exame, data_exame);
		business.inserirFicha(fichaVo);
	}

	@Override
	/* Edição de ficha */
	public void editarFicha(String id_ficha, String id_funcionario, String id_exame, String data_exame) {
		FichaVo fichaVo = new FichaVo(id_ficha, id_funcionario, id_exame, data_exame);
		business.editarFicha(fichaVo);
	}

	@Override
	/* Exclusão de ficha */
	public String excluirFicha(String codigo) {
		business.deletarFicha(Integer.parseInt(codigo));
		return "Ficha de código " + codigo + " excluído com sucesso";
	}
}
