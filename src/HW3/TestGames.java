package HW3;

public class TestGames {
    public static void main(String[] args) {
        TwoPlayerBoardGame game;

//        game = new TicTacToe(Player.createHuman("Student"), Player.createComputer());
//        game.play();
//
//        game = new TicTacToe(Player.createHuman("Roy"), Player.createHuman("Moss"));
//        game.play();
//
//        game = new ConnectFour(Player.createHuman("Student"), Player.createComputer());
//        game.play();

        game = new ConnectFour(Player.createHuman("Jen"), Player.createHuman("Moss"));
        game.play();
    }
}
