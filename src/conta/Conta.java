package conta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import banco.Banco;

public class Conta {
	public int id;
	public String nome;
	public double saldo;
	private static Scanner entrada, entrada2;

	public static void carregarContas() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("contas.txt")));
		String linha, nomeCliente;
		String dadosConta[] = new String[2];
		int idConta;
		double saldoConta;

		linha = br.readLine();
		while (linha != null) {
			dadosConta = linha.split(" ");
			idConta = Integer.parseInt(dadosConta[0]);
			nomeCliente = dadosConta[1];
			saldoConta = Double.parseDouble(dadosConta[2]);
			ContaCorrente c = new ContaCorrente(idConta, nomeCliente);
			c.depositar(saldoConta);
			Banco.adicionarConta(c);
			// contas.add(c);
			linha = br.readLine();
		}
		br.close();
	}

	public static void criarConta() throws IOException {

		entrada = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("Escolha o tipo de conta corrente: ");
			System.out.println("1-Corrente");
			System.out.println("2-Poupanca (nao implementado)");
			opcao = entrada.nextInt();
			if (opcao == 1) {
				ContaCorrente.criaContaCorrente();
				break;
			} else if (opcao == 2) {
				ContaPoupanca.criaContaPoupanca();
				break;
			} else
				System.out.println("Opcao invalida!");
		} while (opcao != 1 || opcao != 2);
	}

	public void mostrarInfos() throws IOException {
		System.out.println("Conta " + id + " " + nome + " Saldo: " + saldo);
		OutputStream os = new FileOutputStream("contas.txt", true);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);

		bw.write(id + " " + nome + " " + saldo);
		bw.newLine();
		bw.close();
	}

	public void sacar(double valor) {
		this.saldo = this.saldo - valor;
	}

	public void depositar(double valor) {
		this.saldo = this.saldo + valor;
	}

	public static void atualizaContas() throws IOException {
		OutputStream os = new FileOutputStream("contas.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);

		for (Conta conta : Banco.contas) {
			bw.write(conta.id + " " + conta.nome + " " + conta.saldo);
			bw.newLine();
		}
		bw.close();
	}
	
	public static void removerConta() throws IOException{
		System.out.println("Digite o id da conta: ");
		entrada2 = new Scanner(System.in);
		int id = entrada2.nextInt();
		Conta conta = Banco.buscaConta(id);
		System.out.println(conta.id + " " + conta.nome + " " + conta.saldo + " removida!");
		Banco.contas.remove(conta);
		atualizaContas();
	}
	
	public static void acessarConta() throws IOException {
		System.out.println("Digite o numero da conta:");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String idS = in.readLine();
		int id = Integer.parseInt(idS);

		entrada2 = new Scanner(System.in);
		int opcao;
		double valorSaque, valorDeposito;

		do {
			System.out.println("Escolha a opcao: ");
			System.out.println("1-Saque");
			System.out.println("2-Deposito");
			System.out.println("3-Extrato (nao implementado)");
			opcao = entrada2.nextInt();
			if (opcao == 1) {
				Conta contaBuscada = Banco.buscaConta(id);
				contaBuscada.mostrarInfos();
				System.out.println("Valor do Saque: ");
				valorSaque = entrada2.nextDouble();
				contaBuscada.sacar(valorSaque);
				Conta.atualizaContas();
				System.out.println("Novo saldo: " + contaBuscada.saldo);

				break;
			} else if (opcao == 2) {
				Conta contaBuscada = Banco.buscaConta(id);
				contaBuscada.mostrarInfos();
				System.out.println("Valor do Deposito: ");
				valorDeposito = entrada2.nextDouble();
				contaBuscada.depositar(valorDeposito);
				Conta.atualizaContas();
				System.out.println("Novo saldo: " + contaBuscada.saldo);
				break;
			} else {
				System.out.println("Opcao invalida!");
				break;
			}
		} while (opcao != 1 || opcao != 2);
	}
}
