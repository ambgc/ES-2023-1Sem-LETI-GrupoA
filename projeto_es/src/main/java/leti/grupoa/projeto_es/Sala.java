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
	 * Construtor da classe Sala que solicita ao usuario a selecao de um arquivo de
	 * salas, realiza a leitura do arquivo e inicializa as estruturas de dados
	 * necessarias.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saida durante a selecao ou
	 *                     leitura do arquivo.
	 * @throws Exception   Se houver um erro nao especificado durante a execucao do
	 *                     construtor.
	 */
	public Sala(String salaName) throws IOException, Exception {
		System.out.println("Por favor, selecione um ficheiro de Salas.");
		JFileChooser jfc = new JFileChooser();
		this.salaName = salaName;

		// Verifica se o usuario selecionou um arquivo.
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			f = jfc.getSelectedFile();
			System.out.println("Ficheiro selecionado: " + f.getAbsolutePath());

			// Inicializa um FileReader e um CSVParser para o arquivo selecionado.
			FileReader fr = new FileReader(f.getAbsolutePath());
			csvParser = new CSVParser(fr, CSVFormat.DEFAULT);

			// Chama o metodo csvRead para processar o conteudo do arquivo.
			csvRead();
			caractChecker();
		} else {
			// Lanca uma excecao se o usuario nao selecionar um arquivo.
			throw new IOException("Por favor, selecione um ficheiro.");
		}
	}

	public String getNome() {
		return salaName;
	}

	/**
	 * Realiza a leitura de um arquivo CSV e processa suas linhas para atualizar
	 * listas de caracteristicas.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saida durante o
	 *                     processamento do arquivo CSV.
	 */
	void csvRead() throws IOException {
		// Itera sobre as linhas do arquivo CSV usando CSVRecord.
		for (CSVRecord csvRecord : csvParser) {
			// Obtem a linha atual do CSV.
			String currLine = csvRecord.get(0);

			// Adiciona a linha a lista auxiliar, considerando quebra de linha se
			// necessario.
			if (aux.size() == 0) {
				aux.add(currLine);
			} else {
				aux.add("\n" + currLine);
			}
		}

		// Chama a funcao addCarac para atualizar a lista geral de caracteristicas.
		addCarac();
	}

	/**
	 * Adiciona caracteristicas a lista geral de caracteristicas com base na
	 * primeira linha da lista auxiliar.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saida durante o
	 *                     processamento.
	 */
	void addCarac() throws IOException {
		// Divide a primeira linha da lista auxiliar usando ";" como delimitador.
		String[] aux2 = aux.get(0).split(";");

		// Adiciona as caracteristicas a lista geral de caracteristicas.
		for (int i = 0; i < aux2.length; i++) {
			aux2[i].trim();
			allCaracteristicas.add(aux2[i]);
		}
	}

	/**
	 * Verifica e processa as caracteristicas associadas a uma determinada sala.
	 *
	 * verificadas e processadas.
	 * 
	 * @throws IOException Se ocorrer um erro de entrada/saida durante o
	 *                     processamento.
	 */
	void caractChecker() throws IOException {
		// String para armazenar as caracteristicas da sala.
		String caractSala = "";

		// Procura a sala na lista auxiliar e obtem suas caracteristicas.
		for (int i = 1; i < aux.size(); i++) {
			if (aux.get(i).contains(salaName)) {
				caractSala = aux.get(i);
			}
		}

		// Processa as caracteristicas se a sala nao estiver vazia.
		if (!caractSala.isEmpty()) {
			// Adiciona elementos vazios para as duas primeiras posicoes da lista.
			myCaracteristicas.add(0, "");
			myCaracteristicas.add(1, "");

			// Divide as caracteristicas da sala usando ";" como delimitador.
			String[] splitColumn = caractSala.split(";");

			// Adiciona as caracteristicas processadas a lista.
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
	 * Retorna o valor associado a uma determinada caracteristica, representada como
	 * uma string, se essa caracteristica existir nas listas de caracteristicas e
	 * valores.
	 *
	 * @param carac A caracteristica para a qual se deseja obter o valor como um
	 *              inteiro.
	 * @return O valor associado a caracteristica como um inteiro.
	 * @throws IOException              Se ocorrer um erro de entrada/saida ao
	 *                                  converter a string para inteiro.
	 * @throws IllegalArgumentException Se a caracteristica fornecida nao existir na
	 *                                  lista de caracteristicas.
	 */

	int getCaracValue(String carac) throws IOException {
		// fazer com que o input seja uma caracteristica qualquer, se existir devolver o
		// valor como int
		for (int i = 0; i < allCaracteristicas.size(); i++) {
			if (allCaracteristicas.get(i).equals(carac) && !myCaracteristicas.isEmpty()) {
				return Integer.parseInt(myCaracteristicas.get(i));
			}
		}
		throw new IllegalArgumentException("A caraceteristica '" + carac + "' nao existe");
	}

	/**
	 * Verifica se a lista de caracteristicas contem um determinado caractere e se a
	 * correspondente caracteristica do objeto atual contem o marcador "X".
	 *
	 * @param carac O caractere a ser verificado na lista de caracteristicas.
	 * @return {@code true} se o caractere estiver presente e a caracteristica
	 *         correspondente conter "X", {@code false} caso contrario.
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
	 * Obtem o numero de atributos associados a sala.
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
