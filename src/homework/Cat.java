package homework;

public class Cat extends Animal {
    public static int catsCount = 0;
    Cat(String name) {
        super(name);
        this.runLimit = 200;
    }

    @Override
    public String swim(int meters) {
        return super.swim(meters) + "Коты не умеют плавать.\n";
    }
}
