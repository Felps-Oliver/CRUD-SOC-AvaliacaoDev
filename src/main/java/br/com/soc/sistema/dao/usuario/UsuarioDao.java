package br.com.soc.sistema.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.UsuarioVo;

public class UsuarioDao extends Dao{

	/* Efetua validação dos dados do usuário */
	public boolean validaLogin(UsuarioVo usuarioVo){
		StringBuilder query = new StringBuilder("SELECT login, password FROM usuario ")
								.append("WHERE login = ? AND password = ?");
		
		boolean usuarioExiste = false;
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setString(i++, usuarioVo.getLogin());
			ps.setString(i, usuarioVo.getPassword());
			
			ResultSet rs = ps.executeQuery();  
			usuarioExiste = rs.next();
			   
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return usuarioExiste;
	}
}
