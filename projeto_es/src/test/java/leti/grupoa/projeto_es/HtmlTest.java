package leti.grupoa.projeto_es;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para funcoes relativas a HTML
 */

class HtmlTest implements ColumnGetter {

	static Schedule s2;
	static Schedule s;
	static TXTFile f;
	static String[] columns;
	static HTMLGenerator htmlgene;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	
		f = new TXTFile();
		s = new Schedule();
		s2 = new Schedule(f);
		
	
	}
		
		
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test1() {

		if (!s.getScheduleText().isEmpty()) { // -- 1: Obter as colunas
			List<List<String>> records = new ArrayList<>();
			String line = new String();
			String headerLine = s.getScheduleText().get(0);
			String[] columns = headerLine.split("\\|");
//			System.out.println(s.getScheduleText()); // X
			System.out.println("1- " + s.getScheduleText().get(0)); // X
			System.out.println("2- " + headerLine); // X
			for (int i = 0; i < columns.length; i++) {
				columns[i] = columns[i].trim();
				System.out.println(columns[i]); // X
			}
		}
	}

	public String[] getColumn() {
		return null;
	}

	@Test
	void test2() { // funcao de teste da interface)

		Schedule s2 = new Schedule(f);

		String[] columns = getColumn(s.getScheduleText().get(1));
		System.out.println("test2");
		for (int i = 0; i < columns.length; i++) {
			columns[i] = columns[i].trim();
			System.out.println(columns[i]); // X
		}

	}

	public String htmltest() {
		String[] headerColumns = getColumn(s.getScheduleText().get(0));

		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>\n" + "	<head>\n"
				+ "		<meta charset='utf-8' />\n"
				+ "		<link href='https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css' rel='stylesheet'>\n"
				+ "		<script type='text/javascript' src='https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js'></script>\n"
				+ "	</head>\n" + "	<body>\n" + "		<H1>Horários</H1>	\n"
				+ "		<div id='example-table'></div>\n" + "\n" + "		<script type='text/javascript'>\n" + "\n"
				+ "			var tabledata = [ \n");

		Iterator<String> rowIterator = s.getScheduleText().iterator(); // iterador de Linhas
		String[] aux;
		String[] input;
		int i = 0;
		while (rowIterator.hasNext()) { // irá iterar enquanto houver linhas
			htmlContent.append("\t{");
			aux = getColumn(rowIterator.next());
			for (int j = 0; j < aux.length; j++) {
				// System.out.println("coluna" + i + ": " + aux[j]);

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

		} // -----


		htmlContent.append("];\n" + "			var table = new Tabulator('#example-table', {\n"
				+ "				data:tabledata,\n" + "				layout:'fitDatafill',\n"
				+ "				pagination:'local',\n" + "				paginationSize:10,\n"
				+ "				paginationSizeSelector:[5, 10, 20, 40],\n" + "				movableColumns:true,\n"
				+ "				paginationCounter:'rows',\n"
				+ "				initialSort: [{ column: 'coluna0', dir: 'asc' }],\n" + "				columns:[\n");

		// Ajuste: começamos a partir do segundo elemento
		for (int k = 0; k < headerColumns.length; k++) {
			htmlContent.append("					{title:'").append(headerColumns[k])
					.append("', field:'coluna").append(k).append("', headerFilter:'input'},\n");
		}

		htmlContent
				.append("				],\n" + "			});\n" + "		</script>\n" + "	</body>\n" + "</html>");

		System.out.println(htmlContent.toString());
		return htmlContent.toString();
	}

	@Test
	void test3() throws IOException {
		String filePath = "Horario.html";
		String a = htmltest();
		

		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(a.toString());
		fileWriter.close();
		System.out.println("HTML criado no ficheiro " + filePath);

	}
	
	
	
}
