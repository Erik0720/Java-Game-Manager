package Games.TicTacToe;

import javax.swing.*;
import java.awt.*;

public class Square extends JLabel {

    private int value;
    private int x;
    private int y;
    private boolean isSet = false;

    public Square(int value, int x, int y, boolean isSet) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.setVisible(true);
        this.isSet = isSet;
        this.setBounds(x,y,90,90);

    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(x,y,90,90);
        if(value == 1) {
            g.setColor(Color.RED);
            g.fillOval(x+20,y+20,50,50);
        } else if(value == 2) {
            g.setColor(Color.BLUE);
            g.fillOval(x+20,y+20,50,50);
        }
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean isSet) {
        this.isSet = isSet;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return (x / 100)-1;
    }

    public int getY() {
        return (y / 100)-2;
    }
}
