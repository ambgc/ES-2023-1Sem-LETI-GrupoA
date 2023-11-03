package leti.grupoa.projeto_es;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Testes {
	static WebDriver driver;
	private CSVParser csvParser;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test

	void test2() throws IOException {

		String csvFilePath = ("C:/Users/afons/Desktop/HorarioDeExemplo.csv");
		FileReader fileReader = new FileReader(csvFilePath);

		csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

		for (CSVRecord csvRecord : csvParser) {
			String columns = "";
			for (int i = 0; i < 10; i++) {
				if (i >= 0 && i < csvRecord.size()) {
					if (columns.isEmpty()) {
						columns = csvRecord.get(i);
					} else {
						columns = columns + "," + csvRecord.get(i);
					}
				}

			}
			columns = columns.replace(";", ", ");
			System.out.println(columns);
		}

	}
}
