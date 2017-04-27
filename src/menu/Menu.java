package menu;

import java.io.IOException;
import java.util.Scanner;
import banco.Funcionario;
import conta.Conta;

public class Menu {

	private Scanner entrada;

	public void iniciarMenu() throws IOException {
		int opcao;
		entrada = new Scanner(System.in);

		do {
			System.out.println("Banco Imaginario");
			System.out.println("1-Acessar Conta\n2-Funcionario\n3-Exit");
			opcao = entrada.nextInt();
			acessarOpcao(opcao);
		} while (opcao != 1 || opcao != 2 || opcao != 3);
	}

	public void acessarOpcao(int opcao) throws IOException {
		switch (opcao) {
		case 1:
			Conta.acessarConta();
			break;
		case 2:
			Funcionario.acessarFuncionario();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("Opcao Invalida");
			break;
		}
	}


}
