package config;

import java.util.Random;

public class Functions {
    public static int getRandomInRange(int min, int max) {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        return i += min;
    }
}
