import java.util.Scanner;

public class HomeWork3 {
    public static void main(String[] args) {

    }

    // 1 задание
    public static void invertingBits(int size) {
        int[] arrInt = new int[size];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < arrInt.length; i++)
            arrInt[i] = scanner.nextInt();

        System.out.print("Созданный массив >>> ");
        for (int i : arrInt) System.out.print(i + " ");
        System.out.println();

        // инвертируем 1 и 0
        for (int i = 0; i < arrInt.length; i++)
            arrInt[i] = (arrInt[i] == 1) ? 0 : 1;

        System.out.print("Инвертированный массив >>> ");
        for (int i : arrInt) System.out.print(i + " ");
    }

    // 2 задание
    public static void array100() {
        int[] arrInt = new int[100];
        for (int i = 0; i < arrInt.length; i++)
            arrInt[i] = i + 1;

        for (int i : arrInt) System.out.print(arrInt[i] + " ");
    }

    // 3 задание
    public static void multiplicationBy2() {
        int[] arrInt = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i : arrInt)
            if (arrInt[i] < 6) arrInt[i] *= 2;

        for (int i : arrInt) System.out.print(i + " ");
    }

    // 4 задание
    public static void array2D() {
        int[][] arrInt = new int[5][5];

        // Главная диагональ
        for (int i = 0; i < arrInt.length; i++)
            for (int j = 0; j < arrInt[i].length; j++)
                arrInt[i][j] = (i == j) ? 1 : 0;

        // Побочная диагональ
        int maxIdx = arrInt.length - 1;
        for (int i = arrInt.length - 1; i >= 0; i--)
            arrInt[i][maxIdx - i] = 1;

        for (int i = 0; i < arrInt.length; i++) {
            for (int j = 0; j < arrInt[i].length; j++) {
                System.out.print(arrInt[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 5 задание
    public static int[] arrayInitialValue(int len, int initialValue) {
        int[] arrInt = new int[len];
        for (int i = 0; i < arrInt.length; i++)
            arrInt[i] = initialValue;
        return arrInt;
    }

    // 6 задание
    public static void arrayMinMax() {
        int[] arrInt = new int[]{1, 10, 4, 8, 3, 12, 0, 2};

        int max = arrInt[0];
        for (int i = 1; i < arrInt.length; i++)
            if (arrInt[i] > max)
                max = arrInt[i];

        int min = arrInt[0];
        for (int i = 0; i < arrInt.length; i++)
            if (arrInt[i] < min)
                min = arrInt[i];

        System.out.println("Max: " + max + "\nMin: " + min);
    }
}
