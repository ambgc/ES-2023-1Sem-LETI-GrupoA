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

public class ScheduleManager {					// Resumo da Classe ScheduleManager. Tem métodos para adicionar, listar e visualizar horários de uma forma simples. 
												// Funciona como um organizador de horários 


    private List<String> scheduleFiles;
    private ArrayList<String> csvFile;

    public ScheduleManager() {
        scheduleFiles = new ArrayList<>();
    }

    // Adicionar um caminho de arquivo de horário à lista
    public void addScheduleFile(String filePath) {
        scheduleFiles.add(filePath);
    }

    // Obter a lista de arquivos de horário
    public List<String> getScheduleFiles() {
        return scheduleFiles;
    }

    // Remover um arquivo de horário da lista
    public void removeScheduleFile(String filePath) {
        scheduleFiles.remove(filePath);
    }

    // Limpar todos os arquivos de horário da lista
    public void clearScheduleFiles() {
        scheduleFiles.clear();
    }

    // Carregar e exibir o conteúdo de um arquivo de horário
    public void loadAndDisplaySchedule(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            csvFile = new ArrayList<String>();
            for (CSVRecord csvRecord : csvParser) {
                String columns = csvRecord.get(0);
                columns = columns.replace(";", " | ");
                csvFile.add(columns);

                for (int i = 0; i < 10; i++) {
                    if (i > 0 && i < csvRecord.size() && !csvRecord.get(i).isBlank()) {
                        columns = columns + "," + csvRecord.get(i);
                    }
                }
                columns = columns.replace(";", " | ");
                csvFile.add("\n" + columns);
            }
            displaySchedule(csvFile);
        }
    }

    // Exibir o conteúdo do arquivo de horário
    public void displaySchedule(ArrayList<String> schedule) {
        for (String line : schedule) {
            System.out.println(line);
        }
    }

}
