package homework;

public abstract class Animal {
    protected String name;
    protected int runLimit;

    protected Animal(String name) {
        this.name = name;
    }

    protected String run(int meters) {
        String s = "Команда для " + name + ": пробеги " + meters + " метров!\n";
        
        return s += (meters <= runLimit) ? name + " пробежал " + meters + " метров.\n" :
                name + " не может пробежать " + meters + " метров. " +
                "Его предел — " + runLimit + " метров.\n";
    }

    protected String swim(int meters) {
        return "Команда для " + name + ": проплыви " + meters + " метров!\n";
    }

}
