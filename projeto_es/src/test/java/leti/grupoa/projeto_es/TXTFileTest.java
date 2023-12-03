package leti.grupoa.projeto_es;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste para a classe TXTFile.
 */

class TXTFileTest {
	// Alterar local do ficheiro em cada maquina.

	static String testPath;
	static TXTFile f;

	/**
	 * Inicializa um novo TXTFile e Path.
	 * 
	 * 
	 */

	@BeforeAll
	static void setUpBeforeClass() {
		testPath = ("C:/Users/afons/Desktop/horario exemplo.txt");
		f = new TXTFile("ficheiro", testPath);
	}

	/**
	 * Teste que verifica a funcionalidade da classe TXTFile, incluindo a conversao
	 * de TXTFile em "Schedule".
	 *
	 * @throws FileNotFoundException Excecao lancada se o ficheiro nao for
	 *                               encontrado.
	 */
	@Test
	void test() throws FileNotFoundException {
		f.print();
		Schedule s = Schedule.toSchedule(f);
		s.printSchedule();
	}
}
