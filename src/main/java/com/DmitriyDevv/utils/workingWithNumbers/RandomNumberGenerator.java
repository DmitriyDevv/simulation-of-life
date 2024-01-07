package com.DmitriyDevv.utils.workingWithNumbers;

import java.util.Random;

public class RandomNumberGenerator {
    public static int getRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
