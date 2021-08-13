public class HomeWorkApp {
    public static void main(String[] args) {
        printThreeWords();
        System.out.println("\n");
        checkSumSign();
        System.out.println("\n");
        printColor();
        System.out.println("\n");
        compareNumbers();
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.print("Apple");
    } // Конец метода printThreeWords()

    public static void checkSumSign() {
        int a = 5;
        int b = 10;
        int result = a + b;
        if (result >= 0) {
            System.out.println("Сумма положительная");
        }
        else {
            System.out.println("Сумма отрицательная");
        }
    } // Конец метода checkSumSign()

    public static void printColor() {
        int value = 20;
        if (value <= 0) {
            System.out.println("Красный");
        }
        else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        }
        else if (value > 100) {
            System.out.println("Зеленый");
        }
    } // Конец метода printColor()

    public static void compareNumbers() {
        int a = 10;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        }
        else {
            System.out.println(" a < b");
        }
    } // Конец метода compareNumbers()
}
