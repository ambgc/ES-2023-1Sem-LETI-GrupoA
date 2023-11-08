package leti.grupoa.projeto_es;

import java.io.FileWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ScheduleRepresent {

	private Schedule targetSchedule;
	
	ScheduleRepresent(Schedule s ){
		
		targetSchedule = s;
		
	} 
	
	public Schedule getSchedule() {
		return targetSchedule;
	}
	
	
	
	public static String htmlData() {
			//Aqui irá nao esta info de exemplo mas sim do CSV
		String tableData = "{id:1, curso:\"Billy Bob\", uc:\"12\", turno:\"male\", turma:1, inscritos:\"red\", dia:\"2\", h_inicio:1, h_fim:\"12\", data:\"14\", caract:\"grande\", sala:\"12\"},\r\n"
				+ "    {id:2, curso:\"Astrofisica\", uc:\"12\", turno:\"male\", turma:1, inscritos:\"red\", dia:\"2\", h_inicio:1, h_fim:\"12\", data:\"14\", caract:\"grande\", sala:\"12\"},";

		return tableData;
        
    }
	
	
	public static String generateTabulatorTemplate(String tableData) {
        String template = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <link href=\"https://cdnjs.cloudflare.com/ajax/libs/tabulator/5.1.5/css/tabulator.min.css\" rel=\"stylesheet\">\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/tabulator/5.1.5/js/tabulator.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div id=\"example-table\"></div>\n" +
                "\n" +
                "<script>\n" +
                "    var tableData = [" + tableData + "];\n" +
                "    var table = new Tabulator(\"#example-table\", {\n" +
                "        data: tableData,\n" +
                "        layout: \"fitDataFill\",\n" +
                "        columns: [\n" +
                "            { title: \"Curso\", field: \"curso\" },\n" +
                "            { title: \"UC\", field: \"uc\" },\n" +
                "            { title: \"Turno\", field: \"turno\" },\n" +
                "            { title: \"Turma\", field: \"turma\" },\n" +
                "            { title: \"Inscritos no Turno\", field: \"inscritos\" },\n" +
                "            { title: \"Dia\", field: \"dia\" },\n" +
                "            { title: \"Início da Aula\", field: \"h_inicio\" },\n" +
                "            { title: \"Fim da Aula\", field: \"h_fim\" },\n" +
                "            { title: \"Data da Aula\", field: \"data\" },\n" +
                "            { title: \"Características da Sala Relevantes à Aula\", field: \"caract\" },\n" +
                "            { title: \"Sala Atribuída\", field: \"sala\" }\n" +
                "        ],\n" +
                "    });\n" +
                "</script>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        return template;
    }
	

    public static void generateHTML() {
        StringBuilder html = new StringBuilder();

        html.append(generateTabulatorTemplate(htmlData()));
        
        try {
            // Specify the file path where you want to save the HTML file
            String filePath = "Horario.html";
            
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(html.toString());
            fileWriter.close();
            System.out.println("HTML file created successfully at " + filePath);
        } catch (IOException e) {

        }

        
    }
    
        
	}
	
	

