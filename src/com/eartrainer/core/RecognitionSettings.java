package com.eartrainer.core;


public abstract class RecognitionSettings {
    private long playLength;
    private Integer[] frequencies;

    public RecognitionSettings(long playLength, Integer[] frequencies) {
        this.playLength = playLength;
        this.frequencies = frequencies;
    }

    public long getPlayLength() {
        return playLength;
    }

    public void setPlayLength(long playLength) {
        this.playLength = playLength;
    }

    public Integer[] getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(Integer[] frequencies) {
        this.frequencies = frequencies;
    }
}
