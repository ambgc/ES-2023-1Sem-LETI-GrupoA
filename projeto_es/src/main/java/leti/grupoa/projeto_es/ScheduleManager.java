package leti.grupoa.projeto_es;

import java.util.ArrayList;

/**
 * Classe que representa um gestor de horarios. Permite adicionar, armazenar e
 * visualizar horarios de forma simples. Funciona como um organizador de
 * horários.
 */
public class ScheduleManager {

    private ArrayList<Schedule> scheduleManager;

    /**
     * Construtor que cria uma instancia de ScheduleManager.
     */
    public ScheduleManager() {
        scheduleManager = new ArrayList<Schedule>();
    }

    /**
     * Adiciona um objeto Schedule a lista de horarios.
     *
     * @param s O objeto Schedule a ser adicionado.
     */
    public void add(Schedule s) {
        scheduleManager.add(s);
    }

    /**
     * obtem a lista de horarios armazenados.
     *
     * @return A lista de horarios.
     */
    public ArrayList<Schedule> getList() {
        return scheduleManager;
    }

    /**
     * Remove um objeto Schedule da lista de horarios geridos.
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
     * Exibe o conteúdo do gestor de horarios, listando o nome dos horários e os
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
