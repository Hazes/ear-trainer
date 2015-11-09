package com.eartrainer;

import java.util.Random;


public class ArrayRandomizer<T> {

    private T[] arrayToRandomize;

    public ArrayRandomizer(T[] arrayToRandomize) {
        setArrayToRandomize(arrayToRandomize);
    }

    public T[] getArrayToRandomize() {
        return arrayToRandomize;
    }

    public void setArrayToRandomize(T[] arrayToRandomize) {
        this.arrayToRandomize = arrayToRandomize;
    }

    public T randomize() {
        return arrayToRandomize[new Random().nextInt(arrayToRandomize.length)];
    }
}
