package HW3;

import java.util.Scanner;
import java.util.Random;


public final class TicTacToe extends TwoPlayerBoardGame {
    private char XO = 'X';
    private int row;
    private int col;

    public TicTacToe(Player p1, Player p2) {
        super(null, 0, p1, p2);
    }

    public String toString() {
        return "";
    }


    protected void askForMove() {
        
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
        return true;
    }

    protected void applyMove() {
        
    }

    protected boolean someoneWon() {
        return false;
    }

    protected void celebrateMove() {
        
    }

    protected void prepareForNextMove() {
        
    }
}
