package Games.ConnectFour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectFourBoard extends JComponent {
    private JTabbedPane tabbedPane;
    private Dot[][] dots;
    private int padding = 5;
    private int turn = 1;


    public ConnectFourBoard(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        ui();
        initialise();
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

    public void initialise() {
        dots = new Dot[7][6];
        turn = 1;
        int x = 50;
        int y = 50;

        for(int i = 0; i < dots.length; i++) {
            for(int j = 0; j < dots[i].length; j++) {
                dots[i][j] = new Dot(0,(i+3)*50 , 300-j*50);
                this.add(dots[i][j]);
                dots[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        for(int i = 0; i < dots.length; i++) {
                            for(int j = 0; j < dots[i].length; j++) {
                                if(dots[i][j] == e.getSource() && dots[i][j].getValue() == 0) {

                                    lowestDot(i,j).setValue(turn);
                                    lowestDot(i,j).repaint();

                                    if(checkWin()) {
                                        JOptionPane.showMessageDialog(null, "Player " + turn + " wins!");
                                        reset();
                                        initialise();

                                    }else if(checkDraw()) {
                                        JOptionPane.showMessageDialog(null, "Draw!");
                                        reset();
                                        initialise();
                                    }

                                    if(turn == 1) turn = 2;
                                    else turn = 1;
                                }
                            }
                        }
                        repaint();
                    }
                });
            }
        }

    }

    public void ui() {
        JLabel labelBack = new JLabel("Back");
        labelBack.setFont(new Font("Arial", Font.PLAIN, 16));
        labelBack.setForeground(Color.WHITE);
        labelBack.setBounds(20, 0, 100, 50);
        this.add(labelBack);
        labelBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(3);     // Go back to the menu
            }
        });
    }

    public Dot lowestDot(int x, int y) {
        int j = y;
        while(j > 0) {
            if(dots[x][j-1] != null && dots[x][j-1].getValue() == 0) {
                j = j - 1;
                System.out.println("X: " + x + " Y: " + j);
            }   else {
                break;
            }
        }
        return dots[x][j];
    }

    public boolean checkWin() {
        for(int i = 0; i < dots.length; i++) {
            for(int j = 0; j < dots[i].length; j++) {
                if(dots[i][j].getValue() != 0) {
                    if(checkHorizontal(i,j) || checkVertical(i,j) || checkDiagonal(i,j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(int x, int y) {
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

    public boolean checkVertical(int x, int y) {
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

    public boolean checkDiagonal(int x, int y) {
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

    public boolean checkDraw() {
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
    }
}