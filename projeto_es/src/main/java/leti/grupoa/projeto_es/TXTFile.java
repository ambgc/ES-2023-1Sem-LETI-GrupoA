package leti.grupoa.projeto_es;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa um ficheiro de TXTFile (TXTFile) e fornece metodos para carregar e fazer print do conteudo do ficheiro.
 */
public class TXTFile {

    private String name;
    private String path;
    private ArrayList<String> txtText;

    /**
     * Construtor que cria uma instancia de TXTFile com um nome e caminho especificados.
     *
     * @param name Nome do ficheiro.
     * @param path Localizacao do ficheiro.
     */
    public TXTFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    /**
     * Devolve o nome do ficheiro.
     *
     * @return O nome do ficheiro.
     */
    public String getName() {
        return name;
    }

    /**
     * Devolve a localizacao.
     *
     * @return O caminho do ficheiro.
     */
    public String getPath() {
        return path;
    }

    /**
     * Devolve o ArrayList de strings do objeto.
     *
     * @return O conteudo do ficheiro TXTFile.
     */
    public ArrayList<String> getTxtText() {
        return txtText;
    }

    /**
     * Carrega o conteudo do ficheiro TXTFile, processando-o e armazenando-o na ArrayList txtText.
     *
     * @throws FileNotFoundException Excecao lancada se o ficheiro .txt nao for encontrado no "path" dado.
     */
    public void load() throws FileNotFoundException {
        txtText = new ArrayList<String>();
        Scanner s = new Scanner(new File(path));
        while (s.hasNextLine()) {
            String next = s.nextLine().replace(";", " | ");
            next = next.replace("	 ", ", ");
            if (txtText.isEmpty()) {
                txtText.add(next);
            } else {
                txtText.add("\n" + next);
            }
        }
    }

    /**
     * Carrega e imprime o conteudo do TXTFile.
     *
     * @throws FileNotFoundException Excecao lancada se o ficheiro TXTFile nao for encontrado.
     */
    public void print() throws FileNotFoundException {
        load();
        System.out.println(txtText + "\n");
        System.out.println(txtText.get(0));
    }
}
