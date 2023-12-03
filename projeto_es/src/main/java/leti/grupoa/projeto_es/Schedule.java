package leti.grupoa.projeto_es;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
	
	public static String loadHTMLFromCSV(String csvFilePath) throws IOException {
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line = new String();
			String headerLine = br.readLine();
			headerLine = headerLine.replace("|", ";");
			String[] headerColumns = headerLine.split(";");
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				records.add(Arrays.asList(values));
			}

			StringBuilder htmlContent = new StringBuilder();
			htmlContent.append("<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>\n" + "	<head>\n"
					+ "		<meta charset='utf-8' />\n"
					+ "		<link href='https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css' rel='stylesheet'>\n"
					+ "		<script type='text/javascript' src='https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js'></script>\n"
					+ "	</head>\n" + "	<body>\n" + "		<H1>Horários</H1>	\n"
					+ "		<div id='example-table'></div>\n" + "\n" + "		<script type='text/javascript'>\n"
					+ "\n" + "			var tabledata = [ \n");

			for (Iterator rowIterator = records.iterator(); rowIterator.hasNext();) {
				List<String> row = (List<String>) rowIterator.next();
				htmlContent.append("\t{");

				// Adiciona cada coluna ao objeto JavaScript
				Iterator<String> columnIterator = row.iterator();
				int columnIndex = 0;

				while (columnIterator.hasNext()) {
					String column = columnIterator.next();

					// Extrai o nome da coluna (por exemplo, colunaCurso) da lista de registros
					String columnName = "coluna" + columnIndex;

					// Adiciona a coluna ao objeto JavaScript
					if(columnIndex == 10) {
						htmlContent.append(columnName).append(": '").append(column).append("'");
					}else {
					htmlContent.append(columnName).append(": '").append(column).append("',");
					}

					if (columnIterator.hasNext()) {
						htmlContent.append("\n\t");
					}

					columnIndex++;
				}

				htmlContent.append("},\n");
			}
			htmlContent.append("];\n" + "			var table = new Tabulator('#example-table', {\n"
					+ "				data:tabledata,\n" + "				layout:'fitDatafill',\n"
					+ "				pagination:'local',\n" + "				paginationSize:10,\n"
					+ "				paginationSizeSelector:[5, 10, 20, 40],\n" + "				movableColumns:true,\n"
					+ "				paginationCounter:'rows',\n"
					+ "				initialSort: [{ column: 'coluna0', dir: 'asc' }],\n" + "				columns:[\n");

			// Ajuste: começamos a partir do segundo elemento
			for (int columnIndex = 0; columnIndex < headerColumns.length; columnIndex++) {
				htmlContent.append("					{title:'").append(headerColumns[columnIndex])
						.append("', field:'coluna").append(columnIndex).append("', headerFilter:'input'},\n");
			}

			htmlContent.append(
					"				],\n" + "			});\n" + "		</script>\n" + "	</body>\n" + "</html>");
			
			System.out.println(htmlContent.toString());
			return htmlContent.toString();
		}
	}
	
	
	
	
	
	
	

    public static void generateHTML(Schedule s) throws IOException {
        StringBuilder html = new StringBuilder();
        System.out.println(s.getFilePath());
        html.append(loadHTMLFromCSV(s.getFilePath()));

            String filePath = "Horario.html";
            
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(html.toString());
            fileWriter.close();
            System.out.println("HTML criado no ficheiro " + filePath);
  

        
    }
	
	
	
	

}
