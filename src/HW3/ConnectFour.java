package HW3;

import java.util.Scanner;
import java.util.Random;


public final class ConnectFour extends TwoPlayerBoardGame {
    private char RY = 'R';
    private int col;

    private static final int ROWS = 6;
    private static final int COLS = 7;

    public ConnectFour(Player p1, Player p2) {
        super(null, 0, p1, p2);
    }

    public String toString() {
        return "";
    }

    protected void askForMove() {
        
    }

    protected void receiveMove() {
        
    }

    protected void generateMove() {
        
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
