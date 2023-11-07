package leti.grupoa.projeto_es;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para a funcionalidade principal do projeto. Acolhe
 * maioritariamente funções da classe Schedule, a central do projeto, mas
 * acomoda outras que possam requisitá-la.
 */
class Testes {

	//inicialização
	static String path = ("C:/Users/afons/Desktop/HorarioDeExemplo.csv");
	static Schedule f;
	static Scanner s;

	/**
	 * Inicializa um novo Schedule e permite aceder imediatamente à configuração
	 * estática do Path deste.
	 *
	 * @throws Exception Excecao lancada se ocorrer um erro durante a inicializacao.
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		f = new Schedule("f", path);
	}

	@AfterAll
	static void tearDownAfterAll() {

		s.close();

	}

	/**
	 * Teste 1: Teste de funcao de leitura/print de ficheiro CSV. Ao mesmo tempo,
	 * verifica o funcionamento das funcoes basicas de Schedule.
	 *
	 */
	@Test
	void test1() throws IOException {
		FileReader fileReader = new FileReader(path);
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

	/**
	 * Teste 2: Teste da funcao anterior, comprimida num metodo.
	 *
	 */
	@Test
	void test2() throws IOException {

		// teste da classe (Afonso)
		System.out.println(f.loadSchedule() + "\n 2");

	}

	/**
	 * Teste 3: Teste de metodo de construcao de Schedule de forma interativa.
	 *
	 */
	@Test
	void test3() throws IOException {
		s = new Scanner(System.in);

		System.out.println("Introduza a localizacao do ficheiro CSV: ");
		String fPath = s.nextLine();
		System.out.println("Introduza o nome do ficheiro: ");
		String fName = s.nextLine();
		if (!fPath.isBlank() && !fName.isBlank()) {
			System.out.println("Ficheiro " + fName + " localizado em " + fPath + " carregado com sucesso.");
			Schedule f = new Schedule(fName, fPath);
			f.printSchedule();
		} else {
			System.out.println("Diretorio/nome invalido.");
		}

	}

	/**
	 * Teste 4: Teste da funcao anterior comprimida num metodo.
	 *
	 */
	@Test
	void test4() throws IOException {

		Schedule f = new Schedule();
		f.printSchedule();
	}
}
