package leti.grupoa.projeto_es.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface PageConfigurable {

    default GridBagConstraints resetGBC(GridBagConstraints gbc) {
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        return gbc;
    }

    default void basicLayout(String title, JFrame frame, Color color) {
        frame.setTitle(title);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setLayout(new GridBagLayout());
        frame.setBackground(color);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }

    default JButton defineButtonLayout(Color backgroundColor, Color foregroundColor, String text, Dimension dimension) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setPreferredSize(dimension);
        return button;
    }

    default JTextField defineTextFieldLayout(String text, String fontName, int font, int size, Color backgroundColor, Color foregroundColor) {
        JTextField textField = new JTextField(text);
        textField.setFont(new Font(fontName, font, size));
        textField.setBackground(backgroundColor);
        textField.setForeground(foregroundColor);
        textField.setEditable(false);
        textField.setOpaque(false);
        return textField;
    }

    default JTextArea defineTextAreaLayout(String text, String fontName, int font, int size, Color backgroundColor, Color foregroundColor) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font(fontName, font, size));
        textArea.setBackground(backgroundColor);
        textArea.setForeground(foregroundColor);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(false);
        return textArea;
    }

    default void setVisibility(JFrame jframe, boolean isVisible) {
        jframe.setVisible(isVisible);
    }

    void initComponents();

    void setupLayout(JFrame frame); // Modified to accept JFrame as a parameter

    void setupListeners();

    // New constructor method
    default void createPage(JFrame frame, Color color, String title) {
    	//frame.setSize(800, 500);
    	
        basicLayout(title, frame, color);
        initComponents();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        

        gbc.gridy = 3;
      

        setupLayout(frame);
        setupListeners();
        setVisibility(frame, true);
    }
    
    
}
