package model.dao;

import java.util.List;

import entidade.model.Vendedor;

public interface VendedorDao {
	
	void inserir(Vendedor obj);
	void atualizar(Vendedor obj);
	void deletarPorId(Integer id);
	Vendedor buscarPorId(Integer id);
	List<Vendedor> acharTodos();

}
