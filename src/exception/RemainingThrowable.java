package exception;

import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;




/*
  Recall the inheritance diagram that we're learning about.

                            Object
                              |
                          Throwable
                         /         \
                    Error           Exception
                                        |
                                 RuntimeException




  An exception is an unwanted or unexpected event.
  It occurs at run-time and disrupts the
  normal flow of the program's instructions.

  Some reasons for exceptions
  in addition to what we've already seen...

􏰀   - a user provides bad input;
􏰀   - our code attempts to open
     a file that doesn't exist.

  We've seen that a RuntimeException is an unchecked exception.
  This means that it is "not checked by the compiler".

  An Exception which is not a RuntimeException is a "checked" exception.




  Here's exactly what the documentation says...


   - The class Exception and its subclasses are a form of Throwable that
     indicates conditions that a reasonable application might want to catch.

     The class Exception and
     any subclasses that are not also subclasses of RuntimeException
     are checked exceptions.

     Checked exceptions
     need to be declared in a method or constructor's throws clause
     if they can be thrown by the execution of the method or constructor
     and propagate outside the method or constructor boundary.


   - RuntimeException is the superclass of those exceptions that
     can be thrown during the normal operation of the Java Virtual Machine.

     RuntimeException and its subclasses are unchecked exceptions.

     Unchecked exceptions
     do not need to be declared in a method or constructor's throws clause
     if they can be thrown by the execution of the method or constructor
     and propagate outside the method or constructor boundary.




  This documentation tells us what we have to learn...
   - how to 'throw' an exception;
   - what the "throws clause" of a method is.




  Throwing an exception is done by writing the 'throw' keyword
  followed by a call to the constructor of a specific exception.

  For example, say we wanted to throw a IndexOutOfBoundsException.
  Look at the documentation for IndexOutOfBoundsException...

    https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html#constructor_summary

  There are two constructors. We could write...
    - throw new IndexOutOfBoundsException()
      to throw an IndexOutOfBoundsException with no detail message;
    - throw new IndexOutOfBoundsException("a detailed message")
      to throw an IndexOutOfBoundsException with
      a detail message consisting of the text "a detailed message".

  HW2 gives a nice example where we'd want to throw an IndexOutOfBoundsException.
  We've seen that StringIndexOutOfBoundsException and ArrayIndexOutOfBoundsException
  inherit from IndexOutOfBoundsException. It makes sense for us to write a class called
  MathVectorIndexOutOfBoundsException which extends IndexOutOfBoundsException.
*/


class MathVectorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    MathVectorIndexOutOfBoundsException(int idx, int dim) {
        // Constructs the underlying IndexOutOfBoundsException to have detail message:
        // Index $idx$ out of bounds for dimension $dim$.
        super("Index " + idx + " out of bounds for dimension " + dim);
    }
}

class MathVector {
    private final int      dim;
    private final double[] values;

    public MathVector(int N) {
        dim    = N;
        values = new double[dim];
    }

    public double getValue(int idx) {
        if (idx < 1 || idx > dim) {
            // 'throw' followed by a call to the constructor we just wrote...
            throw new MathVectorIndexOutOfBoundsException(idx, dim);
        }
        return values[idx - 1];
    }
    public void setValue(int idx, double val) {
        if (idx < 1 || idx > dim) {
            // 'throw' followed by a call to the constructor we just wrote...
            throw new MathVectorIndexOutOfBoundsException(idx, dim);
        }
        values[idx - 1] = val;
    }
}

class RemainingThrowable {
    public static void main(String[] args) {
        // Create a MathVector (0.0, 0.0, 0.0)...
        MathVector v = new MathVector(3);

        // Check we can 1-index its entries...
        System.out.println("(" + v.getValue(1) + ", " + v.getValue(2) + ", " + v.getValue(3) + ")");

        try {
            // Indexing with 0 causes a MathVectorIndexOutOfBoundsException to be thrown.
            double val = v.getValue(0);
        }
        catch (MathVectorIndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            // Indexing with 4 causes a MathVectorIndexOutOfBoundsException to be thrown.
            double val = v.getValue(4);
        }
        catch (RuntimeException e) {
            // MathVectorIndexOutOfBoundsExceptions are
            // IndexOutOfBoundsExceptions which are
            // RuntimeExceptions.
            System.out.println(e);
        }




        // The next two examples use code that is written further down the page.

        // Uncommenting the next line gives a java.lang.StackOverflowError.
        // TwoMoreThings.f();

        System.out.println();
        TwoMoreThings.g();
    }
}




