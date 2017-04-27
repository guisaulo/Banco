package conta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import banco.Banco;

public class ContaCorrente extends Conta {
		
	private static Scanner deposito;

	public ContaCorrente(int id, String nome) {
		this.id=id;
		this.nome=nome;
	}
	
	public static void criaContaCorrente() throws IOException {
		int id = (int) (Math.random() * 100);
		deposito = new Scanner(System.in);
		double valorDeposito;

		System.out.println("Digite seu nome");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String nome = in.readLine();

		ContaCorrente c = new ContaCorrente(id, nome);
		System.out.println("Digite um valor de deposito");
		valorDeposito = deposito.nextDouble();
		c.depositar(valorDeposito);
		c.mostrarInfos();
		Banco.adicionarConta(c);
	}
	
	@Override
	public String toString() {
		return "Conta " + id + " " + nome + " Saldo: " + saldo;
	}
}
