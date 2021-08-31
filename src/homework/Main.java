package homework;

public class Main {

    public static void main(String[] args) {
        int catsCount = 0;
        int dogsCount = 0;
        Animal[] animals = {new Cat("Cat 1"), new Dog("Dog 1"), new Cat("Cat 2"), new Cat("Cat 3"), new Dog("Dog 2")};
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] instanceof Cat) {
                catsCount++;
                // Каст не нужен, поскольку методы родительские
                animals[i].run(150);
                animals[i].swim(5);
            } else if (animals[i] instanceof Dog) {
                dogsCount++;
                animals[i].run(100);
                animals[i].run(600);
                animals[i].swim(5);
                animals[i].swim(15);
            }
            System.out.println();
        }
        System.out.println("Котов: " + catsCount + "; Собак: " + dogsCount +
                "; Всего животных: " + (catsCount + dogsCount));
    }

}
