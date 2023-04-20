package Games.Kniffel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KniffelBoard extends JComponent {
    private JTabbedPane tabbedPane;
    private MouseAdapter labelBackListener;


    public KniffelBoard(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        initListeners();
        initUi();
        initGame();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 700, 500);
        g.setColor(Color.BLACK);
        g.fillRect(14,12,50,25);
    }

    public void initGame() {

    }

    public void initUi() {
        JLabel labelBack = new JLabel("Back");
        labelBack.setFont(new Font("Arial", Font.PLAIN, 16));
        labelBack.setForeground(Color.WHITE);
        labelBack.setBounds(20, 0, 100, 50);

        labelBack.addMouseListener(labelBackListener);

        this.add(labelBack);
    }

    public void initListeners() {
        labelBackListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(3);     // Go back to the menu
            }
        };
    }
}
