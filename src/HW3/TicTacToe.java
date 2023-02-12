package HW3;

import javax.xml.namespace.QName;
import java.util.Scanner;
import java.util.Random;


public final class TicTacToe extends TwoPlayerBoardGame {
    private char XO = 'X';
    private int row;
    private int col;

    public TicTacToe(Player p1, Player p2) {
//        super(99, 9.9, p1, p2);
        super(null, 9, p1, p2);
    }

    public String toString() {
        return "";
    }


    protected void askForMove() {
        System.out.format("%s, it's your move and you're %ss", current.getName(), XO);
        System.out.println("Please choose your move by typing row col where row is 0, 1, or 2 and col is 0, 1, or 2.");
//        Student, it's your move and you're Xs.
//        Please choose your move by typing row col where row is 0, 1, or 2 and col is 0, 1, or 2.
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
