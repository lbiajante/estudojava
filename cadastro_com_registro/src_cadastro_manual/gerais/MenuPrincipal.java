package gerais;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import utilitarias_cadastro_manual.MenuCadastroManual;
import conexao_cliente_manual.ServidorSocket;
import conexao_dispositivo.LerDispositivo;

public class MenuPrincipal {
	private Scanner entrada = new Scanner(System.in);

	public void menuPrincipal() {
		boolean confere = true;
		int i = 0;
		while (confere) {
			try {
				System.out.println("inicializando servidor...\n"
						+ "Indique o tipo de conexão:\n"
						+ "1 - Cliente administrador do banco\n"
						+ "2 - Dispositivo de ronda\n"
						+ "3 - Encerrar servidor");
				i = entrada.nextInt();

				if (i == 1) {
					menuEscolhido(5555);					
					this.menuPrincipal();
					break;

				} else if (i == 2) {
					menuEscolhido(6666);
					this.menuPrincipal();
					break;
				} else if (i == 3) {
					System.out.println("Servidor desconectado");
					confere = false;
				} else {
					System.out
							.println("opcao nao existe, por favor digite novamente");
					confere = true;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("opcao invalida, tente novamente ");
				confere = true;
			}
		}
	}

	public void menuEscolhido(int port) {
		try {
			ServidorSocket servidor = new ServidorSocket();
			ServerSocket server = servidor.server(port);
			System.out.println("aguardando conexao");
			boolean conf = true;
			String op = null;

			while (conf) {
				Socket cliente = server.accept();

				if (port == 5555) {
					System.out.println("cliente local conectado");
					new MenuCadastroManual(cliente);
					System.out
							.println("Deseja aguardar nova conexão manual? (S/N)");
					op = entrada.next();
					if (op.equalsIgnoreCase("S")) {
						System.out.println("aguardando nova conexão manual");
						conf = true;
					} else if (op.equalsIgnoreCase("N")) {
						System.out.println("ok");
						conf = false;
						break;
					} else {
						System.out.println("opcao invalida");
						conf = false;
						break;
					}

				} else if (port == 6666) {
					System.out.println("conexao_dispositivo conectado");
					new LerDispositivo(cliente);
					System.out
							.println("Deseja continuar a descarregar outros dispositivos? (S/N)");
					op = entrada.next();
					if (op.equalsIgnoreCase("S")) {
						System.out
								.println("aguardando nova conexão de dispositivo");
						conf = true;
					} else if (op.equalsIgnoreCase("N")) {
						System.out.println("ok");
						conf = false;
						break;
					} else {
						System.out.println("opcao invalida");
						conf = false;
						break;
					}
					cliente.close();
				}
			}			
		} catch (IOException e) {
			System.err
					.println("Erro ao conectar cliente ou conexao_dispositivo");

		}
	}
}
