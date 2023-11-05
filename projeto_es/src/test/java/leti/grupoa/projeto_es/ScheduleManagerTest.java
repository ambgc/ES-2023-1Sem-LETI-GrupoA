package leti.grupoa.projeto_es;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class ScheduleManagerTest {

	// NÃO ESQUECER DE MUDAR CAMINHO PARA OS FICHEIROS A TESTAR DE ACORDO COM CADA
	// MÁQUINA

	private ScheduleManager manager;
	String testPath = "C:/Users/afons/Desktop/HorarioDeExemplo.csv";
	Schedule s;

	@BeforeEach
	public void setUp() throws IOException {
		manager = new ScheduleManager();
		s = new Schedule("f", testPath);

	}

	@Test
	public void testAddScheduleFile() {
		manager.add(s);
		manager.add(s);

		assertTrue(manager.getList().size() == 2);
	}

	@Test
	public void testRemoveScheduleFile() {
		manager.add(s);
		manager.add(s);

		manager.remove(s);

		assertTrue(manager.getList().size() == 1);
		assertFalse(manager.getList().contains(s));
	}

	@Test
	public void testClearScheduleFiles() {
		manager.add(s);
		manager.add(s);

		manager.clear();

		assertTrue(manager.getList().isEmpty());
	}
	
	@Test
	public void testSchedulePrint() {
		manager.add(s);
		manager.printSchedules();
	}

}