/*
  FileNotFoundException
    https://docs.oracle.com/javase/7/docs/api/java/io/FileNotFoundException.html

  gives an example of an Exception that is not a Runtime Exception and so
  provides us with our first example of a checked exception.
*/

class Examples {
    public void fineToThrowAnUncheckedException() {
        throw new IndexOutOfBoundsException();
    }

    /*
      What does it mean for an Exception to be "checked"?
      Uncommenting the following method will give a compile-time error.
      We saw that this is not the case for an unchecked exception.
      So "checked" means "the compiler will check whether one's been thrown."
    */
    // public void notCatchingACheckedExceptionOrUsingAThrowsClause() {
    //     throw new FileNotFoundException();
    // }

    /*
      The compile-time error produced
      from uncommenting the method above says,
      "unreported exception FileNotFoundException;
      must be caught or declared to be thrown."

      Therefore, we should see an example of catching it
      and an example of declaring it as "thrown".
    */

    /*
      We saw try-catch statements for unchecked exceptions.
      There is no difference when they catch checked exceptions,
      but, in this case, they save us from a compile-time error.
    */
    public void catchingACheckedException() {
        try {
            throw new FileNotFoundException();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /*
      What follows provides our first example of a "throws clause".
      Now, the compiler allows an uncaught checked exception to be thrown.
      If a method uses 'usingAThrowsClause1',
      it'll be that method's responsibility to address the exception
      with either a try-catch statement or a throws clause.
    */
    public void usingAThrowsClause1() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    /*
      This example is silly, but it's worth pointing out.
      Even though we can see that a FileNotFoundException
      will never be thrown, it is still the case that
      when a method uses 'usingAThrowsClause2',
      it has to address a FileNotFoundException
      with either a try-catch statement or a throws clause.
      The three methods which follow this one demonstrate this.
    */
    public void usingAThrowsClause2() throws FileNotFoundException {
        System.out.println("It is fine to say \"throws CloneNotSupportedException\"");
        System.out.println("even though no CloneNotSupportedException is thrown");
        System.out.println("Of course, there's not much point in doing this.");
    }

    /*
      Uncommenting the following method will give a compile-time error:
      "unreported exception FileNotFoundException;
      must be caught or declared to be thrown."
    */
    // public void usingAMethodWhichThrowsACheckedException1() {
    //     usingAThrowsClause2();
    // }

    public void usingAMethodWhichThrowsACheckedException2() {
        try {
            usingAThrowsClause2();
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /*
      You can specify a superclass of what is thrown.
      Regarding the example that follows,
      methods which call 'usingMethodWhichThrowsACheckedException3'
      now need to address an IOException with
      either a try-catch statement or a throws clause.
    */
    public void usingAMethodWhichThrowsACheckedException3() throws IOException {
        usingAThrowsClause2();
    }


    /*
      What's an example where we'll have to acknowledge this type of thing?

      This constructor throws FileNotFoundException.
        https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html#FileInputStream(java.io.File)

      Therefore, when a method uses it, it must address a FileNotFoundException
      with either a try-catch statement or a throws clause.
    */
    // public void givesCompileTimeError() {
    //     FileInputStream fin = new FileInputStream("file_which_may_not_exist.txt");
    // }

    public void methodUsingAFile() {
        FileInputStream fin;

        try {
            fin = new FileInputStream("file_which_may_not_exist.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println("The file could not be found");
            return;
        }
        // [...do things with the file using 'fin'...]
    }
}




class TwoMoreThings {
    /*
      The Error class is used
      to represent fatal problems that
      the program probably can't and shouldn't
      recover from such as java.lang.OutOfMemoryError.
      Errors are treated like unchecked exceptions.
      You don't have to explicitly handle them.
      You shouldn't catch Errors. If you do,
      you shouldn't continue the program.
    */

    // Calling this function gives a java.lang.StackOverflowError.
    public static void f() {
        f();  // Recursion without a base case.
    }


    // And finally...
    public static void g() {
        try {
            throw new Exception();
        }
        catch (Exception e) {
            System.out.println("Attempting to exit g(),");
            return;
        }
        finally {
            System.out.println("but there's a 'finally' clause.");
        }
    }
}


/*
  SUMMARY

  - Unchecked Throwables:
    - RuntimeExceptions and Errors;
    - one is allowed to throw them or call a method which throws them
      even if they're not caught or declared in a throws clause.

  - Checked Throwables:
    - Exceptions which aren't RuntimeExceptions;
    - when they're thrown or a method which throws them is called,
      they must be caught or declared in a throws clause.
*/
