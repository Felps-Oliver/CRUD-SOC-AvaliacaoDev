package br.com.soc.sistema.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceExames {
	
	@WebMethod
	public String buscarExame(String codigo);
	
	@WebMethod
	public void inserirExame(String nome);
	
	@WebMethod
	public void editarExame(String rowid, String nome);
	
	@WebMethod
	public String excluirExame(String codigo);
	
}
