package leti.grupoa.projeto_es;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste para a classe TXTFile.
 */

class TXTFileTest {
	// Alterar local do ficheiro em cada maquina.

	static TXTFile f;

	/**
	 * Inicializa um novo TXTFile e Path.
	 * @throws IOException 
	 * 
	 * 
	 */

	@BeforeAll
	static void setUpBeforeClass() throws IOException {
		f = new TXTFile();
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
		//Schedule s = Schedule.toSchedule(f);
	//	s.printSchedule();
	}
}
