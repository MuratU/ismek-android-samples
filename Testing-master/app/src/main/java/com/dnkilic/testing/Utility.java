package com.dnkilic.testing;

public class Utility {

    //TODO to generate test class press ctrl + shift + t

    public static String concator(String text1, String text2) throws Exception {

        if(text1 == null || text1.isEmpty() || text2 == null || text2.isEmpty()) {
            throw new Exception();
        }

        return text1 + text2;
    }
}
