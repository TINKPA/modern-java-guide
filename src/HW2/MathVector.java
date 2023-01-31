package HW2;

import java.util.Arrays;

class MathVector {
    private final int      dim;
    private final double[] values;


    public MathVector(int N) {
        this.dim = N;
        this.values = new double[N];
    }
    public MathVector(double... arr) {
        this.dim    = arr.length;
        this.values = Arrays.copyOf(arr, arr.length);
    }
    public MathVector(MathVector v) {
        this.dim    = v.dim;
        this.values = Arrays.copyOf(v.values, v.dim);
    }


    public static MathVector vec2DfromPolar(double r, double theta) {
        return new MathVector(r*Math.cos(theta), r*Math.sin(theta));
    }

    public static MathVector vec3DfromPolar(double r, double theta, double phi) {
        return new MathVector(r * Math.sin(theta) * Math.cos(phi),
                                    r * Math.sin(theta) * Math.sin(phi),
                                    r * Math.cos(theta));
    }


    public int getDimension() { return this.dim; }


    public double getValue(int idx) { return this.values[idx - 1]; }
    public void setValue(int idx, double val) {this.values[idx - 1] = val;}


    public double dotWith(MathVector other) {
        double dotProduct = 0;
        for (int i = 0; i < this.dim; i++) {
            dotProduct += this.values[i] * other.values[i];
        }
        return dotProduct;
    }
    public double getMagnitude() {
        double magnitue = 0;
        for (int i = 0; i < this.dim; i++) {
            magnitue += Math.pow(this.values[i], 2);
        }
        return Math.sqrt(magnitue);
    }


    public void scaleBy(double scalar) {
        for (int i = 0; i < this.dim; i++) {
            this.values[i] *= scalar;
        }
    }
    public static MathVector multiply(double scalar, MathVector v) {
        MathVector result = new MathVector(v);
        result.scaleBy(scalar);
        return result;
    }


    public void shiftBy(MathVector other) {
        for (int i = 0; i < this.dim; i++) {
            this.values[i] += other.values[i];
        }
    }
    public static MathVector add(MathVector v1, MathVector v2) {
        double[] result = new double[v1.dim];
        for (int i = 0; i < v1.dim; i++) {
            result[i] = v1.values[i] + v2.values[i];
        }
        return new MathVector(result);
    }
    public static MathVector add(MathVector... vs) {
        MathVector result = new MathVector(vs[0].dim);
        for (MathVector v : vs) {
            result.shiftBy(v);
        }
        return new MathVector(result);
    }


    public boolean equals(MathVector other) {
        if (this.dim != other.dim) {return false;}
        for (int i = 0; i < this.dim; i++) {
            if (this.values[i] != other.values[i]) {
                return false;
            }
        }
        return true;
    }


    public String toString() { return java.util.Arrays.toString(values); }
    public void print() {
        System.out.println(toString());
    }
}