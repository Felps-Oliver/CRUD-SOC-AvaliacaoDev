package br.com.soc.sistema.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceFicha {
	
	@WebMethod
	public String buscarFicha(String codigo);
	
	@WebMethod
	public void inserirFicha(String id_funcionario, String id_exame, String data_exame);
	
	@WebMethod
	public void editarFicha(String id_ficha, String id_funcionario, String id_exame, String data_exame);
	
	@WebMethod
	public String excluirFicha(String codigo);
	
}
