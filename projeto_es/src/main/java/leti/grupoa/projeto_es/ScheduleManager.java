package leti.grupoa.projeto_es;

import java.util.ArrayList;

/**
 * Classe que representa um gestor de horários. Permite adicionar, armazenar e
 * visualizar horários de forma simples. Funciona como um organizador de
 * horários.
 */
public class ScheduleManager {

    private ArrayList<Schedule> scheduleManager;

    /**
     * Construtor que cria uma instância de ScheduleManager.
     */
    public ScheduleManager() {
        scheduleManager = new ArrayList<Schedule>();
    }

    /**
     * Adiciona um objeto Schedule à lista de horários.
     *
     * @param s O objeto Schedule a ser adicionado.
     */
    public void add(Schedule s) {
        scheduleManager.add(s);
    }

    /**
     * Obtém a lista de horários armazenados.
     *
     * @return A lista de horários.
     */
    public ArrayList<Schedule> getList() {
        return scheduleManager;
    }

    /**
     * Remove um objeto Schedule da lista de horários geridos.
     *
     * @param s O objeto Schedule a ser removido.
     */
    public void remove(Schedule s) {
        scheduleManager.remove(s);
    }

    /**
     * Remove todos os objetos Schedule da lista, limpando o gestor.
     */
    public void clear() {
        scheduleManager.clear();
    }

    public void count() {
    	int i = 0;
    	for(Schedule s : scheduleManager) {
    		i++;
    	}
    	System.out.println("Há "+ i + " horários guardados no gestor.");
    }
    /**
     * Exibe o conteúdo do gestor de horários, listando o nome dos horários e os
     * seus dados.
     */
    public void printSchedules() {
        int i = 1;
        for (Schedule s : scheduleManager) {
            System.out.println("---- Horário " + i + ": " + s.getName());
            s.printSchedule();
            i++;
        }
    }
}
