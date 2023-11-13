package com.neighborhood.global.util;

import java.util.Random;

public class RandomCodeUtil {

    public static String generateResultCode(int size) {
        Random rnd = new Random();
        StringBuffer randomCode=new StringBuffer();
        for (int i = 1; i <= size; i++) {
            if (rnd.nextBoolean())
                randomCode.append((char)(rnd.nextInt(26)+65));
            else
                randomCode.append(rnd.nextInt(10));
        }
        return randomCode.toString();
    }
}
