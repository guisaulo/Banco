package banco;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import conta.Conta;
import menu.Menu;

public class Banco {
	String nome, cnpj;
	int numContas;
	
	public static List<Conta> contas;
	Funcionario func = new Funcionario();

	public Banco(String nome, String cnpj) throws IOException {
		this.nome=nome;
		this.cnpj=cnpj;
		contas = new LinkedList<Conta>();
		Conta.carregarContas();
		Menu menu = new Menu();
		menu.iniciarMenu();
	}

	public static void adicionarConta(Conta c) {
		contas.add(c);

	}
	
	public static void mostrarContas() {
		for (Conta conta : contas) {
			System.out.println(conta);
		}
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getCnpj(){
		return this.cnpj;
	}
	
	public int getNumContas(){
		return Banco.contas.size();
	}
	
	public static Conta buscaConta(int id){
		Conta aux = null;
		for (Conta conta : contas) {
			if(conta.id == id){
				aux=conta;
			}
		}
		return aux;
	}
}
	