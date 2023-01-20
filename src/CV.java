import java.util.ArrayList;
import java.util.List;

public class CV extends Toolbox{
    public static void main(String[] args) {
        CV cv = new CV();
//        System.out.println(cv.binary(new int[] {11,13,14,15,62,82,87,89,99}, 99));
//        System.out.println(cv.nqueens(8));
        Integer[] array = new Integer[] {11,1,null,null,-1,-9,10,-5,6,-7,null,null,-6,15};
        TreeNode root = cv.addKeyInBianyTreeLayerByLayer(array);
        cv.inOrder(root, root);

    }

    /*
    Q1: Binary search
     */
    public int binary(int[] array, int target) {
        if (array == null) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        System.out.println(mySubArray(array, left, right));
        while (left <= right) { // out left > right one element since
            int mid = left + (right - left)/2;
            if (array[mid] == target) {
                return mid;
            } else if (target < array[mid]) {
                right = mid;

            } else {
                left = mid + 1;
            }
            System.out.println(mySubArray(array, left, right));
        }
        return -1;
    }

    /*
    Q2
     */
    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();
        helper(n, cur, result);
        return result;
    }

    private void helper(int n, List<Integer> cur, List<List<Integer>> result) {
        if (cur.size() == n) {
            System.out.println(chessboardToString(cur, 8));
            result.add(new ArrayList<Integer>(cur));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (valid(cur, i)) {
//                System.out.println(chessboardToString(cur, 4));
                cur.add(i);
                helper(n, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean valid(List<Integer> cur, int column) {
        int row = cur.size();
        for (int i = 0; i < row; i++) {
//            System.out.println("col " + column + "  " + "row " + i);
//            System.out.println(chessboardToString(cur, 4, i + 1, column));
            if (cur.get(i) == column || Math.abs(cur.get(i) - column) == row - i) {
                return false;
            }
        }
        return true;
    }

    /*
    Q3: in Order
     */
    public void inOrder(TreeNode realRoot, TreeNode root) {
        if (root != null) {
            inOrder(realRoot, root.left);
            inOrder(realRoot, root.right);
        }
    }

    /*
    largest histogram
     */
    /*      1
          1 1
    1   1 1 1   1
    1 1 1 1 1 1 1
    1 1 1 1 1 1 1


    1 1 1 1 1 1 1
    1 1 1 1 1 1 1
     */


}
