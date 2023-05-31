package Games.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class TicTacToeBoard extends JComponent {

    private int width = 700;
    private int height = 500;
    private int turn = -1;    // -1 = red, 1 = blue
    private boolean multiplayer;
    private JTabbedPane tabbedPane;
    private Square[][] nodes;
    private MouseAdapter squareListener;
    private MouseAdapter labelBackListener;
    private TicTacToeBoard tttboard;



    public TicTacToeBoard(JTabbedPane tabbedPane, boolean multiplayer) {
        this.tabbedPane = tabbedPane;
        this.multiplayer = multiplayer;
        tttboard = this;
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
                nodes[i][j].paintComponent(g);
            }
        }

    }

    public void initGame() {
        turn = -1;       // Reset the turn to 1
        nodes = new Square[3][3];     // Create a new 3x3 array of squares

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                nodes[i][j] = new Square(0, (i + 2) * 100, (j + 1) * 100, false);     // Create a new square
                nodes[i][j].addMouseListener(squareListener);     // Add a mouse listener to the square
                this.add(nodes[i][j]);    // Add the square to the board
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

    public int checkWin(Square[][] nodes) {
        int winner = -5;

        // Check rows for win
        for (int i = 0; i < 3; i++) {
            if (nodes[i][0].getValue() == nodes[i][1].getValue() && nodes[i][1].getValue() == nodes[i][2].getValue() && nodes[i][0].getValue() != 0)
                return nodes[i][0].getValue();

        }
        // Check columns for win
        for (int i = 0; i < 3; i++)
            if (nodes[0][i].getValue() == nodes[1][i].getValue() && nodes[1][i].getValue() == nodes[2][i].getValue() && nodes[0][i].getValue() != 0)
                return nodes[0][i].getValue();


        // Check diagonals for win
        if (nodes[0][0].getValue() == nodes[1][1].getValue() && nodes[1][1].getValue() == nodes[2][2].getValue() && nodes[0][0].getValue() != 0)
            return nodes[0][0].getValue();

        if (nodes[0][2].getValue() == nodes[1][1].getValue() && nodes[1][1].getValue() == nodes[2][0].getValue() && nodes[0][2].getValue() != 0)
            return nodes[0][2].getValue();


        return checkDraw(nodes);
    }

    public int checkDraw(Square[][] nodes) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (nodes[i][j].getValue() == 0) {
                    return -5;
                }
            }
        }
        return 0;
    }

    public void initListeners() {
        squareListener = new MouseAdapter() {     // Add a mouse listener to each square
            @Override
            public void mouseClicked(MouseEvent e) {    // When a square is clicked it will change the value of the square
                //System.out.println("Clicked");
                Square temp = (Square)e.getSource();      // Get the square that was clicked

                if (temp.isSet()) {
                    System.out.println("set");// If the square is already set, return
                    return;
                }

                temp.setValue(turn);    // Set the value of the square to the current turn
                temp.setSet(true);    // Set the square to be set
                tttboard.repaint();     // Repaint the square

                if(!multiplayer) {      // If the game is not multiplayer, make the ai move
                    aiMove();
                }
                else {
                    if (turn == -1)   // Change the turn
                        turn = 1;
                    else
                        turn = -1;
                }

                int score = checkWin(nodes);
                System.out.println("Clicked");

                if (score != -5) {
                    if(score == 0)
                        JOptionPane.showMessageDialog(null, "Draw!");
                    if(score == -1)
                        JOptionPane.showMessageDialog(null, "Red Wins!");
                    if(score == 1)
                        JOptionPane.showMessageDialog(null, "Blue Wins!");
                    resetGame();

                }

            }
        };

        labelBackListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(3);     // Go back to the menu
            }
        };
    }

    public void resetGame() {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                this.remove(nodes[i][j]);     // Remove all the squares from the board

        initGame();     // Reset the game
        tttboard.repaint();
    }

    public void aiMove() {
        Square[][] board = nodes.clone();   // Get the current board
        Square bestMove = null;
        double bestScore = -1000;
        double score;
        for(int i = 0; i < 3; i++) {    // Loop through all the available nodes
            for(int j = 0; j < 3; j++) {
                if(!board[i][j].isSet()) {
                    board[i][j].setSet(true);
                    board[i][j].setValue(1);    // Set the current node to the maximizing player
                    score = miniMax(board, 0, false);  // Get the score of the current move
                    System.out.println(score + " " + i + " " + j);
                    board[i][j].setValue(0);    // Reset the current node
                    board[i][j].setSet(false);

                    if (score > bestScore) {     // If the score is better than the best score, set the best score to the current score and set the best move to the current move
                        bestScore = score;
                        bestMove = board[i][j];
                    }
                }
            }
        }

        if(bestMove == null) {  // If there is no best move, return
            System.out.println("No best move");
            return;
        }

        System.out.println(bestMove.getX() + "  " + bestMove.getY());
        nodes[bestMove.getX()][bestMove.getY()].setValue(1);    // Set the best move to the maximizing player
        nodes[bestMove.getX()][bestMove.getY()].setSet(true);  // Set the best move to be set
        tttboard.repaint();    // Repaint the board
    }

    private double miniMax(Square[][] board, int depth, boolean isMaximizingPlayer) {
        Square bestMove = null;
        Square move;
        double bestScore;
        double score = checkWin(board);    // Check if the game is over

        if (score != -5) {  // If the game is over, return the score
            System.out.println("Score: " + score/(depth+1));
            return score /(depth+1);
        }

        if (isMaximizingPlayer) {
            bestScore = -1000;
            for(int i = 0; i < 3; i++) {    // Loop through all the available nodes
                for(int j = 0; j < 3; j++) {
                    if (!board[i][j].isSet()) {
                        board[i][j].setSet(true);
                        board[i][j].setValue(1);    // Set the current node to the maximizing player
                        score = miniMax(board, depth + 1, false);
                        board[i][j].setValue(0);    // Reset the current node
                        board[i][j].setSet(false);

                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
        }
        else {
            bestScore = 1000;
            for(int i = 0; i < 3; i++) {    // Loop through all the available nodes
                for(int j = 0; j < 3; j++) {
                    if (!board[i][j].isSet()) {
                        board[i][j].setSet(true);
                        board[i][j].setValue(-1);    // Set the current node to the minimizing player
                        score = miniMax(board, depth + 1, true);
                        board[i][j].setValue(0);    // Reset the current node
                        board[i][j].setSet(false);

                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
        }
        return bestScore/(depth+1);
    }

}
