package HW3;

public class Player {
    private boolean human;
    private String name;

    private Player(boolean human, String name) {
        this.human = human;
        this.name = name;
    }

    public static Player createHuman(String name) {
        return new Player(true, name);
    }

    public static Player createComputer() {
        return new Player(false, "AI");
    }

    public boolean isHuman() {
        return human;
    }

    public String getName() {
        return name;
    }
}
