package cadastro;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.server.ObjID;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {

	CadastroEmArquivo cad = new CadastroEmArquivo();
	Scanner entrada = new Scanner(System.in);
	private String path;

	public int verificaCodigo(String codigo) {
		int cod = 0;
		boolean confere = true;

		do {
			try {
				while (confere) {
					cod = Integer.parseInt(codigo.trim());
					if (cod <= 0) {
						System.out
								.println("O codigo precisa ser maior que zero");
						confere = true;
					} else {
						confere = false;
					}
				}
			} catch (NumberFormatException e) {
				System.out.printf("Voce nao digitou um número inteiro!\n");
				codigo = textInput("Digite um numero inteiro");
				cod = 0;
			}
		} while (cod == 0);
		return cod;
	}

	// ------------------GERAR ARQUIVO -------------
	public String gerarArquivo() {
		boolean confere = true;
		path = textInput("Digite o nome do arquivo a ser criado ou lido");
		path = path.trim();
		while (confere) {
			if (path.isEmpty() || path.trim().equals("")
					|| path.trim().equals(null)) {
				System.out
						.println("O campo nome do arquivo nao pode ser em branco");
				path = textInput("Digite o nome do arquivo");
				confere = true;
			} else {
				confere = false;
			}
		}
		path = path + ".txt";
		try {
			FileWriter criadorDeArquivo = new FileWriter(path, true);
			criadorDeArquivo.flush();
			criadorDeArquivo.close();
		} catch (IOException e) {
			System.out.println("Erro na criacao do arquivo");
		}
		return path;
	}

	// serialização: gravando o objetos no arquivo binário "nomeArq"
	public static void escreverNoArquivo(ArrayList<Object> cadastroEmArquivo,
			String nomeArq) {
		File arq = new File(nomeArq);
		try {
			ObjectOutputStream objOutput = new ObjectOutputStream(
					new FileOutputStream(arq, true));
			objOutput.writeObject(cadastroEmArquivo);
			objOutput.flush();
			objOutput.close();

		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}

	// desserialização: recuperando os objetos gravados no arquivo binário
	// "nomeArq"
	
	
	/*try{    
	  
	
	
		FileInputStream fin = null;
try{  
    JFileChooser caminhoArquivo = new JFileChooser();  
    caminhoArquivo.showOpenDialog(frame);  
    fin = new FileInputStream(new File(caminhoArquivo.getSelectedFile().getAbsolutePath()));     
    while (true){  
        ObjectInputStream ler = new ObjectInputStream(fin);
        Contato contato = (Contato) ler.readObject();  
        exibecontato.append(contatos.nome + "\n");  
    }  
} catch(ClassNotFoundException cnf){  
    cnf.printStackTrace();  
} catch(IOException ex){  
    ex.printStackTrace();  
} finally {
  if(fin != null)
     fin.close;
}*/

	public static ArrayList<Object> lerArquivo(String path) {
		ArrayList<Object> lista = new ArrayList<Object>();
		FileInputStream path1 = null;
		try {
			path1 = new FileInputStream(path);
			while (true){ 
			//File arq = new File(nomeArq);
			ObjectInputStream objInput = new ObjectInputStream(path1);
			
			lista = (ArrayList<Object>) objInput.readObject();				
		//	objInput.close();
			}
			
		}catch (EOFException eof){
			return (lista);
		}
		catch(ClassNotFoundException cnf){  
		    cnf.printStackTrace();  
		} catch(IOException ex){  
		    ex.printStackTrace();  
		}
		finally {
		  if(path1 != null)
			try {
				path1.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}			
		return (lista);
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
