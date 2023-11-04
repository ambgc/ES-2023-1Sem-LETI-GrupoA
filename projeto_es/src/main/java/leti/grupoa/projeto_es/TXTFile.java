package leti.grupoa.projeto_es;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TXTFile {

    public static void main(String[] args) {
        String arquivoCSV = "/C:/Users/Vasco/Desktop/Escola/ES/HorarioDeExemplo.csv"; // Substitua pelo caminho do seu arquivo CSV
 
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                StringBuilder linhaFormatada = new StringBuilder();
                for (String campo : csvRecord) {
                    if (linhaFormatada.length() > 0) {
                        linhaFormatada.append("| ");
                    }
                    linhaFormatada.append(campo.replace(";", "|"));
                }
                String horario = linhaFormatada.toString();
                System.out.println(horario);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}
