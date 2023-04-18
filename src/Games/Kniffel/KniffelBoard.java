package Games.Kniffel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KniffelBoard extends JComponent {
    private JTabbedPane tabbedPane;


    public KniffelBoard(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        initialise();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 700, 500);
        g.setColor(Color.BLACK);
        g.fillRect(14,12,50,25);
    }

    public void initialise() {
        JLabel labelBack = new JLabel("Back");
        labelBack.setFont(new Font("Arial", Font.PLAIN, 16));
        labelBack.setForeground(Color.WHITE);
        labelBack.setBounds(20, 0, 100, 50);

        labelBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(3);     // Go back to the menu
            }
        });

        this.add(labelBack);
    }
}
