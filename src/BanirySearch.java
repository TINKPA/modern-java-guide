public class BanirySearch extends Toolbox{
    public int appleBS(int[] array) {
        if (array == null) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) { // out left > right one element since
            int mid = left + (right - left)/2;
            if (array[mid] == mid) {
                return mid;
            } else if (mid < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void foo() {
        System.out.println("form foo()");
    }

    public static void main(String[] args) {
        BanirySearch bs = new BanirySearch();
        System.out.println(bs.appleBS(new int[] {-5, 1,2,3, 9,4,5,56, 99}));
        foo();
    }

    public int smallestElementLargerThanTarget(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (array[left] > target) {
            return left;
        } else if (array[right] > target) {
            return right;
        }

        return -1;
    }
}
