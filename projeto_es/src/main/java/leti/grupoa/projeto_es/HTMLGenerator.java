package leti.grupoa.projeto_es;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class HTMLGenerator {
	
	private Schedule s;
	
	public HTMLGenerator(Schedule s){
		
		this.s = s;
		
	}

	public String[] getColumn(String string) {
		return null;
	}

	/**
	 * Carrega os dados de um Schedule para um ficheiro HTML.
	 */

	private String loadHTML() {
		String[] headerColumns = getColumn(s.getScheduleText().get(0));

		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append(htmlHeader);
		Iterator<String> rowIterator = s.getScheduleText().iterator(); // iterador de Linhas
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
	for(int k = 0;k<headerColumns.length;k++){
		htmlContent.append("					{title:'").append(headerColumns[k]).append("', field:'coluna").append(k)
				.append("', headerFilter:'input'},\n");
	}htmlContent.append(htmlFooter);

	return htmlContent.toString();

	}

	public void generateHTML() throws IOException {
		StringBuilder html = new StringBuilder();
		
		System.out.println(s.getFilePath());
		
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

	

