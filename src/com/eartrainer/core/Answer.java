package com.eartrainer.core;


public abstract class Answer {
    protected Integer frequency;

    public Answer(Integer frequency) {
        this.frequency = frequency;
    }

    public String asString() {
        return frequency.toString() + "Hz";
    }

    public boolean equals(Answer other) {
        return asString().equals(other.asString());
    }

    public Integer getFrequency() {
        return frequency;
    }
}
