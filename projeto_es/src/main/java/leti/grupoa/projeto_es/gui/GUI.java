package leti.grupoa.projeto_es.gui;

import java.util.List;

import javax.swing.*;

public class GUI {
    public static List<String> newColumnTitles;
    public static String metrica;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChooseFile chooseFile = new ChooseFile();
            chooseFile.show();
        });
    }
}
