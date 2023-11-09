package leti.grupoa.projeto_es;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Classe que representa a criação de um ficheiro de horário a partir de um
 * ficheiro CSV.
 */
public class Schedule {

	private String name;
	private String path;
	private CSVParser csvParser;
	private ArrayList<String> scheduleText;

	/**
	 * Construtor de Schedule a partir de um ficheiro CSV especificado.
	 *
	 * @param fileName Nome do ficheiro CSV.
	 * @param filePath Local do ficheiro CSV.
	 * @throws IOException Exceção lançada se ocorrer um erro de E/S durante a
	 *                     leitura do ficheiro.
	 */
	public Schedule(String fileName, String filePath) throws IOException {
		this.name = fileName;
		this.path = filePath;
		FileReader fileReader = new FileReader(filePath);
		csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
		loadSchedule();
	}

	/**
	 * Construtor de Schedule interativo, que pede o caminho e nome do ficheiro.
	 *
	 * @throws IOException Exceção lançada se ocorrer um erro de E/S durante a
	 *                     leitura do ficheiro.
	 */
	public Schedule() throws IOException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Introduza a localização do ficheiro CSV: ");
		path = scanner.nextLine();
		System.out.println("Introduza o nome do ficheiro: ");
		String fileName = scanner.nextLine();
		if (!path.isBlank() && !fileName.isBlank()) {
			FileReader fileReader = new FileReader(path);
			csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
			System.out.println("Ficheiro " + fileName + " localizado em " + path + " carregado com sucesso.");
			loadSchedule();
			scanner.close();
		} else {
			System.out.println("Diretório ou nome inválido.");
			scanner.close();
		}
	}

	/**
	 * Construtor utilizado para criar uma instância de Schedule partir de dados já
	 * existentes.
	 *
	 * @param fileName     Nome do ficheiro.
	 * @param filePath     Localização do ficheiro.
	 * @param scheduleText Texto do ficheiro (Dados CSV passados para ArrayList).
	 */
	private Schedule(String fileName, String filePath, ArrayList<String> scheduleText) {
		name = fileName;
		path = filePath;
		csvParser = null;
		this.scheduleText = scheduleText;
	}

	/**
	 * Obtém o nome do Schedule.
	 *
	 * @return O nome do Schedule.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Obtém o caminho do ficheiro do Schedule.
	 *
	 * @return O caminho do ficheiro do Schedule.
	 */
	public String getFilePath() {
		return path;
	}

	/**
	 * Obtém o parser (leitor de CSV) associado a este Schedule.
	 *
	 * @return O parser CSV.
	 */
	public CSVParser getCsvParser() {
		return csvParser;
	}

	/**
	 * Converte um objeto TXTFile (ficheiro .txt convertido num objeto com os dados
	 * e Path deste) em um objeto Schedule.
	 *
	 * @param f Um objeto TXTFile.
	 * @return Uma instância de Schedule criada a partir do TXTFile fornecido.
	 */
	public static Schedule toSchedule(TXTFile f) {
		Schedule s = new Schedule(f.getName(), f.getPath(), f.getTxtText());
		return s;
	}

	/**
	 * Carrega o Schedule a partir do ficheiro CSV, processando os dados e
	 * armazenando o texto.
	 *
	 * @return Uma lista de strings, que contém os dados do ficheiro CSV.
	 */
	public ArrayList<String> loadSchedule() {
		scheduleText = new ArrayList<String>();
		for (CSVRecord csvRecord : csvParser) {
			String columns = "";
			columns = csvRecord.get(0);
			columns = columns.replace(";", " | ");
			scheduleText.add(columns);

			for (int i = 0; i < 10; i++) {
				if (i > 0 && i < csvRecord.size() && !csvRecord.get(i).isBlank()) {
					columns = columns + "," + csvRecord.get(i);
				}
			}
			columns = columns.replace(";", " | ");
			scheduleText.add("\n" + columns);
		}
		return scheduleText;
	}

	/**
	 * Imprime os dados do Schedule.
	 */
	public void printSchedule() {
		System.out.println(scheduleText);
		System.out.println("\n" + scheduleText.get(0));
	}


	public ArrayList<String> getScheduleText() {
		return scheduleText;
	}

}
