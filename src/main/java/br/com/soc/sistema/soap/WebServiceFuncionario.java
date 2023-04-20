package br.com.soc.sistema.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceFuncionario {
	
	@WebMethod
	public String buscarFuncionario(String codigo);
	
	@WebMethod
	public void inserirFuncionario(String nome);
	
	@WebMethod
	public void editarFuncionario(String rowid, String nome);
	
	@WebMethod
	public String excluirFuncionario(String codigo);
	
}
