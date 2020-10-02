package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		PreparedStatement statement = null;
		try {
			statement = conexao.prepareStatement(
				"UPDATE department "
				+ "SET Name = ? "
				+ "WHERE Id = ? ");
			
			statement.setString(1, obj.getNome());
			statement.setInt(2, obj.getId());
		
			statement.executeUpdate();
			
		}
		catch (SQLException e) {
			
		}
		finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public void deletarPorId(Integer id) {
		PreparedStatement statement = null;
		try {
			statement = conexao.prepareStatement("DELETE FROM department WHERE Id = ? ");
			
			statement.setInt(1, id);
			
			int linhas = statement.executeUpdate();
			
			if(linhas == 0) {
				throw new DbException("Este ID nao existe!");
			}
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public Departamento buscarPorId(Integer id) {	
			PreparedStatement statement = null;
			ResultSet resultset = null;
			try {
				statement = conexao.prepareStatement("SELECT * FROM department WHERE Id = ?");
				
				statement.setInt(1, id);
				
				resultset = statement.executeQuery();
				
					if (resultset.next()) {
						Departamento obj = new Departamento();
						obj.setId(resultset.getInt("Id"));
						obj.setNome(resultset.getString("Name"));
						return obj;
				
					}
					return null;
				
				}
				catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			
				finally {
					DB.closeStatement(statement);
					DB.closeResultSet(resultset);
			}
		}
	
		@Override
		public List<Departamento> acharTodos() {
			PreparedStatement statement = null;
			ResultSet resultset = null;
			try {
				statement = conexao.prepareStatement("SELECT * FROM department ORDER BY Name");
				
				resultset = statement.executeQuery();
				
				List<Departamento> list = new ArrayList<>();
				
				while(resultset.next()) {
					Departamento obj = new Departamento();
					obj.setId(resultset.getInt("Id"));
					obj.setNome(resultset.getString("Name"));
					list.add(obj);
				}
				return list;
				
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(statement);
				DB.closeResultSet(resultset);
			}
		}

}
