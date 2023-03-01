package exception;

class RuntimeTryCatch {
    public static void main(String[] args) {
        /*
          We've previously learned a little about java.lang.Object.
          Now it's time to learn a little about java.lang.Throwable.
          The classes we'll learn about fit in to an inheritance diagram...

                                    Object
                                      |
                                  Throwable
                                 /         \
                            Error           Exception
                                                |
                                         RuntimeException

          https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html
          https://docs.oracle.com/javase/7/docs/api/java/lang/Error.html
          https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html
          https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html


          The easiest class to discuss is RuntimeException so we'll start there.
          Some classes which inherit from RuntimeException may already be familiar...

                                            RuntimeException
                                           /       |        \
                        ArithmeticException        |         NegativeArraySizeException
                                                   |
                                       IndexOutOfBoundsException
                                      /                         \
            StringIndexOutOfBoundsException              ArrayIndexOutOfBoundsException

          https://docs.oracle.com/javase/7/docs/api/java/lang/ArithmeticException.html
          https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html
          https://docs.oracle.com/javase/7/docs/api/java/lang/StringIndexOutOfBoundsException.html
          https://docs.oracle.com/javase/7/docs/api/java/lang/ArrayIndexOutOfBoundsException.html
          https://docs.oracle.com/javase/7/docs/api/java/lang/NegativeArraySizeException.html
        */


        // Uncommenting the next line gives an ArithmeticException...
//        System.out.println(1 / 0);


        // Uncommenting the next two lines gives a StringIndexOutOfBoundsException...
        // String s = "";
        // char c = s.charAt(0);

        // Uncommenting the next two lines gives an ArrayIndexOutOfBoundsException...
        // int[] arr0 = new int[] {};
        // System.out.println(arr0[0]);

        // Uncommenting the next line gives a NegativeArraySizeException...
        // int[] arr1 = new int[-1];


        /*
          The examples just given hopefully tell you why these classes exist.
          They have been written to classify different mistakes a coder may make
          and the inheritance tree organizes them in a useful way.

          For example, an ArrayIndexOutOfBoundsException
                    is an IndexOutOfBoundsException
              which is a  RuntimeException
              which is an Exception
              which is    Throwable.

          The reason I've started with RuntimeExceptions is that they are *unchecked* exceptions.
          The word "unchecked" refers to the fact that they are not checked by the compiler.
          For example, uncommenting the next 6 lines does *not* give a compile-time error
          (as long as the lines above are still commented).
        */

    /*
        System.out.println(1 / 0);

        String s = "";
        char c = s.charAt(0);

        int[] arr0 = new int[] {};
        System.out.println(arr0[0]);

        int[] arr1 = new int[-1];
    */


        /*
          It is possible to 'catch' exceptions.

          try-catch statements feel a little bit like if-else statements for exceptions.
          However, it is not true that one block or the other will execute.
          Instead, code in the 'try' block will execute until an uncaught exception is encountered.
           - If an uncaught exception is not encountered, the code in the 'catch' block(s) won't execute.
           - If an uncaught exception is encountered, then, if there is a 'catch' block for the exception,
             the code in that 'catch' block will execute.
        */

//        try {
//            System.out.println("Unproblematic print statement executed.");
//        }
//        catch (ArithmeticException e) {
//            System.out.println("Because there was not an ArithmeticException,");
//            System.out.println("this code will not execute.");
//        }
//
//        System.out.println();
//
//
//        try {
//            System.out.println("Before System.out.println(1 / 0).");
//
//            System.out.println(1 / 0);
//
//            System.out.println("Because there was an ArithmeticException,");
//            System.out.println("this code will not execute.");
//        }
//        catch (ArithmeticException e) {
//            System.out.println("There was an ArithmeticException and");
//            System.out.println("we entered this 'catch' block as soon as it was thrown.");
//            System.out.println("It is important that this 'catch' block");
//            System.out.println("expects an ArithmeticException.");
//        }
//
//        System.out.println();
//        System.out.println();


        /*
          To demonstrate the importance of a 'catch' block expecting
          the appropriate type of exception, we use a couple of nested try-catch statements.
        */

        try {
            try {
                System.out.println(1 / 0);
            }
            catch (ArithmeticException e) {
                System.out.println("The ArithmeticException is caught by this inner 'catch' block");
                System.out.println("because it is designed to catch exactly this type of exception.");
                throw new IndexOutOfBoundsException();
            }
            System.out.println("The ArithmeticException exception has been caught and so");
            System.out.println("the outer try block does not have an uncaught exception.");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("There is no exception to catch,");
            System.out.println("so this code will not execute.");
        }

        System.out.println();


        try {
            try {
                System.out.println(1 / 0);
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("This 'catch' block expects an IndexOutOfBoundsException.");
                System.out.println("It cannot catch an ArithmeticException.");
                System.out.println("Therefore the ArithmeticException is left uncaught");
                System.out.println("and propogates to the outer try-catch statement.");
            }
            System.out.println("This code does not execute because");
            System.out.println("an uncaught exception was encountered.");
        }
        catch (ArithmeticException e) {
            System.out.println("This outer 'catch' block catches the ArithmeticException.");
        }

        System.out.println();
        System.out.println();


        /*
          However, inheritance plays a role.
          Remember that an ArithmeticException
                     is a  RuntimeException
               which is an Exception
               which is a  Throwable.

          Exception is the most general type of object that a reasonable application will catch.
          Throwable is the most general type of object that the compiler will allow us to catch.
          Trying to catch (wordplay intended) an Object will give a compile-time error.
        */

        try {
            System.out.println(1 / 0);
        }
        catch (Throwable e) {
            System.out.println("The ArithmeticException is caught by a 'catch' block");
            System.out.println("expecting an instance of Throwable.");
        }

        System.out.println();
        System.out.println();

        /*
          In the example before the last one, it is relevant that
          an ArithmeticException is not an IndexOutOfBoundsException.
        */


        /*
          It is best to be as precise as possible about
          the type of exception you are expecting to catch.
          That way, you'll be more likely to handle the exception reasonably.

          To help with this, it is possible to write multiple 'catch' blocks.
          An exception will be caught by the earliest 'catch' block
          with the ability to catch it. Therefore, the order of the 'catch' blocks
          matters and some choices of ordering can even cause compile-time errors.
        */

        int example = 0;  // Try running the code with values of 1, 2, 3, and 4.

        try {
            if (example == 1) { int[] arr = new int[] {};  System.out.println(arr[0]); }
            if (example == 2) { String st = "";            char ch = st.charAt(0);     }
            if (example == 3) { int[] arr = new int[-1];                               }
            if (example == 4) {                            System.out.println(1 / 0);  }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("We encountered an ArrayIndexOutOfBoundsException.");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("We encountered an IndexOutOfBoundsException");
            System.out.println("which is not an ArrayIndexOutOfBoundsException.");
        }
        catch (NegativeArraySizeException e) {
            System.out.println("We encountered a NegativeArraySizeException.");
        }
        catch (RuntimeException e) {
            System.out.println("We encountered a RuntimeException");
            System.out.println("which is not an IndexOutOfBoundsException");
            System.out.println("or a NegativeArraySizeException.");
        }
        // catch (ArithmeticException e) { System.out.println("Would never execute."); }
        /*
          Uncommenting this line causes a compile-time error because
          an ArithmeticException is a RuntimeException and
          the catch block for a RuntimeException occurs before this one.
          Therefore, if this was allowed to compile,
          the code in the 'catch' block could never execute.
          Java prevents you from writing useless code!
        */

//        System.out.println();
//        System.out.println();


        /*
          It is possible to handle multiple types of exceptions with one 'catch' block
          even when one exception type does not extend the other.
        */

//        try {
            // Experiment with uncommenting one or the other...
            // System.out.println(1 / 0);
            // int[] arr = new int[-1];
//        }
//        catch (ArithmeticException | NegativeArraySizeException e) {
//            System.out.println("An ArithmeticException or a NegativeArraySizeException was caught.");
//        }
//
//        System.out.println();
//        System.out.println();


        /*
          'catch' blocks only catch exceptions thrown from the corresponding 'try' block,
          not a previous 'catch' block.
        */

//        try {
//            try {
//                System.out.println(1 / 0);
//            }
//            catch (ArithmeticException e) {
//                System.out.println("This 'catch' block catches the ArithmeticException,");
//                System.out.println("but a NegativeArraySizeException is about to be thrown.");
//
//                int[] arr = new int[-1];
//            }
//            catch (NegativeArraySizeException e) {
//                System.out.println("The code in this 'catch' block will not execute");
//                System.out.println("because it can only attempt to catch");
//                System.out.println("exceptions from the 'try' block above.");
//            }
//        }
//        catch (NegativeArraySizeException e) {
//            System.out.println("This outer 'catch' block catches the NegativeArraySizeException");
//            System.out.println("in order to avoid a runtime error.");
//        }
//
//        System.out.println();
//        System.out.println();


        /*
          In all the examples above, we ignored the 'e' in the catch statement.
          When an exception is caught, we obtain an instance of Throwable
          that is useable from within the 'catch' block.

          Most RuntimeExceptions inherit all of their methods from Throwable.
          https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#method_summary
        */

//        try {
//            System.out.println(1 / 0);
//        }
//        catch (ArithmeticException e) {
//            System.out.println(e.toString());
//            System.out.println(e.getMessage());
//        }


        /*
          Runtime exceptions are most frequently caused by bugs.
          This means that it normally makes the most sense to fix
          the problem instead of catching the runtime exception.
          Therefore, some might argue that what I just showed you is useless.
          However, what I've just shown you is useful for two reasons...

           1) try-catch blocks will apply to unchecked exceptions too.
              We'll learn about unchecked exceptions next.
           2) Unfortunately for me and you,
              try-catch blocks are the key
              to grading students' buggy code.
               - HW1.java contains a fake HW1 solution.
               - MathVector.java contains a fake HW2 solution.
               - Both have a 'main' method with some test cases that perform correctly.
               - TestForBugs.java reveals substantial problems with these "solutions".
        */
    }

    class A extends Throwable {
        A() {
            System.out.println("asdf");
        }
    }
}