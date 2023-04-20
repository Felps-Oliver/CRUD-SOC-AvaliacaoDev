package br.com.soc.sistema.dao.ficha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.FichaVo;

public class FichaDao extends Dao {
	
	//Permite retornar os dados no formato de data especificado
	SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");

	/* Consulta pelas fichas do período selecionado */
	public List<FichaVo> findFichasDoPeriodo(FichaVo fichaVo) {
		StringBuilder query = new StringBuilder(
				"SELECT id_funcionario idfuncionario, nm_funcionario funcionario, id_exame idexame, nm_exame exame, data_exame data "
						+ "FROM ficha LEFT JOIN exame ON ficha.id_exame = exame.rowid "
						+ "LEFT JOIN funcionario ON ficha.id_funcionario = funcionario.rowid "
						+ "WHERE data_exame BETWEEN ? AND ? ");
		
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());) {

			FichaVo vo = null;
			List<FichaVo> fichas = new ArrayList<>();

			int i = 1;
			ps.setDate(i++, Date.valueOf(fichaVo.getData_inicio()));
			ps.setDate(i, Date.valueOf(fichaVo.getData_fim()));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				vo = new FichaVo();
				vo.setId_funcionario(rs.getString("idfuncionario"));
				vo.setNome_funcionario(rs.getString("funcionario"));
				vo.setId_exame(rs.getString("idexame"));
				vo.setNome_exame(rs.getString("exame"));
				vo.setData_exame(sdt.format(rs.getDate("data")));

				fichas.add(vo);
			}
			return fichas;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	/* Consulta ficha pelo código */
	public FichaVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder(
				"SELECT id_ficha idficha, nm_exame exame, nm_funcionario funcionario, data_exame data "
						+ "FROM ficha LEFT JOIN exame ON ficha.id_exame = exame.rowid "
						+ "LEFT JOIN funcionario ON ficha.id_funcionario = funcionario.rowid " + "WHERE id_ficha = ? ");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			ps.setInt(1, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				FichaVo vo = null;
				
				while (rs.next()) {
					vo = new FichaVo();
					vo.setId_ficha(rs.getString("idficha"));
					vo.setNome_funcionario(rs.getString("funcionario"));
					vo.setNome_exame(rs.getString("exame"));
					vo.setData_exame(sdt.format(rs.getDate("data")));
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Consulta as fichas filtrando pelo nome do exame */
	public List<FichaVo> findAllByNomeExame(String nome) {
		StringBuilder query = new StringBuilder(
				"SELECT id_ficha idficha, nm_exame exame, nm_funcionario funcionario, data_exame data "
						+ "FROM ficha LEFT JOIN exame ON ficha.id_exame = exame.rowid "
						+ "LEFT JOIN funcionario ON ficha.id_funcionario = funcionario.rowid "
						+ "WHERE lower(nm_exame) LIKE lower(?)");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			ps.setString(1, "%"+nome+"%");

			try (ResultSet rs = ps.executeQuery()) {
				FichaVo vo = null;
				List<FichaVo> fichas = new ArrayList<>();
				
				while (rs.next()) {
					vo = new FichaVo();
					vo.setId_ficha(rs.getString("idficha"));
					vo.setNome_funcionario(rs.getString("funcionario"));
					vo.setNome_exame(rs.getString("exame"));
					vo.setData_exame(sdt.format(rs.getDate("data")));

					fichas.add(vo);
				}
				return fichas;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	/* Consulta as fichas filtrando pelo nome do funcionário */
	public List<FichaVo> findAllByNomeFuncionario(String nome) {
		StringBuilder query = new StringBuilder(
				"SELECT id_ficha idficha, nm_exame exame, nm_funcionario funcionario, data_exame data "
						+ "FROM ficha LEFT JOIN exame ON ficha.id_exame = exame.rowid "
						+ "LEFT JOIN funcionario ON ficha.id_funcionario = funcionario.rowid "
						+ "WHERE lower(nm_funcionario) LIKE lower(?)");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setString(i, "%"+nome+"%");

			try (ResultSet rs = ps.executeQuery()) {
				FichaVo vo = null;
				List<FichaVo> fichas = new ArrayList<>();
				
				while (rs.next()) {
					vo = new FichaVo();
					vo.setId_ficha(rs.getString("idficha"));
					vo.setNome_funcionario(rs.getString("funcionario"));
					vo.setNome_exame(rs.getString("exame"));
					vo.setData_exame(sdt.format(rs.getDate("data")));

					fichas.add(vo);
				}
				return fichas;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	/* Consulta por todas as fichas cadastradas */
	public List<FichaVo> findAllFichas() {
		StringBuilder query = new StringBuilder(
				"SELECT id_ficha idficha, nm_exame exame, nm_funcionario funcionario, data_exame data "
				+ "FROM ficha LEFT JOIN exame ON ficha.id_exame = exame.rowid "
				+ "LEFT JOIN funcionario ON ficha.id_funcionario = funcionario.rowid ORDER BY idficha");
		
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			FichaVo vo = null;
			List<FichaVo> fichas = new ArrayList<>();
			
			while (rs.next()) {
				vo = new FichaVo();
				vo.setId_ficha(rs.getString("idficha"));
				vo.setNome_exame(rs.getString("exame"));
				vo.setNome_funcionario(rs.getString("funcionario"));
				
				//Chama a var sdt e parametriza para retornar o dato em forma de Date
				vo.setData_exame(sdt.format(rs.getDate("data")));

				fichas.add(vo);
			}
			return fichas;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	/* Consulta pelos 5 exames mais realizados do período */
	public List<FichaVo> findTopExames(FichaVo fichaVo) {
		StringBuilder query = new StringBuilder("SELECT id_exame idexame, count(id_exame) totalExame, nm_exame exame "
				+ "FROM ficha LEFT JOIN exame ON exame.rowid = ficha.id_exame "
				+ "WHERE data_exame BETWEEN ? AND ? GROUP BY id_exame "
				+ "ORDER BY count(id_exame) DESC LIMIT 5;");
		
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());) {

			FichaVo vo = null;
			List<FichaVo> fichas = new ArrayList<>();

			int i = 1;
			ps.setDate(i++, Date.valueOf(fichaVo.getData_inicio()));
			ps.setDate(i, Date.valueOf(fichaVo.getData_fim()));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				vo = new FichaVo();
				vo.setId_exame(rs.getString("idexame"));
				vo.setTotal_exame(rs.getString("totalExame"));
				vo.setNome_exame(rs.getString("exame"));

				fichas.add(vo);
			}
			return fichas;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	// Tenta efetuar o INSERT e, caso haja duplicidade na chave primária composta
	// (ou seja, já exista uma ficha com [id_exame, id_funcionario, data_exame] na
	// tabela, lança a exceção SQLIntegrityConstraintViolationException
	public void insertFicha(FichaVo fichaVo) throws SQLIntegrityConstraintViolationException {
		StringBuilder query = new StringBuilder(
				"INSERT INTO ficha (id_funcionario, id_exame, data_exame) values (?, ?, ?)");

		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());) {

			int i = 1;
			ps.setInt(i++, Integer.parseInt(fichaVo.getId_funcionario()));
			ps.setInt(i++, Integer.parseInt(fichaVo.getId_exame()));
			ps.setDate(i, Date.valueOf(fichaVo.getData_exame()));
			ps.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			throw new SQLIntegrityConstraintViolationException(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Edição de dados da ficha */
	public void updateFicha(FichaVo fichaVo) {
		StringBuilder query = new StringBuilder("UPDATE ficha SET id_funcionario = ?, id_exame = ?, "
				+ "data_exame = ? WHERE id_ficha = ?");
		
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;
			ps.setInt(i++, Integer.parseInt(fichaVo.getId_funcionario()));
			ps.setInt(i++, Integer.parseInt(fichaVo.getId_exame()));
			ps.setDate(i++, Date.valueOf(fichaVo.getData_exame()));
			ps.setInt(i, Integer.parseInt(fichaVo.getId_ficha()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Deleção de ficha */
	public void deleteFicha(Integer id_ficha) {
		StringBuilder query = new StringBuilder("DELETE FROM ficha WHERE id_ficha = ?");
		
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			ps.setInt(1, id_ficha);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}