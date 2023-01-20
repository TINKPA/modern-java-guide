import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DFS extends Toolbox{
    public static void main(String[] args) {
        DFS dfs = new DFS();
        Toolbox tb = new Toolbox();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result = dfs.nQueens(4);
        System.out.println(result.get(0));
        System.out.println(tb.chessboardToString(result.get(0),4));
    }

    public List<List<Integer>> nQueens(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<>();
        nQueens_helper(result, cur, n);
        return result;
    }

    private void nQueens_helper(List<List<Integer>> result, List<Integer> cur, int n) {
        if (cur.size() == n) {
            result.add(new LinkedList<>(cur));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(cur, col)) {
                cur.add(col);
                nQueens_helper(result, cur, n);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isValid(List<Integer> cur, int validating_queens_col) {
        int validating_queens_row = cur.size();
        for (int validated_queens_row = 0; validated_queens_row < cur.size(); validated_queens_row++) {
            int validated_queens_col = cur.get(validated_queens_row);
            if (validated_queens_col == validating_queens_col || Math.abs(validated_queens_col - validating_queens_col) == validating_queens_row - validated_queens_row) {
                return false;
            }
        }
        return true;
    }

    public List<String> subSets(String set) {
        List<String> result = new ArrayList<String>();
        if (set == null) {
            return result;
        }
        char[] arraySet = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        helper(arraySet, sb, 0, result);
        return result;
    }

    private void helper(char[] set, StringBuilder sb, int index, List<String> result) {
        if (index == set.length) {
            result.add(sb.toString());
            return;
        }
        // Case 1: add
        sb.append(set[index]);
        helper(set,sb, index + 1, result);
        sb.deleteCharAt(sb.length() - 1);

        // Case 2: not add
        helper(set, sb, index + 1, result);
//        // case 1
//        helper(set, sb.append(set[index]), index + 1, result);
//        // case 2
//        helper(set, sb, index + 1, result);                //return null
//
//
//        sb.deleteCharAt(sb.length() - 1);
    }


    /**
     * All Valid Permutations of Parenthese Izs
     */
    public List<String> validParentheses(int k) {
        List<String> result = new ArrayList<String>();
        char[] cur = new char[k * 2];
        helper(cur, k,k,0, result);
        return result;
    }

    private void helper(char[] cur, int left, int right, int index, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(new String(cur));
            return;
        }
        if (left > 0) {
            cur[index] = '(';
            helper(cur, left-1, right,index+1, result);
        }
        if (right > left) {
            cur[index] = ')';
            helper(cur, left, right - 1, index + 1, result);
        }
    }

    /**
     * All Permutations I
     * @param set
     * @return List<String>
     *     In mathematics, a permutation of a set is, loosely speaking,
     *     an arrangement of its members into a sequence or linear order,
     *     or if the set is already ordered, a rearrangement of its elements.
     */
    // Foeward-
    // i = 0, 1 ,2
    // i = 1, 2
    // i = 3
    public List<String> permutations(String set) {
        List<String> result = new ArrayList<String>();
        if (set == null) {
            return result;
        }
        char[] array = set.toCharArray();
        permutations(array, 0, result);
        return result;
    }

    private void permutations(char[] array, int index, List<String> result) {
        if (index == array.length) {
            result.add(new String(array));
            return;
        }

        for (int i = index; i < array.length; i++) {
            swap(i, index, array);
            permutations(array, index + 1, result);
            swap(i, index, array);
        }
    }

    private void swap(int i, int j, char[] array) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
//    public List<String> permutations(String set) {
//        List<String> result = new ArrayList<String>();
//        if (set == null) {
//            return result;
//        }
//        char[] array = set.toCharArray();
//        helper(array, 0, result);
//        return result;
//    }
//
//    private void helper(char[] input, int index, List<String> result) {
//        if (index == input.length) { // base case
//            result.add(new String(input));
//            return;
//        }
//        // i = 1 ,2
//        for (int i = index; i < input.length; i++) {
//            swap(input, index, i);
//            helper(input, index + 1, result);
//            swap(input, index, i);
//        }
//    }
//
//    // O(n!)
//
//    private void swap(char[] array, int left, int right) {
//        char tmp = array[left];
//        array[left] = array[right];
//        array[right] = tmp;
//    }
    /**
     * Combinations of Coins
     */
    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();
        cb_helper(target, coins, 0, cur, result);
        return result;
    }

    private void cb_helper(int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> result) {
        if (index == coins.length - 1) {
            if ((target % coins[coins.length - 1]) == 0) {
                cur.add(target / coins[coins.length - 1]);
                result.add(new ArrayList<Integer>(cur));
                cur.remove(cur.size() - 1);
            }
            return;
        }
        int max = target / coins[index];
        for (int i = 0; i <= max; i++) {
            cur.add(i);
            cb_helper(target - i * coins[index], coins, index + 1, cur, result);
            cur.remove(cur.size() - 1);
        }
    }




    //                                  abc
    // position 0     bac (0,0)       bac (0,1)              cba (0,2)                n
    // position 1  abc(1,1)   acb(1,2)     bac (1,1)   bca (1,2)       cba      cab   n*(n-1)
    // position 2  abc(2,2)   acb(2,2)                                              n(n-1)(n-2)





















}
