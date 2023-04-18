package Games.ConnectFour;

import javax.swing.*;
import java.awt.*;

public class Dot extends JLabel {
    private int value;
    private int x,y;


    public Dot(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.setBounds(x,y,50,50);
        this.setVisible(true);
    }


    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillOval(x, y, 50, 50);

        if(value == 1) {
            g.setColor(Color.RED);
            g.fillOval(x, y, 50, 50);
        }
        else if(value == 2) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, 50, 50);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
