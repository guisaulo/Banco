package banco;

import java.io.IOException;
import java.util.Scanner;

import conta.Conta;

public class Funcionario {
	public static int passAdm = 123;
	private static Scanner entrada;

	public static void acessarFuncionario() throws IOException {
		System.out.println("Digite a senha de administrador: ");
		int senha, opcao;
		entrada = new Scanner(System.in);
		senha = entrada.nextInt();

		if (Funcionario.passAdm == senha) {
			System.out.println("Acesso liberado!");
			do {
				System.out.println("1-Criar Conta\n2-Mostrar dados das Contas\n3-Remover Conta");
				opcao = entrada.nextInt();
				if (opcao == 1){
					Conta.criarConta();
					break;
				}
				else if (opcao == 2){
					Banco.mostrarContas();
					break;
				}
				else if (opcao == 3){
					Conta.removerConta();
					break;
				}
			} while (opcao != 1 || opcao != 2 || opcao != 3);
		} else {
			System.out.println("Acesso negado!");
		}
	}

}
