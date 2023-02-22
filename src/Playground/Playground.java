package Playground;

public class Playground {
    Playground() {}
    public int i = 99;
    Playground p() { return this; }
    public class Playground_inner {
        Playground_inner p() { return Playground_inner.this; }
        public int j = 0;
    }
    public int foo() {
        Playground_inner pi = new Playground_inner();
        return pi.j; }

    static class static_playground_inner {
        public int j = 0;
        static_playground_inner p() { return this; }
//        public int foo() {
//            return this.i;
//        }
    }
}