package AIPlayers.TicTacToe;

import Games.TicTacToe.Square;

public class AIPlayerTicTacToe {
    private int mySeed;
    private int oppSeed;
    private int[][] board;

    public AIPlayerTicTacToe(Square[][] board) {
        this.board = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                this.board[j][i] = board[i][j].getValue();
            }
        }
    }



}

