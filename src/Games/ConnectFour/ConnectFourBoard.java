package Games.ConnectFour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectFourBoard extends JComponent {
    private JTabbedPane tabbedPane;
    private Dot[][] dots;
    private int padding = 5;
    private int turn = -1;
    private boolean multiplayer;
    private MouseAdapter labelBackListener;
    private MouseAdapter dotListener;
    private int counter = 0;


    public ConnectFourBoard(JTabbedPane tabbedPane, boolean multiplayer) {
        this.tabbedPane = tabbedPane;
        this.multiplayer = multiplayer;
        initListeners();
        initUi();
        initGame();
    }


    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 700, 500);
        g.setColor(Color.BLACK);
        g.fillRect(14,12,50,25);

        for(int i = 0; i < dots.length; i++) {
            for(int j = 0; j < dots[i].length; j++) {
                dots[i][j].paintComponent(g);
            }
        }
    }

    public void initGame() {
        dots = new Dot[7][6];
        turn = -1;
        int x = 50;
        int y = 50;

        for(int i = 0; i < dots.length; i++) {
            for(int j = 0; j < dots[i].length; j++) {
                dots[i][j] = new Dot(0,(i+3)*50 , 300-j*50);
                this.add(dots[i][j]);
                dots[i][j].addMouseListener(dotListener);
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

    public Dot lowestDot(Dot[][] dotsCopy, int x, int y) {
        int j = y;
        while(j > 0) {
            if(dotsCopy[x][j-1] == null || dotsCopy[x][j-1].getValue() != 0) {
                break;
            }
            j = j - 1;
        }
        return dotsCopy[x][j];
    }

    public boolean checkWin(Dot[][] dots,int turn) {
        for(int i = 0; i < dots.length; i++) {
            for(int j = 0; j < dots[i].length; j++) {
                if(dots[i][j].getValue() == 0) {
                    continue;
                }
                if(checkHorizontal(dots, i, j, turn) || checkVertical(dots, i, j, turn) || checkDiagonal(dots, i, j, turn)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(Dot[][] dots, int x, int y, int turn) {
        int count = 0;
        int i = x;
        while(i < dots.length) {
            if(dots[i][y].getValue() == turn) {
                count++;
                i++;
            } else {
                break;
            }
        }
        i = x - 1;
        while(i >= 0) {
            if(dots[i][y].getValue() == turn) {
                count++;
                i--;
            } else {
                break;
            }
        }
        if(count >= 4) {
            return true;
        }
        return false;
    }

    public boolean checkVertical(Dot[][] dots, int x, int y, int turn) {
        int count = 0;
        int j = y;
        while(j < dots[x].length) {
            if(dots[x][j].getValue() == turn) {
                count++;
                j++;
            } else {
                break;
            }
        }
        j = y - 1;
        while(j >= 0) {
            if(dots[x][j].getValue() == turn) {
                count++;
                j--;
            } else {
                break;
            }
        }
        if(count >= 4) {
            return true;
        }
        return false;
    }

    public boolean checkDiagonal(Dot[][] dots, int x, int y, int turn) {
        int count = 0;
        int i = x;
        int j = y;
        while(i < dots.length && j < dots[i].length) {
            if(dots[i][j].getValue() == turn) {
                count++;
                i++;
                j++;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y - 1;
        while(i >= 0 && j >= 0) {
            if(dots[i][j].getValue() == turn) {
                count++;
                i--;
                j--;
            } else {
                break;
            }
        }
        if(count >= 4) {
            return true;
        }
        count = 0;
        i = x;
        j = y;
        while(i < dots.length && j >= 0) {
            if(dots[i][j].getValue() == turn) {
                count++;
                i++;
                j--;
            } else {
                break;
            }
        }
        i = x - 1;
        j = y + 1;
        while(i >= 0 && j < dots[i].length) {
            if(dots[i][j].getValue() == turn) {
                count++;
                i--;
                j++;
            } else {
                break;
            }
        }
        if(count >= 4) {
            return true;
        }
        return false;
    }

    public boolean checkDraw(Dot[][] dots) {
        for(int i = 0; i < dots.length; i++) {
            for(int j = 0; j < dots[i].length; j++) {
                if(dots[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void reset() {
        for(int i = 0; i < dots.length; i++) {
            for(int j = 0; j < dots[i].length; j++) {
                this.remove(dots[i][j]);
            }
        }
        initGame();
        repaint();
    }

    public void initListeners() {
        labelBackListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(3);     // Go back to the menu
            }
        };

        dotListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                for(int i = 0; i < dots.length; i++) {
                    for(int j = 0; j < dots[i].length; j++) {
                        if(dots[i][j] != e.getSource() || dots[i][j].getValue() != 0) {
                            continue;
                        }
                        lowestDot(dots, i,j).setValue(turn);
                        lowestDot(dots, i,j).repaint();
                        repaint();

                        if(checkWin(dots,turn)) {
                            JOptionPane.showMessageDialog(null, "Player " + turn + " wins!");
                            reset();

                        }else if(checkDraw(dots)) {
                            JOptionPane.showMessageDialog(null, "Draw!");
                            reset();
                        }

                        if(!multiplayer) {
                            aiMove();
                            return;
                        }

                        if(turn == -1) turn = 1;
                        else turn = -1;
                        }
                    }
            }
        };
    }

    public void aiMove() {
        Dot[][] dotsCopy = dots.clone();
        Dot move;
        Dot bestMove = null;
        double score;
        double bestScore = -10000;
        for (int i = 0; i < 7; i++) {   // For each column
            move = lowestDot(dotsCopy, i, 6);   // Get the lowest dot in the column
            if(move.getValue() == 0) {  // If the dot is empty
                move.setValue(1);   // Set the dot to the AI's turn
                score = miniMax(dotsCopy, 0, false);    // Get the score of the move
                move.setValue(0);   // Reset the dot

                if(score > bestScore) { // If the score is better than the best score
                    bestScore = score;  // Set the best score to the score
                    bestMove = move;    // Set the best move to the move
                }
            }
        }
        if (bestMove != null)
            bestMove.setValue(1);   // Set the best move to the AI's turn
    }

    public double miniMax(Dot[][] dotsCopy, int depth, boolean maximizingPlayer) {
        Dot move;
        double score;
        double bestScore;

        System.out.println(depth + " " + counter++);
        if(checkWin(dotsCopy,1))
            return 1 / (depth+1);
        else if(checkDraw(dotsCopy))
            return 0;
        else if(checkWin(dotsCopy,-1))
            return -1 / (depth+1);

        if (maximizingPlayer) {
            bestScore = -10000;
            for (int i = 0; i < 7; i++) {   // For each column
                move = lowestDot(dotsCopy, i, 5);   // Get the lowest dot in the column
                if(move != null && move.getValue() == 0) {  // If the dot is empty
                    move.setValue(1);   // Set the dot to the AI's turn
                    score = miniMax(dotsCopy, depth + 1, false);    // Get the score of the move
                    move.setValue(0);   // Reset the dot

                    if(score > bestScore) { // If the score is better than the best score
                        bestScore = score;  // Set the best score to the score
                    }
                }
            }
        }
        else {
            bestScore = 10000;
            for (int i = 0; i < 7; i++) {   // For each column
                move = lowestDot(dotsCopy, i, 5);   // Get the lowest dot in the column
                if(move.getValue() == 0) {  // If the dot is empty
                    move.setValue(-1);   // Set the dot to the AI's turn
                    score = miniMax(dotsCopy, depth + 1, true);    // Get the score of the move
                    move.setValue(0);   // Reset the dot

                    if(score < bestScore) { // If the score is better than the best score
                        bestScore = score;  // Set the best score to the score
                    }
                }
            }
        }
        return bestScore/(depth+1);
    }

}