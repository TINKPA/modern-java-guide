import java.util.ArrayList;
import java.util.List;

public class Midterm extends Toolbox{
    public static void main(String[] args) {
        Midterm m = new Midterm();
        System.out.println(m.nqueens(4));
//        System.out.println(m.minCutPalindrome("abba"));

//        int k = 2;
//        char[] cur = new char[2 * k];
//        m.vaildIfBlocks(k,k,0,cur);
//        TreeNode tree = new TreeNode(-15);
//        tree.left = new TreeNode(5);
//        tree.right = new TreeNode(6);
//        tree.left.left = new TreeNode(-8);
//        tree.left.right = new TreeNode(1);
//        tree.left.left.left = new TreeNode(2);
//        tree.left.left.right = new TreeNode(6);
//        tree.right.left = new TreeNode(3);
//        tree.right.right = new TreeNode(9);
//        tree.right.right.right = new TreeNode(0);
//        tree.right.right.right.left = new TreeNode(4);
//        tree.right.right.right.right = new TreeNode(-1);
//        tree.right.right.right.right.left = new TreeNode(10);
//        System.out.println("Max pathSum of the given binary tree is "
//                + m.max_leaf_to_leaf(tree));
    }
    static Object[] findMinInsertionsDP(char[] str, int n)
    {
        // Create a table of size n*n. table[i][j]
        // will store minimum number of insertions
        // needed to convert str[i..j] to a palindrome.
        int table[][] = new int[n][n];
        int l, h, gap;

        // Fill the table
        for (gap = 1; gap < n; ++gap)
            for (l = 0, h = gap; h < n; ++l, ++h)
                table[l][h] = (str[l] == str[h])?
                        table[l+1][h-1] :
                        (Integer.min(table[l][h-1],
                                table[l+1][h]) + 1);

        // Return minimum number of insertions
        // for str[0..n-1]
        return table;
    }

    // Q1: nqueens
    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();
        helper(n, cur, result);
        return result;
    }

    private void helper(int n, List<Integer> cur, List<List<Integer>> result) {
        if (cur.size() == n) {
            System.out.println(chessboardToString(cur, 4));
            result.add(new ArrayList<Integer>(cur));
            return;
        }
        for (int i = 0; i < n; i++) {

            if (valid(cur, i)) {
                cur.add(i);
                helper(n, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean valid(List<Integer> cur, int column) {
        int row = cur.size();
        for (int i = 0; i < row; i++) {
            if (cur.get(i) == column || Math.abs(cur.get(i) - column) == row - i) {
                return false;
            }
        }
        return true;
    }

    // Q2 Given a bianry tree in which each node contains an int number
    // Find the maximum possible sum from any left node to another leaf node
    public void vaildIfBlocks(int left, int right, int index, char[] cur) {
        if (left == 0 && right == 0) {
            printBlock(cur);
            return;
        }
        if (left > 0) {
            cur[index] = '{';
            vaildIfBlocks(left - 1, right, index + 1, cur);
        }
        if (right > left) {
            cur[index] = '}';
            vaildIfBlocks(left, right - 1, index + 1, cur);
        }
    }

    public void printBlock(char[] cur) {
        int space = 0;
        for (int i = 0; i < cur.length; i++) {
            if (cur[i] == '{') {
                printSpace(space);
                System.out.println("if {");
                space += 2;
            } else {
                space -= 2;
                printSpace(space);
                System.out.println("}");
            }
            System.out.println();
        }
    }

    private void printSpace(int space) {
        for (int i = 0; i < space; i++) {
            System.out.print(' ');
        }
    }

    /**
     * Q3
     * <p>
     * Given a binary tree in which each node contains an int number.
     * <p>
     * Find the maximum possible sum from any leaf node to another leaf node.
     * <p>
     * The maximum sum path may or may not go through root.
     * <p>
     * Expected time complexity is O(n).
     */
    public int max_leaf_to_leaf(TreeNode root) {
        int[] gobal_max = new int[1];
        gobal_max[0] = Integer.MIN_VALUE;
        max_leaf_to_leaf_helper(root, gobal_max);
        return gobal_max[0];
    }

    private int max_leaf_to_leaf_helper(TreeNode node, int[] gobal_max) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return node.key;
        }

        int leftSum = max_leaf_to_leaf_helper(node.left, gobal_max);
        int rightSum = max_leaf_to_leaf_helper(node.right, gobal_max);

        if (node.left != null && node.right != null) {
            int local_max = node.key + leftSum + rightSum;
            gobal_max[0] = Math.max(gobal_max[0], local_max);
            return node.key + Math.max(leftSum, rightSum);
        }

        if (node.left == null) {
            return rightSum + node.key;
        } else {
            return leftSum + node.key;
        }
    }

    // Q4
    public int minCutPalindrome(String input) {
        if (input == null || input.length() <= 1) return 0;

        char[] array = input.toCharArray();
        int[] M = new int[array.length + 1];
        M[0] = 0;
        M[1] = 0;
        //   0 1 2 3
        //   a b a
        // M 0 1   x
        for (int i = 2; i < M.length; i++) {  //i: linear scan
            int local_min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {       //j:[0,j)查表，[j，i)method 判断
                if (isPalindrome(array, j, i - 1)) {
                    if (j == 0) {
                        local_min = 0;
                        break;
                    } else {
                        local_min = Math.min(local_min, M[j] + 1);
                    }
                }
            }
            M[i] = local_min;
        }
        return M[array.length];
    }

    private boolean isPalindrome(char[] array, int a, int b) {
        while (a < b) {
            if (array[a] != array[b]) {
                return false;
            }
            a++;
            b--;
        }
        return true;
    }
}
