package HW3;

public abstract class TwoPlayerBoardGame {


    protected final char[][] board;
    private final int MAX_MOVES;

    protected Player current;
    private Player other;

    protected TwoPlayerBoardGame(char[][] board, int MAX_MOVES, Player p1, Player p2) {
        this.board = board;
        this.MAX_MOVES = MAX_MOVES;

        this.current = p1;
        this.other = p2;
    }


    public final void play() {
        for (int i = 0; i < MAX_MOVES; ++i) {
            System.out.println(this);

            do {
                if (current.isHuman()) {
                    askForMove();
                    receiveMove();
                }
                else {
                    generateMove();
                }
            }
            while (!validMove());

            applyMove();

            if (someoneWon()) {
                System.out.println(this);
                celebrateMove();
                return;
            }
            else {
                prepareForNextMove();
            }
        }

        System.out.println(this);
        System.out.println("It's a draw.");
    }


    protected abstract    void askForMove();
    protected abstract    void receiveMove();
    protected abstract    void generateMove();
    protected abstract boolean validMove();
    protected abstract    void applyMove();
    protected abstract boolean someoneWon();
    protected abstract    void celebrateMove();

    protected void prepareForNextMove() {
        Player tmp = current;
        current = other;
        other = tmp;
    }


}