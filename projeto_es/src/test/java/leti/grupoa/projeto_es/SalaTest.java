package leti.grupoa.projeto_es;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes para funcoes relativas a classe Sala.
 */

class SalaTest {

	private File f;
	private CSVParser csvParser;
	private ArrayList<String> allCaracteristicas = new ArrayList<>();
	private ArrayList<String> myCaracteristicas = new ArrayList<>();
	private ArrayList<String> aux = new ArrayList<>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	/**
	 * Método de teste que solicita ao usuário a seleção de um ficheiro de Salas, inicializa o parser CSV
	 * e o FileReader para o arquivo selecionado.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saída durante a seleção ou inicialização do parser.
	 */
	void test() throws IOException {
	    System.out.println("Por favor, selecione um ficheiro de Salas.");
	    JFileChooser jfc = new JFileChooser();

	    // Verifica se o usuário selecionou um arquivo.
	    if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        f = jfc.getSelectedFile();
	        System.out.println("Ficheiro selecionado: " + f.getAbsolutePath());

	        // Inicializa um FileReader e um CSVParser para o arquivo selecionado.
	        FileReader fr = new FileReader(f.getAbsolutePath());
	        csvParser = new CSVParser(fr, CSVFormat.DEFAULT);
	    } else {
	        // Lança uma exceção se o usuário não selecionar um arquivo.
	        throw new IOException("Por favor, selecione um ficheiro.");
	    }
	}

	@Test
	/**
	 * Método de teste que simula a leitura de um arquivo CSV e adiciona as linhas à lista auxiliar.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saída durante o teste.
	 */
	void test1() throws IOException {
	    // Chama o método de teste anterior para simular a seleção do arquivo e inicialização do parser.
	    test();

	    // Itera sobre as linhas do arquivo CSV usando CSVRecord.
	    for (CSVRecord csvRecord : csvParser) {
	        // Obtém a linha atual do CSV.
	        String currLine = csvRecord.get(0);

	        // Adiciona a linha à lista auxiliar, considerando quebra de linha se necessário.
	        if (aux.size() == 0) {
	            aux.add(currLine);
	        } else {
	            aux.add("\n" + currLine);
	        }
	    }
	}

	@Test
	/**
	 * Método de teste que simula a leitura de características e as adiciona à lista geral de características.
	 * Imprime informações sobre a lista de características.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saída durante o teste.
	 */
	void test2() throws IOException {
	    // Chama o método de teste anterior para simular a leitura do arquivo CSV.
	    test1();

	    // Divide a primeira linha da lista auxiliar usando ";" como delimitador.
	    String[] aux2 = aux.get(0).split(";");

	    // Adiciona as características à lista geral de características.
	    for (int i = 0; i < aux2.length; i++) {
	        allCaracteristicas.add(aux2[i]);
	    }

	    // Imprime a lista geral de características atualizada.
	    System.out.println(allCaracteristicas);
	}

	
	
	@Test
	/**
	 * Método de teste que simula a pesquisa e processamento de características para uma sala específica.
	 * Imprime informações sobre as características processadas.
	 *
	 * @throws IOException Se ocorrer um erro de entrada/saída durante o teste.
	 */
	void test3() throws IOException {
	    // No cenário real (não teste), este método deveria levar a sala como argumento.
	    test2();

	    // Sala a ser pesquisada (simulada no teste).
	    String name = "D1.07";
	    String caractSala = "";

	    // Procura a sala na lista auxiliar e obtém suas características.
	    for (int i = 1; i < aux.size(); i++) {
	        if (aux.get(i).contains(name)) {
	            caractSala = aux.get(i);
	        }
	    }

	    // Imprime as características da sala.
	    System.out.println(caractSala);

	    // Processa as características se a sala não estiver vazia.
	    if (!caractSala.isEmpty()) {
	        // Adiciona elementos vazios para as duas primeiras posições da lista.
	        myCaracteristicas.add(0, "");
	        myCaracteristicas.add(1, "");

	        // Divide as características da sala usando ";" como delimitador.
	        String[] splitColumn = caractSala.split(";");
	        
	        // Adiciona as características processadas à lista.
	        for (int i = 2; i < splitColumn.length; i++) {
	            if (!splitColumn[i].isEmpty()) {
	                myCaracteristicas.add(i, splitColumn[i]);
	            } else {
	                myCaracteristicas.add(i, "");
	            }
	        }

	        // Imprime as características processadas.
	        System.out.println(myCaracteristicas);
	    }
	}

	@Test
	/**
	 * Método de teste que verifica a presença de uma característica específica nas listas de características.
	 * Imprime informações sobre as características e retorna um valor inteiro indicando o resultado.
	 *
	 * @return 1 se a característica "Sala Aulas Mestrado" estiver presente e marcada com "X",
	 *         0 se a característica não estiver presente ou não estiver marcada.
	 * @throws IOException Se ocorrer um erro de entrada/saída durante o teste.
	 */
	int test4() throws IOException {
	    // Faz com que o input seja uma característica qualquer.
	    test3();

	    // Itera sobre as listas de características.
	    for (int i = 0; i < allCaracteristicas.size(); i++) {
	        // Verifica se a característica é "Sala Aulas Mestrado" e está marcada com "X".
	        if (allCaracteristicas.get(i).equals("Sala Aulas Mestrado") && myCaracteristicas.get(i).equals("X")) {
	            System.out.println(allCaracteristicas.get(i) + ": " + myCaracteristicas.get(i));
	            return 1;
	        } else if (!myCaracteristicas.get(i).isEmpty()) {
	            // Imprime que possui a característica, mas não é a específica procurada.
	            System.out.println("Possui: " + allCaracteristicas.get(i));
	        }
	    }
	    return 0;
	}
	
	
	@Test
	/**
	 * Método de teste que cria uma instância da classe Sala, verifica e imprime valores associados a características específicas.
	 *
	 * @throws Exception Se ocorrer um erro não especificado durante a execução do teste.
	 */
	void test5() throws Exception {
	    // Cria uma instância da classe Sala.
	    Sala s = new Sala("AA3.24");
	    
	    // Verifica e imprime informações sobre a sala "AA3.24".
	    s.caractChecker();
	    
	    // Imprime o valor associado à característica "No caracteristicas".
	    System.out.println("Numero de Características" + s.getCaracValue("No caracteristicas"));
	    
	    // Imprime o valor associado à característica "Anfitieatro".
	    System.out.println(s.getCaracValue("Anfitieatro"));
	}

	
	@Test
	/**
	 * Método de teste que cria uma instância da classe Sala, verifica e imprime informações sobre características específicas.
	 *
	 * @throws Exception Se ocorrer um erro não especificado durante a execução do teste.
	 */
	void test6() throws Exception {
	    // Cria uma instância da classe Sala.
	    Sala s = new Sala("AA3.24");
	    
	    // Verifica e imprime informações sobre a sala "AA3.24".
	    s.caractChecker();
	    
	    // Imprime se a sala possui a característica "Laboratorio de Telecomunicacoes".
	    System.out.println(s.hasCarac("Laboratorio de Telecomunicacoes"));
	    
	    // Imprime se a sala possui a característica "Sala Aulas Mestrado".
	    System.out.println(s.hasCarac("Sala Aulas Mestrado"));
	    
	    // Imprime se a sala possui a característica "Exemplo de Input Incorreto".
	    System.out.println(s.hasCarac("Exemplo de Input Incorreto"));
	}
	
}