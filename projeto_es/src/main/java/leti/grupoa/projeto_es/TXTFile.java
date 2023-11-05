package leti.grupoa.projeto_es;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TXTFile {

	private String name;
	private String path;
	private ArrayList<String> txtText;

	public TXTFile(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public ArrayList<String> getTxtText() {
		return txtText;
	}

	public void load() throws FileNotFoundException {
		txtText = new ArrayList<String>();
		Scanner s = new Scanner(new File(path));
		while (s.hasNextLine()) {
			String next = s.nextLine().replace(";", " | ");
			next = next.replace("	 ", ", ");
			if(txtText.isEmpty()) {
			txtText.add(next);
			}else {
				txtText.add("\n" + next);
			}
		}
	}

	public void print() throws FileNotFoundException {
		load();
		System.out.println(txtText + "\n");
		System.out.println(txtText.get(0));
	}

}
