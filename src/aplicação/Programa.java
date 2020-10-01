package aplicação;

import java.util.Date;

import entidade.model.Departamento;
import entidade.model.Vendedor;
import model.dao.FabricaDao;
import model.dao.VendedorDao;

public class Programa {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Livros");
		Vendedor vendedor = new Vendedor(21, "BOB", "Bob@gmail.com", new Date(), 3000.0, obj);
		
		VendedorDao vendedorDao = FabricaDao.criarVendedorDao();
		
		System.out.println(vendedor);
		

	}

}
