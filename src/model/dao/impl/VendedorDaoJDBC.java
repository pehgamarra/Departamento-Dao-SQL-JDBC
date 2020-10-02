package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import entidade.model.Departamento;
import entidade.model.Vendedor;
import model.dao.VendedorDao;

public class VendedorDaoJDBC implements VendedorDao {
	
	private Connection conexao;
	
	public VendedorDaoJDBC(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Vendedor obj) {
	}

	@Override
	public void atualizar(Vendedor obj) {
	}

	@Override
	public void deletarPorId(Integer id) {
	}

	@Override
	public Vendedor buscarPorId(Integer id) {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ? ");
			
			statement.setInt(1, id);
			resultset = statement.executeQuery();
			
		if (resultset.next()) {
			Departamento departamento = instantiateDepartamento(resultset);
			Vendedor obj = instantiateVendedor(resultset, departamento);
			return obj;
			
		}return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(statement);
			DB.closeResultSet(resultset);
		}
	}
	
	private Vendedor instantiateVendedor(ResultSet resultset, Departamento departamento) throws SQLException {
		Vendedor obj = new Vendedor();
		obj.setId(resultset.getInt("Id"));
		obj.setNome(resultset.getString("Name"));
		obj.setEmail(resultset.getString("Email"));
		obj.setSalarioBase(resultset.getDouble("BaseSalary"));
		obj.setDataDeNascimento(resultset.getDate("BirthDate"));
		obj.setDepartamento(departamento);
		return obj;
	}

	private Departamento instantiateDepartamento(ResultSet resultset) throws SQLException {
		Departamento departamento = new Departamento();
		departamento.setId(resultset.getInt("DepartmentId"));
		departamento.setNome(resultset.getString("DepName"));
		return departamento;
		
	}

	@Override
	public List<Vendedor> acharTodos() {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"ORDER BY Name ");
			
			resultset = statement.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while(resultset.next()) {
				Departamento dep = map.get(resultset.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartamento(resultset);
					map.put(resultset.getInt("DepartmentId"), dep);
				}
				
				Vendedor obj = instantiateVendedor(resultset, dep);
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

	@Override
	public List<Vendedor> buscarPorDepartamento(Departamento departamento) {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			statement = conexao.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? "
					+"ORDER BY Name ");
			
			statement.setInt(1, departamento.getId());
			resultset = statement.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while(resultset.next()) {
				Departamento dep = map.get(resultset.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartamento(resultset);
					map.put(resultset.getInt("DepartmentId"), dep);
				}
				
				Vendedor obj = instantiateVendedor(resultset, dep);
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
