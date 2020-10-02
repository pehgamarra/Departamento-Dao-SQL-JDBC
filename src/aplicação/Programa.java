package aplicação;

import java.util.Date;
import java.util.List;

import entidade.model.Departamento;
import entidade.model.Vendedor;
import model.dao.FabricaDao;
import model.dao.VendedorDao;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		
		System.out.println("======== TESTE 1: vendedor encontrado pelo ID========");
		Vendedor vendedor = vendedorDao.buscarPorId(3);
		System.out.println(vendedor);
		
		System.out.println("\n======== TESTE 2: vendedor encontrado pelo Departamento========");
		Departamento departamento = new Departamento(2, null);
		List<Vendedor> lista = vendedorDao.buscarPorDepartamento(departamento);
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
		System.out.println("\n======== TESTE 3: Encontrar todos os vendedores========");
		lista = vendedorDao.acharTodos();
		for (Vendedor obj : lista) {
			System.out.println(obj);
		}
		System.out.println("\n======== TESTE 4: Inserindo Vendedor========");
		Vendedor novoVendedor = new Vendedor (null, "Pedro Henrique Sobrinho Gamarra da Silva"
				, "pedrohhouro@gmail.com", new Date(), 4000.0, departamento);
		vendedorDao.inserir(novoVendedor);
		System.out.println("Inserido! Novo id= " + novoVendedor.getId());
	}

}
