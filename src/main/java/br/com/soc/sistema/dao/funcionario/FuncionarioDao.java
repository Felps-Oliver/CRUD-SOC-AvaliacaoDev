package br.com.soc.sistema.dao.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioDao extends Dao {
	
	/* Consulta por todos os funcionários cadastrados */
	public List<FuncionarioVo> findAllFuncionarios(){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			FuncionarioVo vo =  null;
			List<FuncionarioVo> funcionarios = new ArrayList<>();
			while (rs.next()) {
				vo = new FuncionarioVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));	
				
				funcionarios.add(vo);
			}
			return funcionarios;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	/* Consulta por funcionário filtrando pelo nome */
	public List<FuncionarioVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario "
								+ "WHERE lower(nm_funcionario) LIKE lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				FuncionarioVo vo =  null;
				List<FuncionarioVo> funcionarios = new ArrayList<>();
				
				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
					
					funcionarios.add(vo);
				}
				return funcionarios;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	/* Consulta funcionário filtrando pelo código */
	public FuncionarioVo findByCodigo(Integer codigo) {//throws SQLException{
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario "
				+ "WHERE rowid = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				FuncionarioVo vo =  null;
				
//				if (rs == null)
//					throw new SQLException();
				
				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
				}
				return vo;
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
			//throw e;
		}		
		return null;
	}
	
	/* Insere funcionário*/
	public void insertFuncionario(FuncionarioVo funcionarioVo){
		StringBuilder query = new StringBuilder("INSERT INTO funcionario (nm_funcionario) values (?)");
		
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setString(i, funcionarioVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Edição de funcionário */
	public void updateFuncionario(FuncionarioVo funcionarioVo) throws SQLException {
		StringBuilder query = new StringBuilder("UPDATE funcionario SET nm_funcionario = ? WHERE rowid = ?");
		
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i = 1;
			
			ps.setString(i++, funcionarioVo.getNome());
			ps.setInt(i, Integer.parseInt(funcionarioVo.getRowid()));
			
			if (ps.executeUpdate() == 0)
				throw new SQLException("Codigo do funcionario nao encontrado");
			
		}catch (SQLException e) {
			throw e;
			//e.printStackTrace();
		}
	}
	
	/* Deleção de funcionário.
	 * Ao deletar um funcionário, seus exames realizados também são deletados,
	 * conforme parametrização de 'DELETE ON CASCADE' na tabela */
	public void deleteFuncionario(Integer rowid) throws SQLException {
		StringBuilder query = new StringBuilder("DELETE FROM funcionario WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			
			ps.setInt(1, rowid);
			
			if (ps.executeUpdate() == 0)
				throw new SQLException();
			
		}catch (SQLException e) {
			throw e;
		}
		
	}
}