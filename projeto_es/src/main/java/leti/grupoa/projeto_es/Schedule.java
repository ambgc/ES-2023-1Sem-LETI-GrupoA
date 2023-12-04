package leti.grupoa.projeto_es;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

/**
 * Classe que representa a criação de um ficheiro de horário a partir de um
 * ficheiro CSV.
 */
public class Schedule implements ColumnGetter {

	private File f;
	private CSVParser csvParser;
	private ArrayList<String> scheduleText = new ArrayList<>();

	/**
	 * Obtém o nome do Schedule.
	 *
	 * @return O nome do Schedule.
	 */
	public String getName() {
		return f.getName();
	}

	/**
	 * Obtém o caminho do ficheiro do Schedule.
	 *
	 * @return O caminho do ficheiro do Schedule.
	 */
	public String getFilePath() {
		return f.getAbsolutePath();
	}

	/**
	 * Obtém o parser (leitor de CSV) associado a este Schedule.
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
	 * @param fileName Nome do ficheiro CSV.
	 * @param filePath Local do ficheiro CSV.
	 * @throws IOException Exceção lançada se ocorrer um erro de E/S durante a
	 *                     leitura do ficheiro.
	 */
	public Schedule() throws IOException {
		System.out.println("Por favor selecione um ficheiro CSV.");
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
	 * @param f Um objeto TXTFile.
	 * @return Uma instância de Schedule criada a partir do TXTFile fornecido.
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

	/**
	 * Carrega os dados de um Schedule para um ficheiro HTML.
	 */

	private String loadHTML() {
		String[] headerColumns = getColumn(getScheduleText().get(0));

		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append(htmlHeader);

		Iterator<String> rowIterator = getScheduleText().iterator(); // iterador de Linhas
		String[] aux;
		int i = 0;
		while (rowIterator.hasNext()) { // irá iterar enquanto houver linhas
			htmlContent.append("\t{");
			aux = getColumn(rowIterator.next());
			for (int j = 0; j < aux.length; j++) {

				if (j == aux.length - 1) {
					htmlContent.append("coluna" + i).append(": '").append(aux[j]).append("'");
				} else {
					htmlContent.append("coluna" + i).append(": '").append(aux[j]).append("',");
					htmlContent.append("\n\t");
				}
				i++;
			}
			i = 0;
			htmlContent.append("},\n");
		}

		htmlContent.append(varTable);

		// Ajuste: começamos a partir do segundo elemento
		for (int k = 0; i < headerColumns.length; k++) {
			htmlContent.append("					{title:'").append(headerColumns[k]).append("', field:'coluna")
					.append(k).append("', headerFilter:'input'},\n");
		}
		htmlContent.append(htmlFooter);

		System.out.println(htmlContent.toString());
		return htmlContent.toString();
	}

	public void generateHTML() throws IOException {
		StringBuilder html = new StringBuilder();
		System.out.println(getFilePath());
		html.append(loadHTML());

		String filePath = "Horario.html";

		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(html.toString());
		fileWriter.close();
		System.out.println("HTML criado no ficheiro " + filePath);

	}

	/// --- Strings HTML --- ///

	private static String htmlHeader = "<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>\n" + "	<head>\n"
			+ "		<meta charset='utf-8' />\n"
			+ "		<link href='https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css' rel='stylesheet'>\n"
			+ "		<script type='text/javascript' src='https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js'></script>\n"
			+ "	</head>\n" + "	<body>\n" + "		<H1>Horários</H1>	\n" + "		<div id='example-table'></div>\n"
			+ "\n" + "		<script type='text/javascript'>\n" + "\n" + "			var tabledata = [ \n";

	private static String varTable = "];\n" + "			var table = new Tabulator('#example-table', {\n"
			+ "				data:tabledata,\n" + "				layout:'fitDatafill',\n"
			+ "				pagination:'local',\n" + "				paginationSize:10,\n"
			+ "				paginationSizeSelector:[5, 10, 20, 40],\n" + "				movableColumns:true,\n"
			+ "				paginationCounter:'rows',\n"
			+ "				initialSort: [{ column: 'coluna0', dir: 'asc' }],\n" + "				columns:[\n";

	private static String htmlFooter = "				],\n" + "			});\n" + "		</script>\n"
			+ "	</body>\n" + "</html>";

}
