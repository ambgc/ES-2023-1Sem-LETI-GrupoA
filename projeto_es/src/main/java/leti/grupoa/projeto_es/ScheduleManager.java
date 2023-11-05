package leti.grupoa.projeto_es;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.Scanner;

public class ScheduleManager { // Resumo da Classe ScheduleManager. Tem métodos para adicionar, listar e
								// visualizar horários de uma forma simples.
								// Funciona como um organizador de horários

	private ArrayList<Schedule> scheduleManager;

	public ScheduleManager() {
		scheduleManager = new ArrayList<Schedule>();
	}

	// Adicionar um caminho de arquivo de horário à lista
	public void add(Schedule s) {
		scheduleManager.add(s);
	}

	// Obter a lista de arquivos de horário
	public ArrayList<Schedule> getList() {
		return scheduleManager;
	}

	// Remover um arquivo de horário da lista
	public void remove(Schedule s) {
		scheduleManager.remove(s);
	}

	// Limpar todos os arquivos de horário da lista
	public void clear() {
		scheduleManager.clear();
	}

	// Exibir o conteúdo do gestor de horários
	public void printSchedules() {

		int i = 1;
		for (Schedule s : scheduleManager) {
			System.out.println("Schedule " + i + ": " + s.getName());
			s.printSchedule();
			i++;
		}

	}

}
