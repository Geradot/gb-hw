package homework;

public class Cat extends Animal {
    Cat(String name) {
        super(name);
        this.runLimit = 200;
    }

    @Override
    public void swim(int meters) {
        System.out.println("Команда для " + name + ": проплыви " + meters + " метров.");
        System.out.println("Коты не умеют плавать.");
    }
}
