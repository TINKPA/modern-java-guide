import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public static void main(String[] args) {
        List<Location> blocks = new ArrayList<Location>();
        blocks.add(new Location(0,1));
        blocks.add(new Location(1,1));
        for (Interval Int : makeSubrows(findSubrows(blocks,3))) {
            Int.print();
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();
        NQUEEN2(3, makeSubrows(findSubrows(blocks,3)), cur, result, 0,0,0);
    }

    private static void NQUEEN2(int n,List<Interval> listOfInt ,List<Integer> cur, List<List<Integer>> result,int indexOfQueen, int subrowIndex, int indexInSubRow) {
        if (cur.size() == n) {
//            System.out.println(chessboardToString(cur, 4));
            result.add(new ArrayList<Integer>(cur));
            System.out.println("XXXX");
            return;
        }
        // don't place a queen in this sub row

        // place a queen in this sub row by a for loop indexed by indexInSubRow

        if (subrowIndex < listOfInt.size()) {
            for (int i = listOfInt.get(subrowIndex).getLeftBound(); i <= listOfInt.get(subrowIndex).getRightBound(); i++) {
                if (valid(cur, i, listOfInt.get(subrowIndex).getRow())) {
                    listOfInt.get(subrowIndex).print();
                    cur.add(i);
                    NQUEEN2(n, listOfInt, cur, result, indexOfQueen+1, subrowIndex+1, indexInSubRow);
                    cur.remove(cur.size() - 1);
                }
            }
//            NQUEEN2(n, listOfInt, cur, result, indexOfQueen, subrowIndex+1, indexInSubRow);

        }
    }

    private static boolean valid(List<Integer> cur, int column, int row) {
        for (int i = 0; i < row; i++) {
            if (cur.get(i) == column || Math.abs(cur.get(i) - column) == row - i) {
                return false;
            }
        }
        return true;
    }

    public static List<Location> findSubrows(List<Location> blocks, int n) {
        int listIndex = 0;
        List<Location> listOfLo = new ArrayList<Location>();
        boolean isLeft; boolean isRight;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    listOfLo.add(new Location(i,j));
                }
                if (listIndex < blocks.size() && blocks.get(listIndex).col - 1 == j && blocks.get(listIndex).row == i) {
                    listOfLo.add(new Location(i,j));
                    listIndex++;
                    if (j < n) {
                        listOfLo.add(new Location(i, j+2));
                    }
                }
                if (j == n - 1) {
                    listOfLo.add(new Location(i,j));
                }
            }
        }
        return listOfLo;
    }

    public static List<Interval> makeSubrows(List<Location> listOfLo) {
        List<Interval> listOfInt = new ArrayList<>();
        for (int i = 0; i < listOfLo.size(); i=i+2) {
            Interval Int = new Interval(listOfLo.get(i),listOfLo.get(i+1));
            listOfInt.add(Int);
        }
        return listOfInt;
    }
}

class Interval {
    Location left;
    Location right;

    public Interval(Location l, Location r) {
        this.left = l;
        this.right = r;
    }
    public Interval() {};

    public int getLeftBound() {
        return left.col;
    }
    public int getRightBound() {
        return right.col;
    }

    public int getRow() {
        return left.row;
    }

    public boolean isInterval() {
        return this.left != null && this.right != null;
    }

    public boolean isEmpty() {
        return this.right == null && this.left == null;
    }

    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(this.left.toString() + "~" + this.right.toString());
        return new String(this.left.toString() + " ~ " + this.right.toString());
    }

    public void print() {
        System.out.println(toString());
    }
}

class Location {
    int row;
    int col;

    public Location(int i, int i1) {
        this.row = i;
        this.col = i1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("("+this.row+", "+this.col+")");
        return sb.toString();
    }

    public void print() {
        System.out.println(toString());
    }
}
