package conexao_dispositivo;

import gerais.ConectaBD;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import registro.RegistroVisita;
import conexao_cliente_manual.Gerenciador;

public class RegistroDispositivo {

	private String path = null;

	FormataDataDispositivo fdd = new FormataDataDispositivo();
	RegistroVisita reg = new RegistroVisita();
	CadastrarLocalDispositivo cld = new CadastrarLocalDispositivo();

	public void recebeArquivoDispositivo(Socket cliente, Gerenciador msg,
			String id, String nome) {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					cliente.getInputStream()));
			ObjectInputStream in = new ObjectInputStream(
					cliente.getInputStream());
			path = "arquivo_recebido" + fdd.getDateTime() + "__" + id + "__"
					+ nome + ".bin";
			FileOutputStream file = new FileOutputStream(path);
			byte[] buffer = new byte[4096];

			while (true) {
				int lenght = in.read(buffer);
				if (lenght == -1)
					break;
				file.write(buffer, 0, lenght);
				file.flush();
				file.close();
				in.close();
				reader.close();
			}
			System.out.println("arquivo recebido");
		} catch (IOException e) {
			System.out.println("Erro ao receber arquivo");
		}
	}

	public void cadastrarRegistroDispositivo(String IdPessoa, String nomePessoa) {
		ArrayList<String> lugarS = new ArrayList<String>();
		ArrayList<String> dataS = new ArrayList<String>();
		ArrayList<String> horaS = new ArrayList<String>();
		int qtdeDeRegistros = 0;

		try {
			FileInputStream arq = new FileInputStream(path);
			InputStreamReader input = new InputStreamReader(arq);
			BufferedReader lerCadastro = new BufferedReader(input);
			String linha = lerCadastro.readLine();
			if (linha == null) {
				System.out.println("Cadastro vazio");
			}

			while (linha != null) {
				String[] linhaSplit = linha.split(";");
				lugarS.add(linhaSplit[0]);
				dataS.add(linhaSplit[1]);
				horaS.add(linhaSplit[2]);
				qtdeDeRegistros++;
				linha = lerCadastro.readLine();
			}

			arq.close();
			lerCadastro.close();
		} catch (IOException e) {
			System.err.printf("Erro na manipulacao do arquivo recebido");
		}

		System.out
				.println("Quantidade de registros importados do dispositivo = "
						+ qtdeDeRegistros);

		String sql = "INSERT INTO registro_de_visitas "
				+ "(visitante, data_visita, hora_visita, id_pessoa, lugar) "
				+ "values (?,?,?,?,?);";

		try (PreparedStatement ps = ConectaBD.getConnection().prepareStatement(sql)) {
			for (int i = 0; i < qtdeDeRegistros; i++) {

				reg.setLocal(cld.cadastrarLocal(lugarS.get(i)));
				reg.setData(fdd.data(dataS.get(i)));
				reg.setHora(fdd.hora(horaS.get(i)));
				reg.setIDpessoa(IdPessoa);
				reg.setNomePessoa(nomePessoa);

				ps.setString(1, reg.getNomePessoa());
				ps.setString(2, reg.getData());
				ps.setString(3, reg.getHora());
				ps.setString(4, reg.getIDpessoa());
				ps.setString(5, reg.getLocal());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.clearBatch();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
