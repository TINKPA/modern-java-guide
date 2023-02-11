class Conversions {
    public static void main(String[] args) {
        /*
          WIDENING


          In JAVA, the following are regarded as "widening" conversions...

              byte --> short --> int --> long --> float --> double.


          This is totally reasonable for

              byte --> short --> int --> long

          since this corresponds to the set inclusions

              [-2^7, 2^7-1] --> [-2^15, 2^15-1] 
                            --> [-2^31, 2^31-1] --> [-2^63, 2^63-1]

          and so no information is lost during such conversions.


          It is also reasonable for

              float --> double

          because it corresponds to setting (11 - 8) + (52 - 23) == 32
          extra bits to have value 0; no information is lost (assuming strictfp).

          https://en.wikipedia.org/wiki/Single-precision_floating-point_format
          https://en.wikipedia.org/wiki/Double-precision_floating-point_format


          The following conversions can lose information.

              int  --> float
              long --> float
              long --> double

          However, they're still regarded as "widening" conversions.


          Widening conversions can occur implictly.
        */


        byte   b = 0;
        short  s = b;  // an implicit conversion byte  --> short  occurs
        int    i = s;  // an implicit conversion short --> int    occurs
        long   l = i;  // an implicit conversion int   --> long   occurs
        float  f = l;  // an implicit conversion long  --> float  occurs
        double d = f;  // an implicit conversion float --> double occurs

        /*
          Why is the conversion occurring when writing
              short s = b;
          implicit?

          Because you have to look at another line of code (the one before)
          to know a conversion occurs. Without looking at the line before,
          one could (incorrectly) guess that b is a short.
        */


        /*
          NARROWING


          The following are regarded as "narrowing" conversions...

              byte <-- short <-- int <-- long <-- float <-- double.


          Almost all narrowing conversions must be made explicitly, writing

              (T) expression

          to convert an expression to have type T.


          double --> float drops significant bits (in sensible cases).
          float  --> long  performs truncation    (in sensible cases).
          long   --> int   performs modular arithmetic.
          int    --> short performs modular arithmetic.
          short  --> byte  performs modular arithmetic.
        */


        d = 8192.50048828125;  // (1 / 2^11) + (1 / 2) + 2^13

        f = (float) d;  // an explicit conversion double --> float occurs
        l =  (long) f;  // an explicit conversion float  --> long  occurs

        System.out.println(d); // (1 / 2^11) + (1 / 2) + 2^13 == 8192.50048828125
        System.out.println(f); //              (1 / 2) + 2^13 == 8192.5
        System.out.println(l); //                        2^13 == 8192

        System.out.println("");


        d = 16777217;          //  1 + 2^24
        System.out.println((int) d == 1 + (int) (float) d);

        System.out.println("");


        l = 4295033104L;       // 2^32 + 2^16 + 2^8 + 2^4

        i =   (int) l;  // an explicit conversion long  --> int   occurs
        s = (short) i;  // an explicit conversion int   --> short occurs
        b =  (byte) s;  // an explicit conversion short --> byte  occurs

        System.out.println(i); //        2^16 + 2^8 + 2^4 == 65808
        System.out.println(s); //               2^8 + 2^4 == 272
        System.out.println(b); //                     2^4 == 16

        System.out.println("");


        /*
          We can now prove that the following conversions can lose information.

              int  --> float
              long --> double

          In what follows,
          p4f stands for "problem for float" and
          p4d stands for "problem for double".

          (float) p4f and (double) p4d both cause a subtraction of 1 to occur!
        */

        int p4f = 16777217;                                // 1 + 2^24
        System.out.println(p4f == 1 + (int) (float) p4f);

        long p4d = 9007199254740993L;                      // 1 + 2^53
        System.out.println(p4d == 1 + (long) (double) p4d);

        System.out.println("");


        /*
          Why did I say, "*almost all*
          narrowing conversions must be made explicitly"?
          0 is an int literal, but the following are fine because
          0 is small enough to be stored as a byte and a short.
        */
        b = 0; // similarly, b = -128;    and b = 127;
        s = 0; // similarly, s = -32_768; and s = 32_767;


        /*
          MORE CONVERSIONS

          We can convert a char to an int without losing information,
          and this is classified as a widening conversion.

          We can convert an int to a char. This might lose information.
          Sensible conversions are performed using the extended ASCII table.
        */

        int fromChar;
        fromChar = 'A'; System.out.println(fromChar); System.out.println((char) fromChar);
        fromChar = 'a'; System.out.println(fromChar); System.out.println((char) fromChar);

        System.out.println("");


        /*
          STRING CONVERSION

          String conversion occurs when using + and
          exactly one of its operands is a String:
          the non-String operand is converted to a String
          before the Strings are concatenated.
        */

        b = 8;      System.out.println(b + " is a byte  ");
        s = 88;     System.out.println(s + " is a short ");
        i = 888;    System.out.println(i + " is an int  ");
        l = 8888;   System.out.println(l + " is a long  ");
        f = 88888;  System.out.println(f + " is a float ");
        d = 888888; System.out.println(d + " is a double");

        System.out.println("");


        /*
          IMPLICIT CONVERSIONS DURING ARITHMETIC OPERATIONS - BINARY NUMERIC PROMOTION

          When performing arithmetic operations, the following rules are applied:
           - If either operand is of type double, the other is converted to double.
           - Otherwise, if either operand is of type float, the other is converted to float.
           - Otherwise, if either operand is of type long, the other is converted to long.
           - Otherwise, both operands are converted to type int.
        */

        b = 0; i = 0; s = 0;
//        TypeTester.printType(i + 0.0);
//        TypeTester.printType(i + 0.0f);
//        TypeTester.printType(i + 0L);
//        TypeTester.printType(s + s);
//        TypeTester.printType(b + b);

//         s = s + s; // compiler error
//         b = b + b; // compiler error

        System.out.println("");


        d = 9007199254740992.0;              // 2^53
        System.out.println((1 + d) - d == 0);
        // Some documentation I read seems to suggest that this line can produce
        // different output on different operating systems unless strictfp is specified.
    }
}
