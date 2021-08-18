import java.util.GregorianCalendar;

public class HomeWorkApp {
    public static void main(String[] args) {
        System.out.println(numberInRange(10, 20));
        isPositiveOrNegative(0);
        System.out.println(isNegative(-10));
        printWordNTimes("Hello", 5);
        System.out.println(leapYear(2004));
    }

    // 1 задание
    public static boolean numberInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 2 задание
    public static void isPositiveOrNegative(int a) {
        if (a >= 0) System.out.println("Число положительное");
        else System.out.println("Число отрицательное");
    }

    // 3 задание
    public static boolean isNegative(int a) {
        return a < 0;
    }

    // 4 задание
    public static void printWordNTimes(String s, int k) {
        for (int i = 0; i < k; i++)
            System.out.println(s);
    }

    // 5 задание
    public static boolean leapYear(int year) {
        if ((year % 4 == 0)) {
            return (year % 100 != 0) || (year % 400 == 0);
        } else {
            return false;
        }
    }
}
