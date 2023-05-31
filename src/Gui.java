import Games.ConnectFour.ConnectFourBoard;
import Games.Kniffel.KniffelBoard;
import Games.TicTacToe.TicTacToeBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Gui extends JFrame{

    private int width = 700;
    private int height = 500;
    private boolean multiplayer = true;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private MenuBoard mBoard = new MenuBoard(tabbedPane);


    public Gui() {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);


        tabbedPane.setTabPlacement(SwingConstants.TOP);     // set tab placement to top
        tabbedPane.addTab("Connect Four", new ConnectFourBoard(tabbedPane, multiplayer));   // add tabs
        tabbedPane.addTab("Kniffel", new KniffelBoard(tabbedPane, multiplayer));      // add tabs
        tabbedPane.addTab("Tic Tac Toe", new TicTacToeBoard(tabbedPane, multiplayer));       // add tabs
        tabbedPane.addTab("Menu", mBoard);   // add tabs
        tabbedPane.setSelectedIndex(3);    // set menu as default tab

        panel.add(tabbedPane);
        tabbedPane.setLocation(-5,-25);     // set tab location
        tabbedPane.setSize(705, 525);       // set tab size

        this.add(panel);

        MouseAdapter listenerMultiplayer = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                multiplayer = !multiplayer;
                if (multiplayer) {
                    mBoard.labelMultiplayer.setText("Multiplayer: On");
                    mBoard.labelMultiplayer.setBackground(Color.decode("#016520"));
                } else {
                    mBoard.labelMultiplayer.setText("Multiplayer: Off");
                    mBoard.labelMultiplayer.setBackground(Color.RED);
                }
                mBoard.repaint();
            }
        };

        mBoard.labelMultiplayer.addMouseListener(listenerMultiplayer);
    }
}

