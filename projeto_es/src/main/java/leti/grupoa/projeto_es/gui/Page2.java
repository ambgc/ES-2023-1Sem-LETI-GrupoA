package leti.grupoa.projeto_es.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Page2 extends JPanel {

    public Page2() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Page 2 Title");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action when the "Back" button is clicked
                showPage(1); // Switch back to the first page
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }

    // Custom method to show different pages
    public void showPage(int pageNumber) {
        // This can be a method in your main GUI class to switch between pages
        // For simplicity, you can just print the page number for now
        System.out.println("Switching to Page " + pageNumber);
    }
}
