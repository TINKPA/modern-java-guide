package HW1;

public class HW1 {
    public static void main(String args[]) {
        binary(0).equals("0");
        binary(-888).equals("-1101111000");
        binary(2147483647).equals("1111111111111111111111111111111");
        binary(-2147483648).equals("-10000000000000000000000000000000");
        int[][] square1 = {
                {8, 3, 4},
                {1, 5, 9},
                {6, 7, 2}
        };
        System.out.println(isMagicSquare(square1) == true);

    }

    /**
     * Computes the mean of the values in an array of ints.
     * By convention, the mean of no ints is Double.NaN.
     * @param arr a reference to an array of ints
     * @return the mean of the values stored
     *         in the array that 'arr' references
    */
    public static double average(int[] arr) {
        int len = arr.length;
        if (len == 0) return Double.NaN;
        double sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum/len;
    }


    /**
     * Returns a string containing
     * the binary representation of an int.
     * 
     * 88 can be written as
     * 
     *     1 * 2^6 + 0 * 2^5 + 1 * 2^4 + 1 * 2^3 + 0 * 2^2 + 0 * 2^1 + 0 * 2^0
     * 
     * so binary(88).equals("1011000").
     * 
     * Some other examples...
     *     binary(0).equals("0")
     *     binary(-888).equals("-1101111000")
     *     binary(2147483647).equals("1111111111111111111111111111111")
     *     binary(-2147483648).equals("-10000000000000000000000000000000")
     * 
     * @param n the int to express in binary
     * @return a string storing the binary representation of n
    */
    public static String binary(int n) {
        StringBuilder binary = new StringBuilder();
        if (n < 0) {
            binary.append('-');
            n = -n;
        }
        while (n > 0) {
            binary.append(n % 2);
            n /= 2;
        }
        return binary.reverse().toString();
    }


    /**
     * Says whether an array of an array of ints
     * is a magic square or not.
     * 
     * The conditions to be a magic square are...
     *   - it's a square;
     *   - if square.length == N, then it stores
     *     each of the numbers 1 through N*N exactly once;
     *   - the sums of
     *      - the rows
     *      - the columns
     *      - both main diagonals
     *     are the same.
     * 
     * For example, the following are magic squares...
     *      
     *     8 3 4
     *     1 5 9
     *     6 7 2
     * 
     *      2  7 14 11
     *      9 16  5  4
     *     15 10  3  6
     *      8  1 12 13
     * 
     * ...and the following are NOT magic squares.
     * 
     *     1
     *     2 3
     * 
     *     5 5 5
     *     5 5 5
     *     5 5 5
     * 
     * One can regard the empty array of arrays as magic.
     * 
     * @param square a reference to an array of an array of ints
     * @return true if 'square' references a magic square;
     *         false otherwise
    */
    public static boolean isMagicSquare(int[][] square) {
        // check if square is empty
        if (square.length == 0) return true;

        // check if square is a square
        int n = square.length;
        for (int i = 0; i < n; i++) {
            if (square[i].length != n) return false;
        }

        // check if square contains each number from 1 to n*n exactly once
        boolean[] presence = new boolean[n*n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = square[i][j];
                if (num < 1 || num > n*n) return false; // check if number is out of range
                if (presence[num]) return false; // check if number has already been seen
                presence[num] = true;
            }
        }

        // check if the sum of rows, columns, and diagonals are the same
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += square[0][i];
        }
        for (int i = 1; i < n; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += square[i][j];
                colSum += square[j][i];
            }
            if (rowSum != sum || colSum != sum) return false;
        }
        int diag1Sum = 0;
        int diag2Sum = 0;
        for (int i = 0; i < n; i++) {
            diag1Sum += square[i][i];
            diag2Sum += square[i][n - i - 1];
        }
        if (diag1Sum != sum || diag2Sum != sum) return false;

        return true;
    }



    /**
     * Returns a reference to an array storing the first prime numbers.
     * The function argument specifies how many prime numbers to store;
     * a negative argument results in returning null.
     * 
     * For example,
     *     java.util.Arrays.equals(firstPrimes(-1), null)
     *     java.util.Arrays.equals(firstPrimes( 0), new int[0])
     *     java.util.Arrays.equals(
     *         firstPrimes(8), new int[] {2, 3, 5, 7, 11, 13, 17, 19}
     *     )
     * 
     * @param N the number of prime numbers the array will store
     * @return a reference to an array storing the first N prime numbers;
     *         or null if N < 0
    */
    public static int[] firstPrimes(int N) {
        if (N <= 0) {
            return null;
        }
        boolean[] isComposite = new boolean[N*100];
        int[] primes = new int[N];
        primes[0] = 2;
        int count = 1;
        int num = 3;
        while (count < N) {
            if (!isComposite[num]) {
                primes[count] = num;
                count++;
                for (int i = num*2; i < N * 100; i=i+num) {
                    isComposite[i] = true;
                }
            }
            num++;
        }
        return primes;
     }
}
