public class DP extends Toolbox{
    public static void main(String[] args) {
//        Toolbox tb = new Toolbox();
        DP dp = new DP();

        int[] d = new int[] {3,0,2,1,};

    }

    public long fibonacci(int K) {
        if (K <= 0) {
            return 0;
        }
        long[] array = new long[K + 1];
        array[1] = 1;
        for (int i = 0; i <= K; i++) {
            array[i] = array[i-2] + array[i-1];
        }
        return array[K];
    }


    /** Jump Game III
     *  Given an array of non-negative integers arr, you are initially positioned at start index of the array.
     *  When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
     *
     * Notice that you can not jump outside of the array at any time.
     * @param arr
     * @param start
     * @return
     */

    public boolean canReach(int[] arr, int start) {
        return canReach_helper(arr, start, start);
    }

    public boolean canReach_helper(int[] arr, int start, int pre_start) {
        if (arr == null && arr.length == 0) {
            return false;
        }

        if (start == 0) {
            return true;
        }

        if (start + arr[start] < arr.length && start + arr[start] != pre_start) {
            canReach_helper(arr, start + arr[start],start);
        } else if (0  <= (start - arr[start]) && start - arr[start] != pre_start) {
            canReach_helper(arr, start - arr[start],start);
        }

        return false;
    }

    public int minJump(int[] array) {
        int length = array.length;
        int[] minJump = new int[length];
        minJump[0] = 0;
        for (int i = 1; i < length; i++) {
            minFromVisted(array, minJump, i);
        }
        return minJump[length - 1];
    }

    private void minFromVisted(int[] array, int[] minJump, int cur) {
        minJump[cur] = -1;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < cur; i++) {
            if (array[i] + i >= cur && minJump[i] != -1) {
                if (minJump[i] < result) {
                    result = minJump[i];
                    minJump[cur] = minJump[i] + 1;
                }
            }
        }
    }

    public boolean canJump(int[] array) {
        if (array.length == 1) {
            return true;
        }
        boolean[] canJump = new boolean[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            if (i + array[i] >= array.length - 1) {
                canJump[i] = true;
            } else {
                for (int j = 1; j <= array[i]; j++) {
                    if (canJump[i+j] == true) {
                        canJump[i] = true;
                        break;
                    }
                }
            }
        }
        return canJump[0];
    }

    public int editDistance(String one, String two) {
        int[][] distance = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0) {
                    distance[i][j] = j;
                } else if (j == 0) {
                    distance[i][j] = i;
                } else if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1);
                    distance[i][j] = Math.min(distance[i - 1][j - 1] + 1, distance[i][j]);
                }
            }
        }
        return distance[one.length()][two.length()];
    }
}
