package HW3;

import java.util.Random;
import java.util.Scanner;


public final class TicTacToe extends TwoPlayerBoardGame {
    private char XO = 'X';
    private int row;
    private int col;

    public TicTacToe(Player p1, Player p2) {
        super(new char[3][3], 9, p1, p2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        for (int r = 0; r < board.length ; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == '\u0000') {
                    sb.append(' ');
                } else {
                    sb.append(board[r][c]);
                }
                if (c < (board[0].length - 1)) sb.append('|');
            }
            if (r < (board.length - 1)) { sb.append("\n-----\n"); }

        }
        sb.append('\n');
        return sb.toString();
    }


    protected void askForMove() {
        System.out.format("%s, it's your move and you're %ss\n", current.getName(), XO);
        System.out.println("Please choose your move by typing row col where row is 0, 1, or 2 and col is 0, 1, or 2.");
    }

    protected void receiveMove() {
        Scanner reader = new Scanner(System.in);
        row = reader.nextInt();
        col = reader.nextInt();
    }

    protected void generateMove() {
        Random r = new Random();
        row = Math.abs(r.nextInt()) % 3;
        col = Math.abs(r.nextInt()) % 3;
    }

    protected boolean validMove() {
        return (row >= 0 && row <= 2) &&
                (col >= 0 && col <= 2) &&
                (board[row][col] == '\u0000');
    }

    protected void applyMove() {
        board[row][col] = XO;
    }

    protected boolean someoneWon() {
        // Check horizontal
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == XO) count++;
        }
        if (count == 3) { return true; }
        // Check vertical
        count = 0;
        for (int i = 0; i < board.length; i++) if (board[i][col] == XO) count++;
        if (count == 3) { return true; }
        // Check diagonal
        count = 0;
        for (int i = 0; i < board.length; i++)
            if (board[i][i] == XO) {
                count++;
            }
        if (count == 3) { return true; }
        count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][2 - i] == XO) { count++; }   // 02 11 20
        }
        return count == 3;
    }

    protected void celebrateMove() {
        System.out.println("That was a winning move!");
        System.out.printf("%s (%c) wins!\n", current.getName(), XO);
    }

    protected void prepareForNextMove() {
        super.prepareForNextMove();
        XO = XO == 'O' ? 'X' : 'O';
    }
}
