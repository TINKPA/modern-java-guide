import java.util.LinkedList;
import java.util.*;

public class CrossTraining extends Toolbox{
    public static void main(String[] args) {
        CrossTraining ct = new CrossTraining();
        System.out.println(ct.wordBreak("probabi", new String[] {"pro", "babi", "prob", "abi", "p", "robabi"}));
    }

    /*
    Dictionary Word II
     */
    public int wordBreak(String s, String[] wordDict) {
        return helper(s, toSet(wordDict), 0,0);
    }


    private int helper(String s, Set<String> dictionary, int i, int j) {
        if (j == s.length()) {
            if (dictionary.contains(s.substring(i,j))) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dictionary.contains(s.substring(i, j))) {
            return helper(s, dictionary, j, j+1) + helper(s, dictionary, i, j+1);
        } else {
            return helper(s, dictionary, i, j+1);
        }
    }

    private void helper(String s, List<String> wordDict, List<String> sentences, Set<String> dictionary, StringBuilder sentence) {
        if (s.length() == 0) {
            sentences.add(sentences.toString().substring(1,sentence.length()));
            return;
        }
        if (dictionary.contains(s)) {
            return;
        }

        boolean add = true;

        for (String word : wordDict) {

        }
    }

    public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(2*k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                long d1 = distance(o1,a,b,c);
                long d2 = distance(o2,a,b,c);
                if (d1 == d2) {
                    return 0;
                }
                return d1 < d2 ? -1 : 1;
            }
        });
        Set<List<Integer>> visited = new HashSet<>();
        List<Integer> cur = Arrays.asList(0,0,0);
        visited.add(cur);
        minHeap.offer(cur);
        while (k > 0) {
            System.out.println(minHeap);
            cur = minHeap.poll();
            System.out.println(cur);
            List<Integer> n = Arrays.asList(cur.get(0) + 1,cur.get(1), cur.get(2), (int)distance(cur, a,b,c));
            if (n.get(0) < a.length && visited.add(n)) {
                minHeap.offer(n);
            }
            n = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2),(int)distance(cur, a,b,c));
            if (n.get(1) < b.length && visited.add(n)) {
                minHeap.offer(n);
            }
            n = Arrays.asList(cur.get(0),cur.get(1), cur.get(2) + 1,(int)distance(cur, a,b,c));
            if (n.get(2) < c.length && visited.add(n)) {
                minHeap.offer(n);
            }
            k--;
        }
        System.out.println(cur);
        cur.set(0, a[cur.get(0)]);
        cur.set(1, b[cur.get(1)]);
        cur.set(2, c[cur.get(2)]);
        return cur;
    }

    private long distance(List<Integer> point, int[] a, int[] b, int[] c) {
        long dis = 0;
        dis += a[point.get(0)] * a[point.get(0)];
        dis += b[point.get(1)] * b[point.get(1)];
        dis += c[point.get(2)] * c[point.get(2)];
        return dis;
    }

    /**
     *
     * @param
     * @return
     */
    static class Board {
        public final static int R = 2;
        public final static int C = 4;

        public Board() {
        }

        public Board(int[] values) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    board[i][j] = values[i * C + j];
                }
            }
        }

        public void swap(int i1, int j1, int i2, int j2) {
            int temp = board[i1][j1];
            board[i1][j1] = board[i2][j2];
            board[i2][j2] = temp;
        }

        public int[] findZero() {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 0) {
                        return new int[] {i, j};
                    }
                }
            }
            return null;
        }

        public boolean outOfBound(int i, int j) {
            return i < 0 || i >= R || j < 0 || j >= C;
        }

        @Override
        public int hashCode() {
            int code = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    code = code * 10 + board[i][j];
                }
            }
            return code;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Board)) {
                return false;
            }
            Board b = (Board) o;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] != b.board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public Board clone() {
            Board c = new Board();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    c.board[i][j] = board[i][j];
                }
            }
            return c;
        }

        private int[][] board = new int[R][C];
    }
    final static int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};
    public int numOfSteps(int[] values) {
        Queue<Board> queue = new ArrayDeque<>();
        Map<Board, Integer> boardStep = new HashMap<>();
        Board start = new Board(new int[] {0,1,2,3,4,5,6,7});
        queue.offer(start);
        boardStep.put(start, 0);

        while (!queue.isEmpty()) {
            Board board = queue.poll();
            int step = boardStep.get(board);
            int[] zeroPos = board.findZero();
            int zeroI = zeroPos[0];
            int zeroJ = zeroPos[1];
            for (int[] dir : DIRS) {
                int i = zeroI + dir[0];
                int j = zeroJ + dir[1];
                if (!board.outOfBound(i,j)) {
                    board.swap(zeroI, zeroJ, i, j);
                    if (!boardStep.containsKey(board)) {
                        Board newBoard = board.clone();
                        queue.offer(newBoard);
                        boardStep.put(newBoard, step + 1);
                    }
                    board.swap(zeroI, zeroJ, i, j);
                }
            }
        }
        return boardStep.getOrDefault(new Board(values), -1);
    }

    public int largestProduct(String[] dict) {
        int globalMax = 0;
        for (int i = 0; i < dict.length; i++) {
            int localMax = 0;
            for (int j = 0; j < dict.length; j++) {
                if (!doShareChar(dict[i], dict[j])) {
                    localMax = Math.max(localMax, dict[i].length() * dict[j].length());
                }
            }
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
    }

    private boolean doShareChar(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int largest(int[] array) {
        int result = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= array.length; i++) {
            int cur = (i == array.length ? 0 : array[i]);
            while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
                System.out.println("Stack: " + stackToString(stack));
                int height = array[stack.pollFirst()];
                int left = (stack.isEmpty() ? 0 : stack.peekFirst() + 1);
                result = Math.max(result, height * (i - left));
                System.out.println(left + " " + i + " height: " + height);
            }
            stack.offerFirst(i);
        }
        return result;
    }
}
