package leti.grupoa.projeto_es.gui;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Page2Test {

    @Test
    void testPage2Components() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            ColumnOrganizer page2 = new ColumnOrganizer();
            frame.add(page2);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Add assertions to check if the components are not null
            assertNotNull(page2.getTitleLabel());
            assertNotNull(page2.getConfirmButton());
            assertNotNull(page2.getGoBackButton());
        });
    }
}
