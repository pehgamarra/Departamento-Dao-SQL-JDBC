package aplicação;

import java.util.List;
import java.util.Scanner;

import entidade.model.Departamento;
import model.dao.DepartamentoDao;
import model.dao.FabricaDao;

public class Programa2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		DepartamentoDao departamentoDao = FabricaDao.criarDepartamentoDao();
		
		System.out.println("======== TESTE 1: Buscar por Id ========");
		Departamento dep = departamentoDao.buscarPorId(1);
		System.out.println(dep);
		
		System.out.println("\n======== TESTE 2: Buscar todos departamentos ========");
		List<Departamento> lista = departamentoDao.acharTodos();
			for (Departamento d : lista) {
			System.out.println(d);
			}
		
		System.out.println("\n======== TESTE 3: Inserir departamento ========");
		Departamento novoDepartamento = new Departamento(null, "Musica");
		departamentoDao.inserir(novoDepartamento);
		System.out.println("Inserido! Novo 'Id': " + novoDepartamento.getId());

		System.out.println("\n======== TESTE 4: Atualizar Departamento ========");
		Departamento attDepartamento = departamentoDao.buscarPorId(1);
		attDepartamento.setNome("Computador");
		departamentoDao.atualizar(attDepartamento);
		System.out.println("Atualização completa");

		System.out.println("\n======== TESTE 5: Deletar departamento ========");
		System.out.print("Insira o numero do 'Id' a ser deletado (POR EXEMPLO " + novoDepartamento.getId() + ") : ");
		
		int id = sc.nextInt();
		
		departamentoDao.deletarPorId(id);
		System.out.println("Deleção Completa!");

		sc.close();
		
	}
}
