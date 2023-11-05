package leti.grupoa.projeto_es;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TXTFileTest {
	//Alterar local de ficheiro em cada máquina.

	static String testPath = ("C:/Users/afons/Desktop/horario exemplo.txt");
	static TXTFile f;

	@BeforeAll

	static void setUpBeforeClass() {
		f = new TXTFile("ficheiro", testPath);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test //teste de classe TXT, teste de conversão TXT em "Schedule"
	void test() throws FileNotFoundException {

		f.print();
		Schedule s = Schedule.toSchedule(f);
		s.printSchedule();
		
	}

}
