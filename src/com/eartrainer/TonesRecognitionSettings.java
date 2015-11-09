package com.eartrainer;


public class TonesRecognitionSettings {
    private long tonePlayLength;

    public TonesRecognitionSettings(long tonePlayLength) {
        this.tonePlayLength = tonePlayLength;
    }

    public long getTonePlayLength() {
        return tonePlayLength;
    }

    public void setTonePlayLength(long tonePlayLength) {
        this.tonePlayLength = tonePlayLength;
    }
}
