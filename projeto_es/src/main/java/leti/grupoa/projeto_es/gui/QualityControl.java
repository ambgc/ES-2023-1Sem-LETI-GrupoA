package leti.grupoa.projeto_es.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QualityControl extends JFrame implements PageConfigurable {

    private JTextArea resultTextArea;

    public QualityControl() {
        initialize();
    }

    private void initialize() {
        createPage(this, new Color(75, 99, 103), "Quality Control");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(75, 99, 103));

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        buttonPanel.setBackground(new Color(75, 99, 103));

        JButton capacidadeNormalButton = createButton("Capacidade Normal");
        JButton noCaracteristicasButton = createButton("No características");
        JButton capacidadeExameButton = createButton("Capacidade Exame");

        JButton subtractButton = createOperatorButton("-");
        JButton addButton = createOperatorButton("+");
        JButton multiplyButton = createOperatorButton("*");
        JButton divideButton = createOperatorButton("/");
        
        JButton deleteButton = createDeleteButton();

        JButton confirmarButton = createConfirmarButton();

        buttonPanel.add(capacidadeNormalButton);
        buttonPanel.add(noCaracteristicasButton);
        buttonPanel.add(capacidadeExameButton);
        buttonPanel.add(deleteButton);  
        buttonPanel.add(subtractButton);
        buttonPanel.add(addButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(confirmarButton);  // "Confirmar" button

        return buttonPanel;
    }

    private JButton createButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToResultTextArea(buttonText);
            }
        });
        customizeButton(button);
        return button;
    }

    private JButton createOperatorButton(String operator) {
        JButton button = new JButton(operator);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToResultTextArea(" " + operator + " ");
            }
        });
        customizeButton(button);
        return button;
    }

    private JButton createDeleteButton() {
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLastWord();
            }
        });
        customizeButton(deleteButton);
        return deleteButton;
    }

    private JButton createConfirmarButton() {
        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarButtonClicked();
            }
        });
        customizeButton(confirmarButton);
        return confirmarButton;
    }

    private void customizeButton(JButton button) {
        button.setBackground(new Color(184, 216, 216));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    private void appendToResultTextArea(String text) {
        resultTextArea.append(text);
    }

    private void deleteLastWord() {
        String currentText = resultTextArea.getText().trim();
        if (!currentText.isEmpty()) {
            // Use regex to find the last word and remove it
            Pattern pattern = Pattern.compile("\\S+\\s*$");
            Matcher matcher = pattern.matcher(currentText);

            if (matcher.find()) {
                int lastWordStart = matcher.start();
                resultTextArea.setText(currentText.substring(0, lastWordStart).trim());
            }
        }
    }

    private void confirmarButtonClicked() {
        String qualityControlText = getQualityControlText();
        GUI.metrica = getQualityControlText();
        System.out.println("Quality Control Text: " + qualityControlText);
        // Add your logic here based on the retrieved text
    }

    public String getQualityControlText() {
        return resultTextArea.getText();
    }

    @Override
    public void initComponents() {
        // No additional components to initialize for this example
    }

    @Override
    public void setupLayout(JFrame frame) {
        // No additional layout setup for this example
    }

    @Override
    public void setupListeners() {
        // No additional listeners to set up for this example
    }

    
}
