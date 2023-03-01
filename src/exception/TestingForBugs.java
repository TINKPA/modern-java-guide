package exception;
public class TestingForBugs {
    public static void main(String[] args) {
        try {
            System.out.println(Double.isNaN(HW1.HW1.average(new int[] {})));
        }
        catch (ArithmeticException e) {
            System.out.println("ArithmeticException when calculating the average of no numbers");
        }

        System.out.println();




        try {
            System.out.println(HW1.HW1.isMagicSquare(new int[][] {
                {1,2},
                {3}
            }));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException while checking whether {{1,2},{3}} is magic");
        }


        try {
            System.out.println(HW1.HW1.isMagicSquare(new int[][] {
                {0}
            })); 
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException while checking whether {{0}} is magic");
        }


        try {
            System.out.println(HW1.HW1.isMagicSquare(new int[][] {}));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException while checking whether {} is magic");
        }

        System.out.println();




        try {
            int[] arr = HW1.HW1.firstPrimes(-1);
        }
        catch (NegativeArraySizeException e) {
            System.out.println("NegativeArraySizeException when calling firstPrimes(-1)");
        }


        try {
            int[] arr = HW1.HW1.firstPrimes(0);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException when calling firstPrimes(0)");
        }


        try {
            int[] arr = HW1.HW1.firstPrimes(1);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException when calling firstPrimes(1)");
        }

        System.out.println();




//        MathVector v1 = new MathVector(1, 0);
//        MathVector v2 = new MathVector(0, 1);
//        MathVector v3 = new MathVector(1, 1);
//
//        try {
//            MathVector v = MathVector.add(v1, v2, v3);
//        }
//        catch (ArrayIndexOutOfBoundsException e) {
//            System.out.print("ArrayIndexOutOfBoundsException for MathVector.add ");
//            System.out.println("when adding 3 2-dimensional MathVectors");
//        }
    }
}