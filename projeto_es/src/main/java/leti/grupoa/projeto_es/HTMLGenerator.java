package leti.grupoa.projeto_es;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HTMLGenerator {

	private Schedule s;
	private List<String> modifiedTitles = new ArrayList<>();


	public HTMLGenerator(Schedule s,List<String> modifiedTitles ) {
        this.modifiedTitles = modifiedTitles;
		this.s = s;

	}

	public List<String> modTiltesPosition() {
		   List<String> modTitlesPosition = new ArrayList<>();

	        for (String modTitle : modifiedTitles) {
	            // Usando a implementação específica da interface
	            int index = getColumnIndex( modTitle);
	            modTitlesPosition.add(String.valueOf(index));
	        }

	        return modTitlesPosition;
	}

	 public void changeColumnOrder(List<String> newOrder) {
	        modifiedTitles = newOrder;
	    }
	
	// Método auxiliar para obter o índice da coluna usando a interface ColumnGetter
    private int getColumnIndex( String columnName) {
        String[] columns = s.getColumn();
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equals(columnName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Column not found: " + columnName);
    }
    
	/**
	 * Carrega os dados de um Schedule para um ficheiro HTML.
	 */
    private String loadHTML() {
        String[] headerColumns = s.getColumn(s.getScheduleText().get(0));
        List<String> modTitlesPositions = modTiltesPosition(); // Obtém as posições dos títulos modificados

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append(HTML_HEADER);

        Iterator<String> rowIterator = s.getScheduleText().iterator();
        String[] aux;
        int i = 0;
        while (rowIterator.hasNext()) {
            htmlContent.append("\t{");
            aux = s.getColumn(rowIterator.next());
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

        htmlContent.append(VAR_TABLE);

        // Adiciona as colunas na ordem correta dos títulos modificados
        for (String position : modTitlesPositions) {
            int index = Integer.parseInt(position);
            htmlContent.append("					{title:'").append(headerColumns[index])
                    .append("', field:'coluna").append(index).append("', headerFilter:'input'},\n");
        }

        htmlContent.append(HTML_FOOTER);

        return htmlContent.toString();
    }

	/**
	 * Este método chama o método loadHTML para obter o conteúdo HTML, cria um
	 * arquivo chamado "Horario.html" e escreve o conteúdo HTML no arquivo. Ele
	 * trata exceções do tipo IOException.
	 **/

	public void generateHTML() throws IOException {
		
		// 1. Construir a representação HTML
		StringBuilder html = new StringBuilder();
		System.out.println(s.getFilePath());

		// 2. Especificar o caminho do arquivo HTML
		html.append(loadHTML());
		String filePath = "Horario.html";

		 // 3. Escrever a representação HTML no arquivo
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(html.toString());
		fileWriter.close();
		System.out.println("HTML criado no ficheiro " + filePath);

	}

	/// --- Strings HTML --- ///

	 // Constantes HTML
    private static final String HTML_HEADER = "<html lang='en' xmlns='http://www.w3.org/1999/xhtml'>\n" +
            "	<head>\n" +
            "		<meta charset='utf-8' />\n" +
            "		<link href='https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css' rel='stylesheet'>\n" +
            "		<script type='text/javascript' src='https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js'></script>\n" +
            "	</head>\n" +
            "	<body>\n" +
            "		<H1>Horários</H1>	\n" +
            "		<div id='example-table'></div>\n" +
            "\n" +
            "		<script type='text/javascript'>\n" +
            "\n" +
            "			var tabledata = [ \n";

    private static final String VAR_TABLE = "];\n" +
            "			var table = new Tabulator('#example-table', {\n" +
            "				data:tabledata,\n" +
            "				layout:'fitDatafill',\n" +
            "				pagination:'local',\n" +
            "				paginationSize:10,\n" +
            "				paginationSizeSelector:[5, 10, 20, 40],\n" +
            "				movableColumns:true,\n" +
            "				paginationCounter:'rows',\n" +
            "				initialSort: [{ column: 'coluna0', dir: 'asc' }],\n" +
            "				columns:[\n";

    private static final String HTML_FOOTER = "				],\n" +
            "			});\n" +
            "		</script>\n" +
            "	</body>\n" +
            "</html>";
}
