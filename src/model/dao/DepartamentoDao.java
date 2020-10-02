package model.dao;

import java.util.List;

import entidade.model.Departamento;

public interface DepartamentoDao {
	
	void inserir(Departamento obj);
	void atualizar(Departamento obj);
	void deletarPorId(Integer id);
	List<Departamento> acharTodos();
	Departamento buscarPorId(Integer id);

}
