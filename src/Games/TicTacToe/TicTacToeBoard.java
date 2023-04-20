package Games.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TicTacToeBoard extends JComponent {

    private int width = 700;
    private int height = 500;
    private JTabbedPane tabbedPane;
    private Square[][] squares;
    private int turn = 1;
    private MouseAdapter squareListener;
    private MouseAdapter labelBackListener;


    public TicTacToeBoard(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        initListeners();
        initUi();
        initGame();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.fillRect(14,12,50,25);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j].paintComponent(g);
            }
        }

    }

    public void initGame() {
        turn = 1;       // Reset the turn to 1
        squares = new Square[3][3];     // Create a new 3x3 array of squares

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j] = new Square(0, (j + 2) * 100, (i + 1) * 100, false);     // Create a new square
                squares[i][j].addMouseListener(squareListener);     // Add a mouse listener to the square
                this.add(squares[i][j]);    // Add the square to the board
            }
        }
    }

    public void initUi() {
        JLabel labelBack = new JLabel("Back");
        labelBack.setFont(new Font("Arial", Font.PLAIN, 16));
        labelBack.setForeground(Color.WHITE);
        labelBack.setBounds(20, 0, 100, 50);

        labelBack.addMouseListener(labelBackListener);
        this.add(labelBack);
    }

    public void checkWin() {
        int winner = 0;

        // Check rows for win
        for (int i = 0; i < 3; i++) {
            if (squares[i][0].getValue() == squares[i][1].getValue() && squares[i][1].getValue() == squares[i][2].getValue() && squares[i][0].getValue() != 0) {
                if (squares[i][0].getValue() == 1) {
                    winner = 1;
                } else {
                    winner = 2;
                }
            }
        }
        // Check columns for win
        for (int i = 0; i < 3; i++) {
            if (squares[0][i].getValue() == squares[1][i].getValue() && squares[1][i].getValue() == squares[2][i].getValue() && squares[0][i].getValue() != 0) {
                if (squares[0][i].getValue() == 1) {
                    winner = 1;
                } else {
                    winner = 2;
                }
            }
        }
        // Check diagonals for win
        if (squares[0][0].getValue() == squares[1][1].getValue() && squares[1][1].getValue() == squares[2][2].getValue() && squares[0][0].getValue() != 0) {
            if (squares[0][0].getValue() == 1) {
                winner = 1;
            } else {
                winner = 2;
            }
        }
        if (squares[0][2].getValue() == squares[1][1].getValue() && squares[1][1].getValue() == squares[2][0].getValue() && squares[0][2].getValue() != 0) {
            if (squares[0][2].getValue() == 1) {
                winner = 1;
            } else {
                winner = 2;
            }
        }

        if (winner != 0) {
            JOptionPane.showMessageDialog(null, "Player " + winner + " wins!");
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    this.remove(squares[i][j]);
                }
            }
            initGame();
            this.repaint();
        }   else if (checkDraw()) {
            JOptionPane.showMessageDialog(null, "Draw!");

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    this.remove(squares[i][j]);
                }
            }

            initGame();
            this.repaint();
        }
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (squares[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void initListeners() {
        squareListener = new MouseAdapter() {     // Add a mouse listener to each square
            @Override
            public void mouseClicked(MouseEvent e) {    // When a square is clicked it will change the value of the square
                // and repaint the square with the new value and color
                if (((Square)e.getSource()).isSet()) {
                    return;
                }

                ((Square)e.getSource()).setValue(turn);
                ((Square)e.getSource()).setSet(true);
                ((Square)e.getSource()).repaint();
                if (turn == 1) {
                    turn = 2;
                } else {
                    turn = 1;
                }

                checkWin();
            }
        };

        labelBackListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(3);     // Go back to the menu
            }
        };
    }

}
