package com.eartrainer.utils;


public abstract class Utils {

    public static <T> String[] toStringArray(T[] array) {
        String[] strArray = new String[array.length];
        for (int i = 0; i < strArray.length; ++i)
            strArray[i] = array[i].toString();
        return strArray;
    }
}
