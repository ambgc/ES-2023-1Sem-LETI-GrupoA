package leti.grupoa.projeto_es;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;

class Testes { // <- Classe principalmente para testes de Schedule, mas acolhe todas.

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

//	@Test
//
//	void test1() throws IOException {
//	-- (Afonso)
//		String path = ("C:/Users/afons/Desktop/HorarioDeExemplo.csv");
//		CSVFile f = new CSVFile("f", path);
//		CSVParser csvParser = f.getCsvParser();
//		
//		//load 
//		ArrayList<String> csvFile = new ArrayList<String>();
//		for (CSVRecord csvRecord : csvParser) {
//			String columns = "";
//			columns = csvRecord.get(0);
//			columns = columns.replace(";", " | ");
//			csvFile.add(columns);
//
//			for (int i = 0; i < 10; i++) {
//				if (i > 0 && i < csvRecord.size() && !csvRecord.get(i).isBlank() || !csvRecord.get(i).isEmpty()) {
//					columns = columns + "," + csvRecord.get(i);
//				}
//			}
//			columns = columns.replace(";", " | ");
//			csvFile.add("\n" + columns);
//		}
//		//read
//		System.out.println(csvFile);
//		System.out.println("\n" + csvFile.get(0));
//	}
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
	@Test
	// -- teste: teste de função (Alex)
	void test4() throws IOException {

		Schedule f = new Schedule();
		f.printSchedule();

	}

}