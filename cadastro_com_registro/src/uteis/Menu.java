package uteis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import registro.AtualizarRegistro;
import registro.CadastrarRegistro;
import registro.ListarRegistro;
import registro.RemoverRegistro;
import local.CadastrarLocal;
import local.ListarLocais;
import local.RemoverLocal;
import cadastro.Atualizar;
import cadastro.Cadastrar;
import cadastro.Listar;
import cadastro.Remover;

public class Menu {

	Scanner entrada = new Scanner(System.in);
	Cadastrar cadastrar = new Cadastrar();
	CadastrarRegistro cadRegistro = new CadastrarRegistro();
	CadastrarLocal cadLocal = new CadastrarLocal();
	Listar listar = new Listar();
	ListarRegistro listRegistro = new ListarRegistro();
	ListarLocais listLocal = new ListarLocais();
	Remover remover = new Remover();
	RemoverRegistro remRegistro = new RemoverRegistro();
	RemoverLocal remLocal = new RemoverLocal();
	Atualizar atualizar = new Atualizar();
	AtualizarRegistro atualRegistro = new AtualizarRegistro();

	public String menuPrincipal = "\nBEM VINDO AO CADASTRO DE PESSOAS E REGISTROS DE VISITAS\n"
			+ "\n---------------------------------------------------------------------------------------------\n"
			+ "Digite o numero da opcao selecionada:"
			+ "\n1 - Cadastrar pessoas/ Novos registros de visitas/ Novos locais\n"
			+ "2 - Listar cadastro de pessoas/ Listar registros de visitas/ Listar locais cadstrados\n"
			+ "3 - Apagar item do cadastro de pessoas/ Apagar registros de visitas/ Apagar locais\n"
			+ "4 - Atualizar cadastro de pessoas/ Atualizar registros de visitas\n"
			+ "5 - Sair\n"
			+ "---------------------------------------------------------------------------------------------";

	public String listaSubMenu(int opcao) {
		String mensagemCliente = null;
		switch (opcao) {

		case 1:
			mensagemCliente = "Deseja cadastrar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais";
			break;
		case 2:
			mensagemCliente = "Deseja listar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais";
			break;
		case 3:
			mensagemCliente = "Deseja remover: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais";
			break;
		case 4:
			mensagemCliente = "Deseja atualizar: \n1 - Pessoas\n2 - Registros de Visitas\n ";
			break;
		case 5:
			mensagemCliente = "Programa de cadastro finalizado!";
			break;

		default:
			mensagemCliente = "Opcao invalida";
		}
		return mensagemCliente;
	}

	public String submenu(ObjectInputStream input, ObjectOutputStream output, int subOpcao) throws ClassNotFoundException, IOException {

		String mensagemCliente = null;
		switch (subOpcao) {
		case 11:
			cadastrar.cadastrar();
			mensagemCliente = "Cadastro de pessoa feito com sucesso";
			break;
		case 12:
			cadRegistro.inserirRegistro();
			mensagemCliente = "Registro de visita feito com sucesso";
			break;
		case 13:
			cadLocal.cadastrarLocal();
			mensagemCliente = "Local inserido com sucesso";
			break;
		case 21:			
			mensagemCliente = listar.listarCadastros();
			break;
		case 22:			
			mensagemCliente = listRegistro.listarRegistros();
			break;
		case 23:			
			mensagemCliente = listLocal.listarLocais();
			break;
		case 31:
			remover.removerCadastro();
			mensagemCliente = "Cadastro removido";
			break;
		case 32:
			remRegistro.removerRegistro();
			mensagemCliente = "Regiostro removido";
			break;
		case 33:
			
			mensagemCliente = remLocal.removerLocal(input, output);
			break;
		case 41:
			atualizar.atualizarCadastro();
			mensagemCliente = "Cadastro atualizado";
			break;
		case 42:
			atualRegistro.atualizarRegistro();
			mensagemCliente = "Registro atualizado";
			break;
		default:
			System.out.println("Opcao invalida. Tente novamente!");
			// this.menu();
		}
		return mensagemCliente;
	}
}
