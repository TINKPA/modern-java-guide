// Add any extra import statements you may need here


import java.util.ArrayList;
import java.util.List;

class Main {
    static final int[][] DIRS = {{-1,0}, {1,0}, {0,-1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    private static boolean exist(char[][] board, String word) {
        final int rows = board.length;
        final int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean helper(char[][] board, int x, int y, String word, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
                visited[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }

        visited[x][y] = true;
        for (int[] dir : DIRS) {
            int neiX = dir[0] + x;
            int neiY = dir[0] + y;
            if (helper(board, neiX, neiY, word, index + 1, visited)) {
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'b','c','e','e','d'},
                {'b','b','e','a','e'},
                {'e','b','c','c','a'},
                {'a','c','e','c','c'},
                {'a','b','c','d','c'}
        };
        String[] words = new String[] {"accacd","caea","dcd","acede","ceedd","aa","eeacda","bb"};
    }
}