package leti.grupoa.projeto_es;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Classe que representa a criacao de um ficheiro de horario a partir de um
 * ficheiro CSV.
 */
public class Schedule implements ColumnGetter {

	private File f;
	private CSVParser csvParser;
	private ArrayList<String> scheduleText = new ArrayList<>();

	/**
	 * obtem o nome do Schedule.
	 *
	 * @return O nome do Schedule.
	 */
	public String getName() {
		return f.getName();
	}

	/**
	 * obtem o caminho do ficheiro do Schedule.
	 *
	 * @return O caminho do ficheiro do Schedule.
	 */
	public String getFilePath() {
		return f.getAbsolutePath();
	}

	/**
	 * obtem o parser (leitor de CSV) associado a este Schedule.
	 *
	 * @return O parser CSV.
	 */
	public CSVParser getCsvParser() {
		return csvParser;
	}

	public ArrayList<String> getScheduleText() {
		if (csvParser != null)
			loadSchedule();
		return scheduleText;
	}

	/**
	 * Construtor de Schedule a partir de um ficheiro CSV especificado.
	 *
	 * @throws IOException Exceção lançada se ocorrer um erro de E/S durante a
	 *                     leitura do ficheiro.
	 */
	public Schedule() throws IOException {
		System.out.println("Por favor selecione um ficheiro horário CSV.");
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			f = jfc.getSelectedFile();
			System.out.println("Ficheiro selecionado: " + f.getAbsolutePath());
			FileReader fr = new FileReader(f.getAbsolutePath());
			csvParser = new CSVParser(fr, CSVFormat.DEFAULT);
			loadSchedule();
		} else {
			throw new IOException("Por favor selecione um ficheiro.");
		}
	}

	/**
	 * Constrói um Schedule a partir de um ficheiro TXTFile (.txt)
	 *
	 * @param t Um objeto TXTFile.
	 */
	public Schedule(TXTFile t) {
		f = t.getFile();
		csvParser = null;
		scheduleText = t.getTxtText();
	}

	/**
	 * Processa os dados e armazena o texto de um Schedule
	 *
	 * @return Uma lista de strings, que contém os dados do ficheiro CSV.
	 */
	private ArrayList<String> loadSchedule() {
		for (CSVRecord csvRecord : csvParser) {
			String columns = "";
			columns = csvRecord.get(0);
			columns = columns.replace(";", " | ");
			scheduleText.add(columns);
			for (int i = 0; i < 10; i++) {
				if (i > 0 && i < csvRecord.size() && !csvRecord.get(i).isBlank()) {
					columns = columns + "," + csvRecord.get(i);
					columns = columns.replace(";", " | ");
					scheduleText.add("\n" + columns);
				}
			}

		}
		return scheduleText;
	}

	/**
	 * Imprime os dados do Schedule.
	 */
	public void printSchedule() {
		if (csvParser != null || scheduleText.isEmpty()) {
			loadSchedule();
		}
		System.out.println(scheduleText);
		System.out.println("\n" + scheduleText.get(0));
	}

	/// ----------- Funções de geração HTML ------------- ///

	public String[] getColumn() {
		return null;
	}

	public void addScheduleLine(String line) {
		scheduleText.add(line);
	}

}
