package Playground;

public class main {
    public static void main(String[] args) {
//        Playground.Playground_inner a = a.new Playground_inner();
        Playground p = new Playground();
        Playground.Playground_inner p_inner =  p.new Playground_inner();
        System.out.println(p.p());
        System.out.println(p_inner);

        BouncingBallGame ballGame = new BouncingBallGame();
    }
}
