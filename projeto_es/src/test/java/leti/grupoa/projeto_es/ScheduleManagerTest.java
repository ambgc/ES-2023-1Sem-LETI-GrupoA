package leti.grupoa.projeto_es;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScheduleManagerTest {

    private ScheduleManager manager;

    @BeforeEach
    public void setUp() {
        manager = new ScheduleManager();
    }

    @Test											//NÃO ESQUECER DE MUDAR CAMINHOS PARA OS FICHEIROS A TESTAR  DE ACORDO COM CADA MÁQUINA
    public void testAddScheduleFile() {
        manager.addScheduleFile("/C:/Users/Vasco/Desktop/Escola/ES/HorarioDeExemplo.csv");
        manager.addScheduleFile("/C:/Users/Vasco/Desktop/Escola/ES/HorarioExemplo2.csv");

        assertTrue(manager.getScheduleFiles().size() == 2);
    }

    @Test
    public void testRemoveScheduleFile() {
    	manager.addScheduleFile("/C:/Users/Vasco/Desktop/Escola/ES/HorarioDeExemplo.csv");
        manager.addScheduleFile("/C:/Users/Vasco/Desktop/Escola/ES/HorarioExemplo2.csv");

        manager.removeScheduleFile("/C:/Users/Vasco/Desktop/Escola/ES/HorarioDeExemplo.csv");

        assertTrue(manager.getScheduleFiles().size() == 1);
        assertFalse(manager.getScheduleFiles().contains("/C:/Users/Vasco/Desktop/Escola/ES/HorarioDeExemplo.csv"));
    }

    @Test
    public void testClearScheduleFiles() {
        manager.addScheduleFile("/C:/Users/Vasco/Desktop/Escola/ES/HorarioDeExemplo.csv");
        manager.addScheduleFile("/C:/Users/Vasco/Desktop/Escola/ES/HorarioExemplo2.csv");

        manager.clearScheduleFiles();

        assertTrue(manager.getScheduleFiles().isEmpty());
    }

    // Add more test cases for other methods as needed

}
