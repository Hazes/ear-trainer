package com.eartrainer.core;


public abstract class QAType {
    protected Integer frequency;

    public QAType(Integer frequency) {
        this.frequency = frequency;
    }

    public String asString() {
        return frequency.toString() + "Hz";
    }

    public boolean equals(QAType other) {
        if (other != null)
            return asString().equals(other.asString());
        return false;
    }

    public Integer getFrequency() {
        return frequency;
    }
}
