package leti.grupoa.projeto_es;

import java.util.ArrayList;

public class main {

	static ArrayList<String> mt = new ArrayList<>();
	static Schedule s;
	static TXTFile f;
	static Schedule s2;

	public static void initialize(Schedule s) {

		String[] firstLine = s.getColumn(s.getScheduleText().get(0));
		for (int i = 0; i < firstLine.length; i++) {
			mt.add(firstLine[i]);
		}

		String aux = mt.get(0);
		String aux2 = mt.get(mt.size() - 1);
		mt.remove(mt.size() - 1);
		mt.add(mt.size() - 1, aux);
		mt.remove(mt.get(0));
		mt.add(0, aux2);
	}

	static ScheduleManager sm = new ScheduleManager();

	public static void main(String[] args) throws Exception {

		TXTFile f = new TXTFile();
		s = new Schedule();
		s2 = new Schedule(f);

		System.out.println("1. Criar um horário (1) - CSV");
		System.out.println("\n5. Imprimir horário (1)");
		s2.printSchedule();

		System.out.println("\n6. Adicionar os horários ao gestor");
		sm.add(s);
		sm.add(s2);
		sm.printSchedules();

		System.out.println("\n8. Remover um dos horários do gestor");
		sm.count();
		sm.remove(s);
		System.out.println("A remover...");
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

		System.out.println("\n10. Executar horário em HTML (1 - Sem alterações às colunas)");

		HTMLGenerator hg1 = new HTMLGenerator(s);

		hg1.generateHTML("Horario");

		System.out.println("\n10. Executar horário em HTML (2 - Com a ordem das colunas alerada)");
		initialize(s); // função aux para não encher demasiado a main
		HTMLGenerator hg2 = new HTMLGenerator(s, mt);
		hg2.generateHTML("HorarioModificado");

		System.out.println("\n11. Aplicar métricas a Salas (manualmente)");
		Sala s1 = new Sala("AA2.23");
		Sala s2 = new Sala("C1.01");

		Metrica m1 = new Metrica("Capacidade diff.", "Capacidade Normal - 14 * No caracteristicas + Capacidade Exame");
		System.out.println(m1.getFormula());
		m1.addClass(s1);
		m1.addClass(s2);
		s1.caractChecker();
		m1.getQuality();
		
		
		
	}
	
	
}
