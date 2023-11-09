package leti.grupoa.projeto_es;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ScheduleRepTest {

	String name = "HorarioDeExemplo.csv";
	String path = "C:/Coding/HorarioDeExemplo.csv";		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() throws IOException {

		Schedule s = new Schedule(name, path);
		ScheduleRepresent.generateHTML();
	}	

}
