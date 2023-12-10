package leti.grupoa.projeto_es.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColumnOrganizer extends JFrame implements PageConfigurable {

    private DefaultListModel<String> columnListModel;
    private JList<String> columnList;
   
  

    public ColumnOrganizer(List<String> initialColumnTitles) {
        createPage(this, new Color(75, 99, 103), "Organizador de Colunas");     
        initialize(initialColumnTitles);
        
    }

    private void initialize(List<String> initialColumnTitles) {
    	
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(75, 99, 103));
        add(mainPanel);
        initializeColumnList(initialColumnTitles);
    }

    private void initializeColumnList(List<String> columnTitles) {
        for (String columnTitle : columnTitles) {
            columnListModel.addElement(columnTitle);
        }
    }

    @Override
    public void initComponents() {
        // No additional components to initialize for this example
    }

    @Override
    public void setupLayout(JFrame frame) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(75, 99, 103));

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(new Color(75, 99, 103)); 
        
        columnListModel = new DefaultListModel<>();
        columnList = new JList<>(columnListModel);
        JScrollPane scrollPane = new JScrollPane(columnList);
        scrollPane.setPreferredSize(new Dimension(10, 200));
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttuns Panel (cima e baixo)
        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBackground(new Color(75, 99, 103));

        JButton moveUpButton = defineButtonLayout(new Color(184, 216, 216), Color.BLACK, "Mover Para Cima", new Dimension(130, 50));
        moveUpButton.addActionListener(e -> moveSelectedColumn(-1));

        JButton moveDownButton = defineButtonLayout(new Color(184, 216, 216), Color.BLACK, "Mover Para Baixo", new Dimension(130, 50));
        moveDownButton.addActionListener(e -> moveSelectedColumn(1));
                
        buttonPanel.add(moveUpButton);
        buttonPanel.add(moveDownButton);
        
     // Options Panel (Confirmar and Voltaratras)
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        optionsPanel.setBackground(new Color(75, 99, 103));

        JButton Confirmar = defineButtonLayout(new Color(122, 158, 159), Color.BLACK, "Confirmar", new Dimension(130, 50));
        Confirmar.addActionListener(e -> performConfirmAction());
        JButton Voltaratras = defineButtonLayout(new Color(122, 158, 159), Color.BLACK, "Voltar atrás", new Dimension(130, 50));
        Voltaratras.addActionListener(e -> performGoBackAction());
        
        optionsPanel.add(Confirmar);
        optionsPanel.add(Voltaratras);

        mainPanel.add(createInstructionsPanel(), BorderLayout.NORTH); // New panel for instructions
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(optionsPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        frame.add(mainPanel, gbc);
    }

    private JPanel createInstructionsPanel() {
        JPanel instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.setBackground(new Color(75, 99, 103));

        JTextArea instructionsText = defineTextAreaLayout(
                "Ordene as colunas selecionando uma coluna e clicando em 'Mover para Cima' ou 'Mover para Baixo'.\n" +
                        "A ordem que definir aqui será refletida no HTML final.",
                "Arial", Font.PLAIN, 15, new Color(75, 99, 103), Color.BLACK);

        instructionsPanel.add(instructionsText, BorderLayout.CENTER);

        return instructionsPanel;
    }

    @Override
    public void setupListeners() {
        // No additional listeners to set up for this example
    }

    private void moveSelectedColumn(int direction) {
        int selectedIndex = columnList.getSelectedIndex();
        if (selectedIndex != -1) {
            int newIndex = selectedIndex + direction;
            if (newIndex >= 0 && newIndex < columnListModel.getSize()) {
                String selectedValue = columnListModel.remove(selectedIndex);
                columnListModel.add(newIndex, selectedValue);
                columnList.setSelectedIndex(newIndex);
            }
        }
    }

    public void performConfirmAction() {
        // Implement the logic for the "Confirmar" button
        // This method is called when the "Confirmar" button is clicked

    	GUI.newColumnTitles = getColumnTitles();

        // Print the column titles to the console
        System.out.println("Column Titles: " + getColumnTitles());

        // If you want to do something else with the column titles, add the logic here
    }

    public void performGoBackAction() {
    	ChooseFile chooseFilePage = new ChooseFile();
        chooseFilePage.setVisibility(chooseFilePage, true);

        dispose(); // Close the current frame
    }

    public List<String> getColumnTitles() {
        List<String> columnTitles = new ArrayList<>();
        for (int i = 0; i < columnListModel.getSize(); i++) {
            columnTitles.add(columnListModel.get(i));
        }
        return columnTitles;
    }

    
}
