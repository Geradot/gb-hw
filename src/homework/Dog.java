package homework;

public class Dog extends Animal {
    public static int dogsCount = 0;
    private int swimLimit;

    Dog(String name) {
        super(name);
        this.runLimit = 500;
        this.swimLimit = 10;
    }

    @Override
    public String swim(int meters) {
        String s = super.swim(meters);

        return s += (meters <= swimLimit) ? name + " проплыл " + meters + " метров.\n" :
                (name + " не может проплыть " + meters + " метров. " +
                        "Его предел — " + swimLimit + " метров.\n");
    }
}
