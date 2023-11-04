package leti.grupoa.projeto_es;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVFile {

	private String fileName;
	private String filePath;
	private CSVParser csvParser;
	private ArrayList<String> csvFile;

	public CSVFile(String fileName, String filePath) throws IOException {
		this.fileName = fileName;
		this.filePath = filePath;
		FileReader fileReader = new FileReader(filePath);
		csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
	}

	public static CSVFile createCSVFile() throws IOException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("/C:/Users/Vasco/Desktop/Escola/ES/HorarioDeExemplo.csv "); // Substituir pelo caminho do arquivo CSV
		String fPath = scanner.nextLine();
		System.out.println("HorarioDeExemplo.csv");		//Introduza o nome do ficheiro
		String fName = scanner.nextLine();
		if (!fPath.isBlank() && !fName.isBlank()) {
			System.out.println("Ficheiro " + fName + " localizado em " + fPath + " carregado com sucesso.");
			CSVFile f = new CSVFile(fName, fPath);
			scanner.close();
			return f;
		} else {
			System.out.println("Diretório ou nome inválido.");
			scanner.close();
		}
		return null;

	}

	public String getName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public CSVParser getCsvParser() {
		return csvParser;
	}

	public ArrayList<String> loadFile() {
		csvFile = new ArrayList<String>();
		for (CSVRecord csvRecord : csvParser) {
			String columns = "";
			columns = csvRecord.get(0);
			columns = columns.replace(";", " | ");
			csvFile.add(columns);

			for (int i = 0; i < 10; i++) {
				if (i > 0 && i < csvRecord.size() && !csvRecord.get(i).isBlank()) {
					columns = columns + "," + csvRecord.get(i);
				}
			}
			columns = columns.replace(";", " | ");
			csvFile.add("\n" + columns);
		}
		return csvFile;
	}

	public void readFile() {
		loadFile();
		System.out.println(csvFile);
		System.out.println("\n" + csvFile.get(0));
	}

}