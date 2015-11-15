package com.eartrainer.utils;


import android.graphics.Color;

public abstract class Utils {

    public static <T> String[] toStringArray(T[] array) {
        String[] strArray = new String[array.length];
        for (int i = 0; i < strArray.length; ++i)
            strArray[i] = array[i].toString();
        return strArray;
    }

    public static int setAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
