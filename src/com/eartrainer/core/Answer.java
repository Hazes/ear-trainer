package com.eartrainer.core;


public abstract class Answer {
    protected Integer frequency;

    public Answer(Integer frequency) {
        this.frequency = frequency;
    }

    public String asString() {
        return frequency.toString() + "Hz";
    }

    public Integer getFrequency() {
        return frequency;
    }
}
