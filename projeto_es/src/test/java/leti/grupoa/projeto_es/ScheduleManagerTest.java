package leti.grupoa.projeto_es;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

/**
 * Classe de teste para a classe ScheduleManager.
 */
public class ScheduleManagerTest {

	// NAO ESQUECER DE MUDAR CAMINHO PARA OS FICHEIROS A TESTAR DE ACORDO COM CADA
	// MAQUINA

	private ScheduleManager manager;
	String testPath = "C:/Users/afons/Desktop/HorarioDeExemplo.csv";
	Schedule s;

	@BeforeEach
	public void setUp() throws IOException {
		manager = new ScheduleManager();
		s = new Schedule("f", testPath);
	}

	/**
	 * Teste que verifica se o metodo "add" adiciona corretamente um horario a
	 * lista.
	 */
	@Test
	public void testAddScheduleFile() {
		manager.add(s);
		manager.add(s);

		assertTrue(manager.getList().size() == 2);
	}

	/**
	 * Teste que verifica se o metodo "remove" remove corretamente um horario da
	 * lista.
	 */
	@Test
	public void testRemoveScheduleFile() {
		manager.add(s);
		manager.add(s);

		manager.remove(s);

		assertTrue(manager.getList().size() == 1);
		assertFalse(manager.getList().contains(s));
	}

	/**
	 * Teste que verifica se o metodo "clear" remove todos os horarios da lista.
	 */
	@Test
	public void testClearScheduleFiles() {
		manager.add(s);
		manager.add(s);

		manager.clear();

		assertTrue(manager.getList().isEmpty());
	}

	/**
	 * Teste que verifica se o metodo "printSchedules" imprime os horarios
	 * corretamente.
	 */
	@Test
	public void testSchedulePrint() {
		manager.add(s);
		manager.printSchedules();
	}
}
