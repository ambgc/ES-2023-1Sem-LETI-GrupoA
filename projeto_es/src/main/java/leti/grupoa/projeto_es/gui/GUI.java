package leti.grupoa.projeto_es.gui;

import leti.grupoa.projeto_es.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

	private boolean fileSelected = false;

	public GUI() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton openFileButton = new JButton("Escolher o ficheiro CSV a ser usado");
		JButton continueButton = new JButton("Continuar");
		

		openFileButton.setPreferredSize(new Dimension(250, 40));
		continueButton.setPreferredSize(new Dimension(150, 40));

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 20, 0); // Add some vertical space between components

		// Title
		JLabel titleLabel = new JLabel("Bem-vindo à app CSV to Browser");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(titleLabel, gbc);

		// Descriptive Text
		gbc.gridy = 1;
		JLabel descriptionLabel = new JLabel("Por favor, escolha um ficheiro CSV para continuar:");
		panel.add(descriptionLabel, gbc);

		// Open File Button
		gbc.gridy = 2;
		panel.add(openFileButton, gbc);

		// Continue Button
		gbc.gridy = 3;
		panel.add(continueButton, gbc);

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("CSV to Browser");

		// Set the size of the JFrame
		frame.setSize(500, 300);

		// Set the frame to appear in the center of the screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Add an ActionListener to the open file button
		openFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});

		// Add an ActionListener to the continue button
		continueButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (fileSelected) {
		            continueWorkflow();
		        } else {
		            // Provide feedback to the user that a file needs to be selected
		            JOptionPane.showMessageDialog(null, "Por favor, escolha um ficheiro CSV antes de continuar.",
		                    "Aviso", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
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

		JFrame secondFrame = new JFrame();
		JPanel secondPanel = new JPanel();

		JLabel page2Label = new JLabel("This is Page 2");
		secondPanel.add(page2Label);

		secondFrame.add(secondPanel);
		secondFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		secondFrame.setSize(400, 300);
		secondFrame.setLocationRelativeTo(null);
		secondFrame.setVisible(true);
	}
	
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GUI());
	}
}
