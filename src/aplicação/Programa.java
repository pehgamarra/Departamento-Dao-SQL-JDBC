package aplicação;

import entidade.model.Vendedor;
import model.dao.FabricaDao;
import model.dao.VendedorDao;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		
		Vendedor vendedor = vendedorDao.acharPorId(3);
		
		System.out.println(vendedor);
		

	}

}
