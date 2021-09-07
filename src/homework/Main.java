package homework;

import static homework.Cat.catsCount;
import static homework.Dog.dogsCount;

public class Main {

    public static void main(String[] args) {
        Animal[] animals = {new Cat("Cat 1"), new Dog("Dog 1"), new Cat("Cat 2"), new Cat("Cat 3"), new Dog("Dog 2")};
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] instanceof Cat) {
                // Каст не нужен, поскольку методы родительские
                catsCount++;
                System.out.println(animals[i].run(150));
                System.out.println(animals[i].swim(5));
            } else if (animals[i] instanceof Dog) {
                dogsCount++;
                System.out.println(animals[i].run(100));
                System.out.println(animals[i].run(600));
                System.out.println(animals[i].swim(5));
                System.out.println(animals[i].swim(15));
            }
            System.out.println();
        }
        System.out.println("Котов: " + catsCount + "; Собак: " + dogsCount +
                "; Всего животных: " + (catsCount + dogsCount));
    }

}
