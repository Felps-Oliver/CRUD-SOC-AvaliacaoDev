package br.com.soc.sistema.business.usuario;

import br.com.soc.sistema.dao.usuario.UsuarioDao;
import br.com.soc.sistema.vo.UsuarioVo;

public class UsuarioBusiness {

	private UsuarioDao dao;
	
	public UsuarioBusiness() {
		this.dao = new UsuarioDao();
	}
	
	//Valida no banco se há usuário cadastrado com o login e senha informado
	public boolean validaUsuario(UsuarioVo usuarioVo) {
		if(dao.validaLogin(usuarioVo)) {
			return true;
		}
		return false;
	}
	
}
