package AIPlayers.TicTacToe;

import Games.TicTacToe.Square;

public class AIPlayerTicTacToe {
    private int mySeed;
    private int oppSeed;
    private int[][] board;

    public AIPlayerTicTacToe(Square[][] sBoard) {
        board = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[j][i] = sBoard[i][j].getValue();
            }
        }
    }

    public int[] move() {
        int[] move = new int[2];
        move[0] = 0;
        move[1] = 0;
        int bestScore = -1000;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                if(board[i][j] == 0) {
                    board[i][j] = 2;
                    int score = minimax(board, 0, false);
                    board[i][j] = 0;
                    if(score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;
    }

    public int minimax(int[][] board, int depth, boolean isMaximizing) {
        int result = checkWin(board);
        if(result != 0) {
            return result;
        }
        if(isMaximizing) {
            int bestScore = -1000;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++){
                    if(board[i][j] == 0) {
                        board[i][j] = 2;
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = 0;
                        if(score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 1000;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++){
                    if(board[i][j] == 0) {
                        board[i][j] = 1;
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = 0;
                        if(score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public int checkWin(int[][] board) {
        // Check rows for win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                if (board[i][0] == 1) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        // Check columns for win
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
                if (board[0][i] == 1) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        // Check diagonals for win
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            if (board[0][0] == 1) {
                return -1;
            } else {
                return 1;
            }
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
            if (board[0][2] == 1) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}

