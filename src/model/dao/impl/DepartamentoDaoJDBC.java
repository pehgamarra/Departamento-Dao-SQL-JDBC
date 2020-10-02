package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import entidade.model.Departamento;
import model.dao.DepartamentoDao;

public class DepartamentoDaoJDBC implements DepartamentoDao{
	
	private Connection conexao;

	public  DepartamentoDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Departamento obj) {
		PreparedStatement statement = null;
		try {
			statement = conexao.prepareStatement(
			 "INSERT INTO department "
			+"(Name) "
			+"VALUES "
			+"(?)",
			Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, obj.getNome());
			
		
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet resultset = statement.getGeneratedKeys();
				if(resultset.next()) {
					int id = resultset.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(resultset);
			}
			else {
				throw new DbException("Erro inesperado!! Nenhuma linha foi afetada!");
			}
		
		}
		catch (SQLException e) {
			
		}
		finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public void atualizar(Departamento obj) {
		
	}

	@Override
	public void deletarPorId(Integer id) {
		
	}

	@Override
	public Departamento acharPorId(Integer id) {
		return null;
	}

	@Override
	public List<Departamento> acharTodos() {
		return null;
	}

}
