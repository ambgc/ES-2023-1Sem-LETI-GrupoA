package leti.grupoa.projeto_es;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A classe {@code HTMLGenerator} gera um ficheiro HTML a partir de dados de um
 * objeto {@link Schedule}. Os titulos modificados podem ser especificados para
 * alterar a ordem das colunas no HTML gerado.
 */
public class HTMLGenerator {
	private Schedule s;
	private ArrayList<String> modifiedTitles = new ArrayList<>();

	/**
	 * Construtor da classe que recebe um objeto {@link Schedule} e uma lista de
	 * titulos modificados.
	 *
	 * @param s              O objeto Schedule com os dados.
	 * @param modifiedTitles A lista de titulos modificados.
	 */
	public HTMLGenerator(Schedule s, ArrayList<String> modifiedTitles) {
		this.modifiedTitles = modifiedTitles;
		this.s = s;
	}

	/**
	 * Construtor da classe que recebe apenas um objeto {@link Schedule}.
	 *
	 * @param s O objeto Schedule com os dados.
	 */
	public HTMLGenerator(Schedule s) {
		this.s = s;
		this.modifiedTitles = null;
	}

	/**
	 * obtem a lista de titulos modificados.
	 *
	 * @return A lista de titulos modificados.
	 */
	public ArrayList<String> getModifiedTitles() {
		return modifiedTitles;
	}

	/**
	 * obtem as posicoes das colunas modificadas.
	 *
	 * @return Uma lista de strings que contém as posicoes das colunas modificadas.
	 */
	public ArrayList<String> modTitlesPosition() {
		ArrayList<String> modTitlesPosition = new ArrayList<>();
		for (String modTitle : modifiedTitles) {
			int index = getColumnIndex(modTitle);
			modTitlesPosition.add(String.valueOf(index));
		}
		return modTitlesPosition;
	}

	/**
	 * Altera a ordem das colunas com base na lista especificada.
	 *
	 * @param newOrder A nova ordem das colunas.
	 */
	public void changeColumnOrder(ArrayList<String> newOrder) {
		modifiedTitles = newOrder;
	}

	// Método auxiliar para obter o índice da coluna usando a interface ColumnGetter
	private int getColumnIndex(String columnName) {
		String[] columns = s.getColumn(s.getScheduleText().get(0));
		for (int i = 0; i < columns.length; i++) {
			if (columns[i].equals(columnName)) {
				return i;
			}
		}
		throw new IllegalArgumentException("Coluna não encontrada: " + columnName);
	}

	/**
	 * Carrega os dados de um Schedule para uma representacao em HTML.
	 *
	 * @return Uma string que representa o conteudo HTML gerado.
	 */
	private String loadHTML() {
		String[] headerColumns = s.getColumn(s.getScheduleText().get(0));
		if (getModifiedTitles() != null) {
			ArrayList<String> modTitlesPositions = modTitlesPosition();
		}
		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append(HTML_HEADER);
		htmlContent.append(VAR_TABLE);
		if (getModifiedTitles() != null) {
			ArrayList<String> modTitlesPositions = modTitlesPosition();
			for (String position : modTitlesPositions) {
				int index = Integer.parseInt(position);
				htmlContent.append("					{title:'").append(headerColumns[index])
						.append("', field:'coluna").append(index).append("', headerFilter:'input'},\n");
			}
		} else {
			for (int k = 0; k < headerColumns.length; k++) {
				htmlContent.append("					{title:'").append(headerColumns[k]).append("', field:'coluna")
						.append(k).append("', headerFilter:'input'},\n");
			}
		}
		htmlContent.append(HTML_FOOTER);
		return htmlContent.toString();
	}

	/**
	 * Gera um ficheiro HTML com base nos dados do Schedule.
	 *
	 * @param name O nome do ficheiro HTML a ser gerado.
	 * @throws IOException Se ocorrer um erro de E/S durante a escrita do ficheiro.
	 */
	public void generateHTML(String name) throws IOException {
		StringBuilder html = new StringBuilder();
		html.append(loadHTML());
		String filePath = name + ".html";
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(html.toString());
		fileWriter.close();
		System.out.println("HTML criado no ficheiro " + filePath);
	}

	// Constantes HTML
	private static final String HTML_HEADER = "<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>\n" + "	<head>\n"
			+ "		<meta charset='utf-8' />\n"
			+ "		<link href='https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css' rel='stylesheet'>\n"
			+ "		<script type='text/javascript' src='https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js'></script>\n"
			+ "	</head>\n" + "	<body>\n" + "		<H1>Horarios</H1>	\n" + "		<div id='example-table'></div>\n"
			+ "\n" + "		<script type='text/javascript'>\n" + "\n" + "			var tabledata = [ \n";
	private static final String VAR_TABLE = "];\n" + "			var table = new Tabulator('#example-table', {\n"
			+ "				data:tabledata,\n" + "				layout:'fitDatafill',\n"
			+ "				pagination:'local',\n" + "				paginationSize:10,\n"
			+ "				paginationSizeSelector:[5, 10, 20, 40],\n" + "				movableColumns:true,\n"
			+ "				paginationCounter:'rows',\n"
			+ "				initialSort: [{ column: 'coluna0', dir: 'asc' }],\n" + "				columns:[\n";
	private static final String HTML_FOOTER = "				],\n" + "			});\n" + "		</script>\n"
			+ "	</body>\n" + "</html>";
}
