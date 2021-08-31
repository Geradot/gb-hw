package homework;

public class Dog extends Animal {
    private int swimLimit;

    Dog(String name) {
        super(name);
        this.runLimit = 500;
        this.swimLimit = 10;
    }

    @Override
    public void swim(int value) {
        super.swim(value);
        if (value <= swimLimit) {
            System.out.println(name + " проплыл " + value + " метров.");
        } else {
            System.out.println(name + " не может проплыть " + value + " метров. " +
                    "Его предел — " + swimLimit + " метров.");
        }
        System.out.println();
    }
}
