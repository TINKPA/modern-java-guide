import java.util.*;
import java.util.LinkedList;

/**
 * Empty Heap
 */


public class Heap extends BinayTree {
    public static void main(String[] args) {
        GraphNode one = new GraphNode(1);
        one.hashCode();
        GraphNode two = new GraphNode(2);
        GraphNode three = new GraphNode(3);
        GraphNode four = new GraphNode(4);
        GraphNode five = new GraphNode(5);
        one.neighbors.add(two);
        one.neighbors.add(three);
        two.neighbors.add(one);
        two.neighbors.add(four);
        two.neighbors.add(five);
        three.neighbors.add(one);
        three.neighbors.add(five);
        four.neighbors.add(two);
        five.neighbors.add(two);
        five.neighbors.add(three);
        List<GraphNode> list = Collections.EMPTY_LIST;
        Collections.addAll(list = new ArrayList<GraphNode>(), one, two, three, four, five);
        Heap hp = new Heap();
        System.out.println(hp.isBipartite(list));;
    }
    /**
     * Bipartite Lec 7
     * Determine if an undirected graph is bipartite. A bipartite graph
     * is one in which the nodes can be divided into two graups such that
     * no nodes have direct edgees to other other nodes in the same group
     */
    public boolean isBipartite(List<GraphNode> graph) {
        HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
        for (GraphNode node: graph) {
            if (!BFS(node, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        queue.offer(node);
        visited.put(node, 0);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            int curGroup = visited.get(cur);
            int neiGroup = curGroup == 0 ? 1 : 0;
            for (GraphNode nei : cur.neighbors) {
                if (!visited.containsKey(nei)) {
                    visited.put(nei, neiGroup);
                    queue.offer(nei);
                } else if (visited.get(nei) != neiGroup) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Midterm 2
     * Given two sorted arrays A nad B. We can pick one element a from A
     * and another element b from B, and their sum s is defined to be
     * s = a + b
     *
     * Find the k-th smallest s from all possible values of s
     *
     * A = {1,3,5} B = {4,5}
     * k = 1  1 + 4 = 5
     * k = 2 3 + 4 = 7
     * k = 3 1 + 8 = 4 + 5 = 9
     * @param array1, array2
     * @return int
     */
    public int kthSmallestInTwoSortedArray(int[] array1, int[] array2, int k) {
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                if (c1.equals(c2)) {
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1;
            }
        });
        minHeap.offer(new Cell(0,0, array1[0] + array2[0]));
        for (int i = 0; i < k - 1; i++){
            Cell cur = minHeap.poll();
            if (cur.indexInArray1 + 1 < array1.length) {
                minHeap.offer(new Cell(cur.indexInArray1 + 1,
                        cur.indexInArray2,
                        array1[cur.indexInArray1 + 1] + array2[cur.indexInArray2]));
            }
            if (cur.indexInArray2 + 1 < array2.length) {
                minHeap.offer(new Cell(cur.indexInArray1,
                        cur.indexInArray2 + 1,
                        array1[cur.indexInArray1] + array2[cur.indexInArray2 + 1]));
            }
        }
        return minHeap.peek().value;
    }

    // O(k * log k) O(k)

    static class Cell {
        int indexInArray1;
        int indexInArray2;
        int value;
        Cell(int indexInArray1, int indexInArray2, int value) {
            this.indexInArray1 = indexInArray1;
            this.indexInArray2 = indexInArray2;
            this.value = value;
        }
    }

    public boolean isCompleted(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean isBoba = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                } else {
                    if (isBoba) {
                        return false;
                    }
                    isBoba = true;
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                } else {
                    if (isBoba) {
                        return false;
                    }
                    isBoba = true;
                }
            }
        }
        return true;
    }

    public int[] kSmallest(int[] array, int k) {
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1 > o2 ? -1 : 1;
            }
        });

        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                maxHeap.offer(array[i]);
            } else if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; 0 <= i; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    // kth Smallest Number in Sorted Matrix

    /**
     * Assumptions:
     *  1. matrix is not null element
     *  2. k > 0 and k < N*M
     * @param
     */
//    public int kthSmallest(int[][] matrix, int k) {
//        int rows = matrix.length;
//        int cols = matrix[0].length;
//        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
//            @Override
//            public int compare(Cell c1, Cell c2) {
//                if (c1.equals(c2)) {
//                    return 0;
//                }
//                return c1.value < c2.value ? -1 : 1;
//            }
//        });
//        boolean[][] visited = new boolean[rows][cols];
//        minHeap.offer(new Cell(0,0,matrix[0][0]));
//        visited[0][0] = true;
//        for (int i = 0; i < k - 1; i++) {
//            Cell cur = minHeap.poll();
//            if (cur.row + 1 < rows && !visited[cur.row + 1][cur.col]) {
//                minHeap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row+1][cur.col]));
//                visited[cur.row + 1][cur.col] = true;
//            }
//            if (cur.col + 1 < cols && !visited[cur.row][cur.col + 1]) {
//                minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
//                visited[cur.row][cur.col + 1] = true;
//            }
//        }
//        return minHeap.peek().value;
//    }
//
//    static class Cell {
//        int row;
//        int col;
//        int value;
//
//        Cell(int row, int col, int value) {
//            this.row = row;
//            this.col = col;
//            this.value = value;
//        }
//    }

    static class GraphNode {
        public int key;
        public List<GraphNode> neighbors;
        public GraphNode(int key) {
            this.key = key;
            this.neighbors = new ArrayList<GraphNode>();
        }
    }
}
