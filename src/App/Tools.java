package App;

import java.util.Random;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 06.09.2021
 */

public class Tools {

    public static Random random = new Random();

    public static int randomValue(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
