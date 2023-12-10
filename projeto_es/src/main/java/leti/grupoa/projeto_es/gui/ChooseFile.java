package leti.grupoa.projeto_es.gui;

import javax.swing.*;

import leti.grupoa.projeto_es.Schedule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChooseFile extends JFrame implements PageConfigurable{

    private JFrame frame;
    private boolean fileSelected = false;

    public ChooseFile() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CSV to Browser");

        JPanel panel = new JPanel();
        JButton openFileButton = new JButton("Escolher o ficheiro CSV a ser usado");
        JButton continueButton = new JButton("Continuar");

        openFileButton.setPreferredSize(new Dimension(250, 40));
        continueButton.setPreferredSize(new Dimension(150, 40));

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(75, 99, 103)); // Background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);

        JLabel titleLabel = new JLabel("Bem-vindo à app CSV to Browser");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 95, 85)); // Text color
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        JLabel descriptionLabel = new JLabel("Por favor, escolha um ficheiro CSV para continuar:");
        descriptionLabel.setForeground(new Color(255, 255, 255)); // Text color
        panel.add(descriptionLabel, gbc);

        gbc.gridy = 2;
        panel.add(openFileButton, gbc);

        gbc.gridy = 3;
        panel.add(continueButton, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileSelected) {
                    continueWorkflow();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, escolha um ficheiro CSV antes de continuar.",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }
	// Method to open a file chooser and initiate Schedule
	private void openFile() {
		try {
			// Initialize Schedule using the constructor that prompts the user to choose a
			// CSV file
			Schedule schedule = new Schedule();
			// Display or use the schedule as needed
			System.out.println("Schedule initiated from file: " + schedule.getFilePath());

			// Optionally, provide feedback to the user
			JOptionPane.showMessageDialog(null, "Schedule loaded successfully!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			fileSelected = true;

		} catch (Exception ex) {
			// Handle any exceptions that might occur during Schedule initialization
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading Schedule: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Method to handle the workflow after choosing a file
	private void continueWorkflow() {
	    // Implement the logic for the "Continue" button
	    System.out.println("Continue button clicked!");

	    // Dispose of the current frame
	    dispose();

	    // Create an instance of ColumnOrganizer (or your new page class)
	    List<String> columnNames = new ArrayList<>(List.of(
	            "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
	            "Dia da semana", "Hora de início da aula", "Hora de fim da aula", "Data da aula",
	            "Caracaterísticas da sala atribuída para a aula", "Sala de aula atribuída"));
	    ColumnOrganizer columnOrganizer = new ColumnOrganizer(columnNames);
	    columnOrganizer.setVisibility(frame, true);
	    
	}

	@Override
	public void initComponents() {
		//vazio
		
	}

	@Override
	public void setupLayout(JFrame frame) {
		//vazio
		
	}

	@Override
	public void setupListeners() {
		//vazio
		
	}

}
