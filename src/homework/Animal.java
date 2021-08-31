package homework;

public abstract class Animal {
    protected String name;
    protected int runLimit;

    protected Animal(String name) {
        this.name = name;
    }

    protected void run(int meters) {
        System.out.println("Команда для " + name + ": пробеги " + meters + " метров!");
        if (meters <= runLimit) {
            System.out.println(name + " пробежал " + meters + " метров.");
        } else {
            System.out.println(name + " не может пробежать " + meters + " метров. " +
                    "Его предел — " + runLimit + " метров.");
        }
        System.out.println();
    }

    protected void swim(int meters) {
        System.out.println("Команда для " + name + ": проплыви " + meters + " метров!");
    }

}
