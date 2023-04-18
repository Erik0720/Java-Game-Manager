import Games.ConnectFour.ConnectFourBoard;
import Games.Kniffel.KniffelBoard;
import Games.TicTacToe.TicTacToeBoard;

import javax.swing.*;


public class Gui extends JFrame{

    private int width = 700;
    private int height = 500;


    public Gui() {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(SwingConstants.TOP);     // set tab placement to top
        tabbedPane.addTab("Connect Four", new ConnectFourBoard(tabbedPane));   // add tabs
        tabbedPane.addTab("Kniffel", new KniffelBoard(tabbedPane));      // add tabs
        tabbedPane.addTab("Tic Tac Toe", new TicTacToeBoard(tabbedPane));       // add tabs
        tabbedPane.addTab("Menu", new MenuBoard(tabbedPane));   // add tabs
        tabbedPane.setSelectedIndex(3);    // set menu as default tab

        panel.add(tabbedPane);
        tabbedPane.setLocation(-5,-25);     // set tab location
        tabbedPane.setSize(705, 525);       // set tab size

        this.add(panel);
    }
}

