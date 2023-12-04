package leti.grupoa.projeto_es;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Classe que representa um ficheiro de TXTFile (TXTFile) e fornece metodos para
 * carregar e fazer print do conteudo do ficheiro.
 */
public class TXTFile {

	private File f;
	private ArrayList<String> txtText;

	/**
	 * Construtor que cria uma instancia de TXTFile com um nome e caminho
	 * especificados.
	 *
	 * @param name Nome do ficheiro.
	 * @param path Localizacao do ficheiro.
	 * @throws IOException 
	 */
	public TXTFile() throws IOException {
		System.out.println("Por favor selecione um ficheiro de texto.");
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			f = jfc.getSelectedFile();
			System.out.println("Ficheiro selecionado: " + f.getAbsolutePath());
			load();
		}else {
			throw new IOException("Por favor selecione um ficheiro.");
		}
	}
	
	public File getFile() {
		return f;
	}

	/**
	 * Devolve o nome do ficheiro.
	 *
	 * @return O nome do ficheiro.
	 */
	public String getName() {
		return f.getName();
	}

	/**
	 * Devolve a localizacao.
	 *
	 * @return O caminho do ficheiro.
	 */
	public String getPath() {
		return f.getAbsolutePath();
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
	 * Carrega o conteudo do ficheiro TXTFile, processando-o e armazenando-o na
	 * ArrayList txtText.
	 *
	 * @throws FileNotFoundException Excecao lancada se o ficheiro .txt nao for
	 *                               encontrado no "path" dado.
	 */
	public void load() throws FileNotFoundException {
		txtText = new ArrayList<String>();
		Scanner s = new Scanner(f.getAbsoluteFile());
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
	 * @throws FileNotFoundException Excecao lancada se o ficheiro TXTFile nao for
	 *                               encontrado.
	 */
	public void print() throws FileNotFoundException {
		System.out.println(txtText + "\n");
		System.out.println(txtText.get(0));
	}
}
