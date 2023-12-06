package leti.grupoa.projeto_es;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ScheduleTest {

	static Schedule s;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		s = new Schedule();

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Teste 1: Teste de funcao de leitura/print de ficheiro CSV. Ao mesmo tempo,
	 * verifica o funcionamento das funcoes basicas de Schedule.
	 *
	 */
	@Test
	void test1() throws IOException {
		FileReader fileReader = new FileReader(s.getFilePath());
		CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
		ArrayList<String> csvFile = new ArrayList<String>();
		for (CSVRecord csvRecord : csvParser) {
			String columns = "";
			columns = csvRecord.get(0);
			columns = columns.replace(";", " | ");
			csvFile.add(columns);

			for (int i = 0; i < 10; i++) {
				if (i > 0 && i < csvRecord.size() && !csvRecord.get(i).isBlank() || !csvRecord.get(i).isEmpty()) {
					columns = columns + "," + csvRecord.get(i);
				}
			}
			columns = columns.replace(";", " | ");
			csvFile.add("\n" + columns);
		}
		System.out.println(csvFile);
		System.out.println("\n" + csvFile.get(0));
		csvParser.close();
	}

//	
//	@Test
//
//	void test2() throws IOException {
//
//		// teste da classe (Afonso)
//		String path = ("C:/Users/afons/Desktop/HorarioDeExemplo.csv");
//		Schedule f = new Schedule("F", path);
//		System.out.println(f.loadSchedule());
//
//	}

//	@Test
//  -- teste: criação de
//
//	ficheiro interativa (Alex)
//
//	void test3() throws IOException {
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.println("Introduza a localização do ficheiro CSV: ");
//		String fPath = scanner.nextLine();
//		System.out.println("Introduza o nome do ficheiro: ");
//		String fName = scanner.nextLine();
//		if (!fPath.isBlank() && !fName.isBlank()) {
//			System.out.println("Ficheiro " + fName + " localizado em " + fPath + " carregado com sucesso.");
//			Schedule f = new Schedule(fName, fPath);
//			f.printSchedule();
//		} else {
//			System.out.println("Diretório ou nome inválido.");
//		}
//		scanner.close();
//
//	}

	/**
	 * Teste 2: Teste da funcao anterior, comprimida num metodo.
	 *
	 */
	@Test
	void test2() throws IOException {

		// teste da classe (Afonso)
		System.out.println(s.getScheduleText() + "\n 2");

	}

	@Test
	void test3() throws IOException { // GERADOR ANTIGO!

	//	System.out.println(s.getScheduleText());
		s.printSchedule();
	//	s.printSchedule();

	//	TXTFile f = new TXTFile();
	//	Schedule s2 = new Schedule(f);
	//	s.printSchedule();
		s.generateHTML();
	}

}
