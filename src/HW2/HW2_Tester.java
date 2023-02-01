package HW2;

class HW2_Tester {
    public static void main(String[] args) {
        MathVector v1 = new MathVector(1);
        MathVector v2 = new MathVector(1.0, 2.0);
        MathVector v3 = new MathVector(v2);

        System.out.println(v1.getDimension() + " " + v2.getDimension());     // 1 2
        System.out.println(v1.getValue(1));                                  // 0.0
        System.out.print('\n');


        v3.setValue(2, 3.0);

        System.out.println(v1.toString());                            // [0.0]
        System.out.println(v2);                                       // [1.0, 2.0]
        v3.print();                                                   // [1.0, 3.0]
        System.out.print("xxxxxxxxxxx \n");

        MathVector.vec2DfromPolar(2, Math.PI / 3).print();
        // close to [1.0, Math.sqrt(3.0)]

        MathVector.vec3DfromPolar(4, Math.PI / 6, Math.PI / 3).print();
        // close to [1.0, Math.sqrt(3.0), 2 * Math.sqrt(3)]

        System.out.print('\n');


        MathVector v = new MathVector(1, 2, 0, 0);
        MathVector w = new MathVector(0, 0, 3, 4);

        final MathVector e1 = new MathVector(1, 0, 0, 0);
        final MathVector e2 = new MathVector(0, 1, 0, 0);
        final MathVector e3 = new MathVector(0, 0, 1, 0);
        final MathVector e4 = new MathVector(0, 0, 0, 1);


        System.out.println(v.dotWith(e1));                  // 1.0
        System.out.println(v.dotWith(e2));                  // 2.0
        System.out.println(w.dotWith(w));                   // 25.0
        System.out.println(w.getMagnitude());               // 5.0
        System.out.print('\n');


        System.out.println(MathVector.multiply(2, w));      // [0.0, 0.0, 6.0, 8.0]
        w.print();    // w unchanged by line before         // [0.0, 0.0, 3.0, 4.0]

        w.scaleBy(2);
        w.print();    // w   changed by line before         // [0.0, 0.0, 6.0, 8.0]

        System.out.print('\n');


        System.out.println(MathVector.add(v, w));           // [1.0, 2.0, 6.0, 8.0]
        v.print();    // v unchanged by line before         // [1.0, 2.0, 0.0, 0.0]
        w.print();    // w unchanged by line before         // [0.0, 0.0, 6.0, 8.0]
        System.out.print('\n');

        v.shiftBy(w);
        v.print(); // v   changed by line before            // [1.0, 2.0, 6.0, 8.0]
        w.print(); // w unchanged by line before            // [0.0, 0.0, 6.0, 8.0]
        System.out.print('\n');


        MathVector linearCombination = MathVector.add(
            MathVector.multiply(8, e1), MathVector.multiply(7, e2),
            MathVector.multiply(6, e3), MathVector.multiply(5, e4)
        );
        linearCombination.print();                          // [8.0, 7.0, 6.0, 5.0]
        System.out.print('\n');


        System.out.println(!e1.equals(e2));                                 // true
        System.out.println(!e2.equals(e3));                                 // true
        System.out.println(!e3.equals(e4));                                 // true
        System.out.println(!(new MathVector(4)).equals(new MathVector(5))); // true

        System.out.println(v.equals(v));                                    // true
        System.out.println(w.equals(w));                                    // true
        System.out.println(w.equals(new MathVector(0, 0, 6, 8)));           // true
        System.out.println((new MathVector(8)).equals(new MathVector(8)));  // true

        System.out.print('\n');
    }
}
