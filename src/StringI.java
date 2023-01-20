import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringI extends Toolbox{
    public static void main(String[] args) {
        StringI str = new StringI();
        System.out.println(str.removeSpaces("a___"));
    }

    public String removeSpaces(String input) {
        if (input.length() == 1) {
            return input;
        }
        char[] array = input.toCharArray();
        int end = 0;
        for (int i = 0; i < input.length(); i++) {
            if (array[i] == '_' && (array[i-1] == '_' || i == 0)) {
                continue;
            }
            array[end++] = array[i];
        }
        return new String(array,0,end);
    }
//    public String compress(String input) {
//        if (input == null || input.isEmpty()) {
//            return input;
//        }
//        char[] array = input.toCharArray();
//        return
//    }
public List<String> permutations(String input) {
    if (input == null) {
        return null;
    }
    List<String> result = new ArrayList<String>();
    char[] array = input.toCharArray();
    helper(array, result, 0);
    return result;
}

    private void helper(char[] array, List<String> result, int index) {
        if (index == array.length) {
            result.add(new String(array));
            return;
        }
        Set<Character> used = new HashSet<Character>();
        for (int i = index; i < array.length; i++) {
            if (used.add(array[i])) {
                swap(array, index, i);
                helper(array, result, index+1);
                swap(array, index, i);
            }
        }
    }



    public String decompressII(String input) {
        char[] array = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            char ch = array[i++];
            int count = array[i] - '0';
            for (int c = 0; c < count; c++);
                sb.append(ch);
        }
        return sb.toString();
    }

//    private int copyDigits(char[] input, int index, int count) {
//        int len = 0;
//        for (int i = count; i > 0; 1 /= 10) {
//            index++;
//            len++;
//        }
//        for (int i = count; i > 0; i /= 10) {
//            int digit = i % 10;
//            input[--index] = (char)('0' + digit);
//        }
//        return len;
//    }





    /**
     * String Shuffing
     */
    public char[] reorder(char[] array) {
        if (array.length % 2 == 0) {
            reorder(array, 0, array.length - 1);
        } else {
            reorder(array, 0, array.length - 2);
        }
        return array;
    }

    private void reorder(char[] array, int left, int right) {
        int length = right - left + 1;
        if (length <= 2) {
            return;
        }
        int mid = left + length / 2;
        int lmid = left + length / 4;
        int rmid = left + length * 3 / 4;
        reverse(array, lmid, mid - 1);
        reverse(array, mid, rmid - 1);
        reverse(array, lmid, rmid - 1);
        reorder(array, left, left + (lmid - left) * 2 - 1);
        reorder(array, left + (lmid - left) * 2, right);
    }

    private void reverse(char[] array, int left, int right) {
        while (left < right) {
            char tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }
}


