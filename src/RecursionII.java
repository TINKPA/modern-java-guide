import java.util.ArrayList;
import java.util.List;

public class RecursionII extends Toolbox{
    public static void main(String[] args) {
        RecursionII r = new RecursionII();
        r.countArrangement(3);
    }

    public int countArrangement(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();
        helper(n, cur, result);
        System.out.println(listOfListToString(result));
        return result.size();
    }

    private void helper(int n, List<Integer> cur, List<List<Integer>> result) {
        if (cur.size() == n) {
//            System.out.println(listOfListToString(result));

            result.add(new ArrayList<Integer>(cur));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (valid(cur,i)) {
                cur.add(i);
                System.out.println(cur);
                helper(n, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean valid(List<Integer> cur, int column) {
        int row = cur.size();
        for (int i = 0; i < row; i++) {
            if (cur.get(i) == column || ((row + 1) % column != 0 && column % (row + 1) != 0)) {
                return false;
            }
        }
        return true;
    }
}
