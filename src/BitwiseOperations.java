//import java.util.LinkedList;
//import java.util.Queue;
//
//import static org.junit.Assert.assertEquals;
//
//public class BitwiseOperations extends Toolbox{
//    public static void main(String[] args) {
//        Queue<Integer> IntQue = new LinkedList<>();
//        BitwiseOperations bo = new BitwiseOperations();
//        int i = 50;
//        bo.reverseI(1003);
//}
//    @Test
//    public void test1() {
//        BitwiseOperations b = new BitwiseOperations();
//        assertEquals(false,b.bitTester(6,0));
//        assertEquals(true,b.bitTester(6,1));
//        assertEquals(true,b.bitTester(6,2));
//        assertEquals(7, b.bitSetter(6,0));
//        assertEquals(14, b.bitSetter(6,3));
//
//    }
//
//    /**
//     * Given an integer, test whether x's k-th is one.
//     * @param number
//     * @return
//     */
//
//    public boolean bitTester(int number, int k) {
//        if ((number & (1 << k)) == 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    /**
//     * Given an integer x, how to set x's k-th bit to 1?
//     * @param number
//     * @param k
//     * @return
//     */
//
//    public int bitSetter(int number, int k) {
//        return number | (1 << k);
//    }
//
//    /**
//     * Given an integer x, how to set x's k-th bit to 0?
//     * @param number
//     * @param k
//     * @return
//     */
//
//    public int bitResetter(int number, int k) {
//        return number & ~(1 << k);
//    }
//
//    /**
//     * Determine If A number Is Power of 2
//     * O(log2n) O(1)
//     */
//    public boolean isPowerOfTwoI(int number) {
//        if (number <= 0) {
//            return false;
//        }
//        while ((number & 1) == 0) {
//            System.out.println(intToBinaryString(number));
//            number >>>= 1;
//        }
//        return number == 1;
//    }
//
//    /**
//     * Hexadecimal Representation
//     * @param number
//     * @return
//     */
//    public boolean isPowerOfTwoII(int number) {
//        if (number <= 0) {
//            return false;
//        }
//        int count = 0;
//        while (number > 0) {
//            count += number & 1;
//            number = number >>> 1;
//        }
//        return count == 1;
//    }
//
//    public boolean isPowerOfTwoIII(int number) {
//        return number > 0 && (number & (number - 1)) == 0;
//    }
//
//    /**
//     * Number Of Different Bits
//     * Determine the number of bits that are different for two given integers
//     */
//    public int diffBits(int a, int b) {
//        int c = a^b;
//        int count = 0;
//        while (c != 0) {
//            count += c & 1;
//            c >>>= 1;
//        }
//        return count;
//    }
//
//    /**
//     * All Unique Characters II
//     * Determine if the characters of a given string are all unique
//     * O(n) O(1)
//     * @param word
//     * @return
//     */
//    public boolean allUnique(String word) {
//        int[] vec = new int[8];
//        char[] array = word.toCharArray();
//        for (char c : array) {
//            if ((vec[c/32] >>> (c&32) & 1) != 0) {
//                return false;
//            }
//            vec[c / 32] |= 1 << (c % 32);
//        }
//        return true;
//    }
//
//    /**
//     *
//     * @param num
//     * @return
//     */
//    public long reverseI(long num) {
//        for (int offset = 0; offset < 16; ++offset) {
//            long right = (num >> offset) & 1L;
//            long left = (num >> (31 - offset)) & 1L;
//            if (left != right) {
//                num ^= (1L << offset);
//                num ^= (1L << (31 - offset));
//            }
//        }
//        return num;
//    }
//
//    /**
//     *
//     * @param number
//     * @return
//     */
//    public String hex(int number) {
//        String prefix = "0x";
//        if (number == 0) {
//            return prefix + "0";
//        }
//        StringBuilder sb = new StringBuilder();
//        while (number > 0) {
//            int rem = number % 16;
//            if (rem < 10) {
//                sb.append((char)('0' + rem));
//            } else {
//                sb.append((char) (rem - 10 + 'A'));
//            }
//            number >>>= 4;
//        }
//        return prefix + sb.reverse().toString();
//    }
//
//
//
//}
//
//
