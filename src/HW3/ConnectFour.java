package HW3;

import java.util.Scanner;
import java.util.Random;


public final class ConnectFour extends TwoPlayerBoardGame {
    private char RY = 'R';
    private int col;
    private int row;
    private static final int ROWS = 6;
    private static final int COLS = 7;

    public ConnectFour(Player p1, Player p2) {
        super(new char[ROWS][COLS], ROWS*COLS, p1, p2);
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
            if (r < (board.length - 1)) { sb.append("\n-------------\n"); }

        }
        sb.append("\n0 1 2 3 4 5 6\n");
        return sb.toString();
    }

    protected void askForMove() {
        System.out.format("%s, it's your move and you're %ss\n", current.getName(), RY);
        System.out.println("Please choose your move by typing a column number: 0, 1, 2, 3, 4, 5, or 6.");
    }

    protected void receiveMove() {
        Scanner reader = new Scanner(System.in);
        col = reader.nextInt();
    }

    protected void generateMove() {
        Random r = new Random();
        col = Math.abs(r.nextInt()) % 6;
    }

    protected boolean validMove() {
        if (col >= COLS) { return false; }
        row = ROWS - 1;
        while (row >= 0) {
            if (board[row][col] == '\u0000') {
                return true;
            }
            row--;
        }
        return false;
    }

    protected void applyMove() {
        board[row][col] = RY;
    }

    protected boolean someoneWon() {
        // Check horizontal
        int count = 0;
        for (int i = col; i < COLS && board[row][i] == RY; i++) {
            count++;
        }
        for (int i = col - 1; i >= 0 && board[row][i] == RY; i--) {
            count++;
        }
        if (count >= 4) { return true; }

        // Check vertical
        count = 0;
        for (int i = row; i < ROWS && board[i][col] == RY; i++) {
            count++;
        }
        if (count >= 4) { return true; }

        // Check diagonal (top-left to bottom-right)
        count = 0;
        int i = row; int j = col;
        while (i < ROWS && j < COLS && board[i][j] == RY) {
            count++;
            i++;
            j++;
        }
        i = row - 1; j = col - 1;
        while (i >= 0 && j >= 0 && board[i][j] == RY) {
            count++;
            i--;
            j--;
        }
        if (count >= 4) { return true; }

        // Check diagonal (bottom-left to top-right)
        count = 0;
        i = row;
        j = col;
        while (i >= 0 && j < COLS && board[i][j] == RY) {
            count++;
            i--;
            j++;
        }
        i = row + 1;
        j = col - 1;
        while (i < ROWS && j >= 0 && board[i][j] == RY) {
//            System.out.printf("i: %d  j: %d", i, j);
            count++;
            i++;
            j--;
        }
        return count >= 4;
    }

    protected void celebrateMove() {
        System.out.printf("That was a winning move!%n%s (%c) wins!%n", current.getName(), RY);
    }

    protected void prepareForNextMove() {
        super.prepareForNextMove();
        RY = RY == 'R' ? 'Y' : 'R';
    }
}
