package aplicação;

import entidade.model.Vendedor;
import model.dao.FabricaDao;
import model.dao.VendedorDao;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		
		System.out.println("=== TESTE 1: vendedor encontrado pelo ID===");
		Vendedor vendedor = vendedorDao.buscarPorId(3);
		
		System.out.println(vendedor);
		
	}

}
