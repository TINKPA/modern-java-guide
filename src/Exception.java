public class Exception {
    static void fun() throws IllegalAccessException {
        System.out.println("Inside fun().");
        throw new IllegalAccessException("demo");
    }
    public static void main(String[] args) {
        test5();
    }

    // Q1
    static void test1() throws TestExpection {
        try {
            throw new TestExpection();
        } catch (TestExpection t) {
            System.out.println("Got the Test Exception");
            throw t;
        } finally {
            System.out.println("Inside finally block");
        }
    }

    // Q2
    static void test2() throws TestExpection {
        int x = 0;
        int y = 10;
        int z = y/x;
    }

    // Q5
    static void test5() {
        try {
            int[] a = {1,2,3,4};
            for (int i = 1; i <= 4; i++) {
                System.out.println("a{" + i + "]=" + a[i] + "\n");
            }
        } catch (java.lang.Exception e) {
            System.out.println("error = ");
        }
    }
}

class TestExpection extends java.lang.Exception {

}