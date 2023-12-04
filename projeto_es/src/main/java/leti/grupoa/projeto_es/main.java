package leti.grupoa.projeto_es;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {

		System.out.println("1. Criar um horário (1) - CSV");
		Schedule s = new Schedule();

		System.out.println("\n2. Criar um horário (2) - Ficheiro de texto");
		Schedule s2 = new Schedule(new TXTFile());

		System.out.println("\n3. Criar um gestor de Horários");
		ScheduleManager sm = new ScheduleManager();

		System.out.println("\n4. Imprimir horário (1)");
		s.printSchedule();

		System.out.println("\n5. Imprimir horário (1)");
		s2.printSchedule();


		System.out.println("\n6. Adicionar os horários ao gestor");
		sm.add(s);
		sm.add(s2);
		sm.count();

		System.out.println("\n7. Imprimir os horários dentro do gestor");
		sm.printSchedules();

		System.out.println("\n8. Remover um dos horários do gestor");
		sm.remove(s);
		sm.count();

		System.out.println("\n9. Remover todos os horários do gestor");
		sm.add(s);
		sm.add(s2);
		sm.add(s);
		sm.add(s2);
		sm.count();
		sm.clear();
		System.out.println("A remover...");
		sm.count();
		
		System.out.println("\n10. Executar horário em HTML (1 - ficheiro .txt)");
		
		System.out.println("\n10. Executar horário em HTML (2 - ficheiro CSV)");



	}

}
