package leti.grupoa.projeto_es;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * A classe Sala representa uma sala de um edificio, com informacoes como o
 * nome, capacidades, tipo e edificio ao qual pertence.
 */
public class Sala {
	private String salaName;
	private File f;
	private CSVParser csvParser;
	private final ArrayList<String> allCaracteristicas = new ArrayList<>();
	private ArrayList<String> myCaracteristicas = new ArrayList<>();
	private ArrayList<String> aux = new ArrayList<>();

	/**
	 * Construtor da classe Sala que solicita ao usuário a seleção de um arquivo de
	 * salas, realiza a leitura do arquivo e inicializa as estruturas de dados
	 * necessárias.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saída durante a seleção ou
	 *                     leitura do arquivo.
	 * @throws Exception   Se houver um erro não especificado durante a execução do
	 *                     construtor.
	 */
	public Sala(String salaName) throws IOException, Exception {
		System.out.println("Por favor, selecione um ficheiro de Salas.");
		JFileChooser jfc = new JFileChooser();
		this.salaName = salaName;

		// Verifica se o usuário selecionou um arquivo.
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			f = jfc.getSelectedFile();
			System.out.println("Ficheiro selecionado: " + f.getAbsolutePath());

			// Inicializa um FileReader e um CSVParser para o arquivo selecionado.
			FileReader fr = new FileReader(f.getAbsolutePath());
			csvParser = new CSVParser(fr, CSVFormat.DEFAULT);

			// Chama o método csvRead para processar o conteúdo do arquivo.
			csvRead();
			caractChecker();
		} else {
			// Lança uma exceção se o usuário não selecionar um arquivo.
			throw new IOException("Por favor, selecione um ficheiro.");
		}
	}

	public String getNome() {
		return salaName;
	}

	/**
	 * Realiza a leitura de um arquivo CSV e processa suas linhas para atualizar
	 * listas de características.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saída durante o
	 *                     processamento do arquivo CSV.
	 */
	void csvRead() throws IOException {
		// Itera sobre as linhas do arquivo CSV usando CSVRecord.
		for (CSVRecord csvRecord : csvParser) {
			// Obtém a linha atual do CSV.
			String currLine = csvRecord.get(0);

			// Adiciona a linha à lista auxiliar, considerando quebra de linha se
			// necessário.
			if (aux.size() == 0) {
				aux.add(currLine);
			} else {
				aux.add("\n" + currLine);
			}
		}

		// Chama a função addCarac para atualizar a lista geral de características.
		addCarac();
	}

	/**
	 * Adiciona características à lista geral de características com base na
	 * primeira linha da lista auxiliar.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saída durante o
	 *                     processamento.
	 */
	void addCarac() throws IOException {
		// Divide a primeira linha da lista auxiliar usando ";" como delimitador.
		String[] aux2 = aux.get(0).split(";");

		// Adiciona as características à lista geral de características.
		for (int i = 0; i < aux2.length; i++) {
			aux2[i].trim();
			allCaracteristicas.add(aux2[i]);
		}
	}

	/**
	 * Verifica e processa as características associadas a uma determinada sala.
	 *
	 * @param salaName O nome da sala para a qual as características serão
	 *                 verificadas e processadas.
	 * @throws IOException Se ocorrer um erro de entrada/saída durante o
	 *                     processamento.
	 */
	void caractChecker() throws IOException {
		// String para armazenar as características da sala.
		String caractSala = "";

		// Procura a sala na lista auxiliar e obtém suas características.
		for (int i = 1; i < aux.size(); i++) {
			if (aux.get(i).contains(salaName)) {
				caractSala = aux.get(i);
			}
		}

		// Processa as características se a sala não estiver vazia.
		if (!caractSala.isEmpty()) {
			// Adiciona elementos vazios para as duas primeiras posições da lista.
			myCaracteristicas.add(0, "");
			myCaracteristicas.add(1, "");

			// Divide as características da sala usando ";" como delimitador.
			String[] splitColumn = caractSala.split(";");

			// Adiciona as características processadas à lista.
			for (int i = 2; i < splitColumn.length; i++) {
				if (!splitColumn[i].isEmpty()) {
					myCaracteristicas.add(i, splitColumn[i]);
				} else {
					myCaracteristicas.add(i, "");
				}
			}
		}
	}

	/**
	 * Retorna o valor associado a uma determinada característica, representada como
	 * uma string, se essa característica existir nas listas de características e
	 * valores.
	 *
	 * @param carac A característica para a qual se deseja obter o valor como um
	 *              inteiro.
	 * @return O valor associado à característica como um inteiro.
	 * @throws IOException              Se ocorrer um erro de entrada/saída ao
	 *                                  converter a string para inteiro.
	 * @throws IllegalArgumentException Se a característica fornecida não existir na
	 *                                  lista de características.
	 */

	int getCaracValue(String carac) throws IOException {
		// fazer com que o input seja uma caracteristica qualquer, se existir devovler o
		// valor como int
		for (int i = 0; i < allCaracteristicas.size(); i++) {
			if (allCaracteristicas.get(i).equals(carac) && !myCaracteristicas.isEmpty()) {
				return Integer.parseInt(myCaracteristicas.get(i));
			}
		}
		throw new IllegalArgumentException("A caraceterística '" + carac + "' não existe");
	}

	/**
	 * Verifica se a lista de características contém um determinado caráter e se a
	 * correspondente característica do objeto atual contém o marcador "X".
	 *
	 * @param carac O caráter a ser verificado na lista de características.
	 * @return {@code true} se o caráter estiver presente e a característica
	 *         correspondente contiver "X", {@code false} caso contrário.
	 */

	boolean hasCarac(String carac) {

		for (int i = 0; i < allCaracteristicas.size(); i++) {
			if (allCaracteristicas.get(i).equals(carac) && myCaracteristicas.get(i).contains("X")) {
				return true;
			}
		}

		return false;
	}

	// Metodos de acesso aos atributos da sala

	/**
	 * Obtem o nome do edificio ao qual a sala pertence.
	 *
	 * @return Nome do edificio.
	 */
	public String getEdificio() {
		return allCaracteristicas.get(0);
	}

	/**
	 * Obtem a capacidade normal da sala.
	 *
	 * @return Capacidade normal da sala.
	 */
	public int getCapacidadeNormal() {
		return Integer.parseInt(allCaracteristicas.get(2));
	}

	/**
	 * Obtem a capacidade extra da sala.
	 *
	 * @return Capacidade extra da sala.
	 */
	public int getCapacidadeExame() {
		return Integer.parseInt(allCaracteristicas.get(3));
	}

	/**
	 * Obtem o numero de atriburos associados a sala.
	 *
	 * @return Lista de tipos da sala.
	 */
	public int getNumeroCaracteristicas() {
		return allCaracteristicas.size();
	}

	/**
	 * Obtem a lista de tipos associados a sala.
	 *
	 * @return Lista de caracteristicas da sala.
	 */
	public List<String> getCaracteristicasSala() {
		return allCaracteristicas;
	}

	public String makeHTML() {
		return "Sala [edificio=" + getEdificio() + ", nome=" + getNome() + ", capacidadeNormal=" + getCapacidadeNormal()
				+ ", capacidadeExame=" + getCapacidadeExame() + ", nCaracteristicas=" + getNumeroCaracteristicas()
				+ ", caracteristicas=" + getCaracteristicasSala().toString() + "]";
	}

}