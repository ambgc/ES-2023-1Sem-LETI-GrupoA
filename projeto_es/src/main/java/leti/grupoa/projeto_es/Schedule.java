package leti.grupoa.projeto_es;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Schedule {

	private String name;
	private String path;
	private CSVParser csvParser;
	private ArrayList<String> scheduleText;

	public Schedule(String fileName, String filePath) throws IOException {
		this.name = fileName;
		this.path = filePath;
		FileReader fileReader = new FileReader(filePath);
		csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
		loadSchedule();
	}

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

	private Schedule(String fileName, String filePath, ArrayList<String> scheduleText) {
		name = fileName;
		path = filePath;
		csvParser = null;
		this.scheduleText = scheduleText;

	}

	public String getName() {
		return name;
	}

	public String getFilePath() {
		return path;
	}

	public CSVParser getCsvParser() {
		return csvParser;
	}

	public static Schedule toSchedule(TXTFile f) {
		Schedule s = new Schedule(f.getName(), f.getPath(), f.getTxtText());
		return s;
	}

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

	public void printSchedule() {
		System.out.println(scheduleText);
		System.out.println("\n" + scheduleText.get(0));
	}

	public ArrayList<String> getScheduleText() {
		return scheduleText;
	}

}