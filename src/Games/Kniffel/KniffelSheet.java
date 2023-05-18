package Games.Kniffel;

import javax.swing.*;

public class KniffelSheet extends JPanel {

    // upper Part of the Game Sheet
    private JPanel ones;
    private JPanel twos;
    private JPanel threes;
    private JPanel fours;
    private JPanel fives;
    private JPanel sixes;
    private JPanel scoreBfBonus;    // Score before potential bonus is added
    private JPanel bonus;
    private JPanel scoreUpperPartTotal;   // Score after potential bonus is added

    // lower Part of the Game Sheet
    private JPanel threeOfAKind;
    private JPanel fourOfAKind;
    private JPanel fullHouse;
    private JPanel smallStraight;
    private JPanel largeStraight;
    private JPanel yacht;
    private JPanel chance;
    private JPanel scoreLowerPartTotal;
    private JPanel scoreTotal;

    // variables
    private int gameTurn;
    private int[] numbersRolled;

}
