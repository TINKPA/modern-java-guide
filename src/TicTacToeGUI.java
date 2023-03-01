import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public final class TicTacToeGUI {
    public static void main(String[] args) {
        TicTacToeGUI t = new TicTacToeGUI();
    }

    private State state; // the state of a game separately from the GUI.
    private GUI gui; //todo: The constructor of GUI will generate the JFrame

    private void startNewGame() {
        if (gui != null) {
            gui.dispose();
        }
        state = new State();
        gui = new GUI();
    }




    TicTacToeGUI() {
        startNewGame();
    }



    private class State {
        private char XO = 'X';

        private void prepareForNextMove() {
            XO = (XO == 'X') ? 'O' : 'X';
        }


        private final char[][] board = new char[][] {{' ',' ',' '},
                                                     {' ',' ',' '},
                                                     {' ',' ',' '}};

        private void applyMove(int row, int col) {
            board[row][col] = XO;
        }


        private boolean someoneWon() {
            if (' ' != board[0][0] && board[0][0] == board[0][1] && board[0][1] == board[0][2]) { return true; }
            if (' ' != board[1][0] && board[1][0] == board[1][1] && board[1][1] == board[1][2]) { return true; }
            if (' ' != board[2][0] && board[2][0] == board[2][1] && board[2][1] == board[2][2]) { return true; }

            if (' ' != board[0][0] && board[0][0] == board[1][0] && board[1][0] == board[2][0]) { return true; }
            if (' ' != board[0][1] && board[0][1] == board[1][1] && board[1][1] == board[2][1]) { return true; }
            if (' ' != board[0][2] && board[0][2] == board[1][2] && board[1][2] == board[2][2]) { return true; }

            if (' ' != board[0][0] && board[0][0] == board[1][1] && board[1][1] == board[2][2]) { return true; }
            if (' ' != board[0][2] && board[0][2] == board[1][1] && board[1][1] == board[2][0]) { return true; }

            return false;
        }

        private boolean boardFull() {
            return board[0][0] != ' ' && board[0][1] != ' ' && board[0][2] != ' ' &&
                   board[1][0] != ' ' && board[1][1] != ' ' && board[1][2] != ' ' &&
                   board[2][0] != ' ' && board[2][1] != ' ' && board[2][2] != ' ';
        }
    }




    private class GUI extends JFrame {
        GUI() {
            super("TicTacToe");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a new JPanel to hold the buttons
            JPanel panel = new JPanel();

            // Create an array of 9 JButtons
            JButton[] buttons = new JButton[9];

            // Create the 9 JButtons and add them to the panel
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JButton();
                final int row = i / 3;
                final int col = i % 3;

                buttons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (state.board[row][col] == ' ') {
                            state.applyMove(row, col);
                            buttons[row*3+col].setText(String.valueOf(state.XO));
                            if (state.someoneWon()) {
                                JOptionPane.showMessageDialog(gui,
                                        String.format("That was a winning move! %c wins!", state.XO),
                                        "WINNING MOVE",
                                        JOptionPane.WARNING_MESSAGE);
                                for (int i = 0; i < 9; ++i) {
                                    buttons[i].setText(" ");
                                }
                                state = new State();
                                JOptionPane.showMessageDialog(gui,
                                        "It's a draw",
                                        "DRAW",
                                        JOptionPane.WARNING_MESSAGE);

                            }
                            if (state.boardFull()) {
                                for (int i = 0; i < 9; ++i) {
                                    buttons[i].setText(" ");
                                }
                                state = new State();
                                JOptionPane.showMessageDialog(gui,
                                        "It's a draw",
                                        "DRAW",
                                        JOptionPane.WARNING_MESSAGE);

                            }

                            state.prepareForNextMove();
                        } else {
                          JOptionPane.showMessageDialog(gui,
                                  "Someone has already made a move in that position.",
                                  "INVALID MOVE",
                                  JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
                panel.add(buttons[i]);
            }

            // Set the layout of the panel to a 3 by 3 grid
            panel.setLayout(new java.awt.GridLayout(3, 3));

            // Add the panel to the JFrame
            add(panel);

            setSize(600, 600);
            setVisible(true);
        }
        private class Tile {}
    }

    private void processValidMove(int row, int col) {}
}