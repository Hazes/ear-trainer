package com.eartrainer.core;


public class EQRecognitionSettings extends RecognitionSettings {
    private Integer[] gainsDecibels;

    public EQRecognitionSettings(long playLength, Integer[] gainsDecibels, Integer[] frequencies) {
        super(playLength, frequencies);
        setGainsDecibels(gainsDecibels);
    }

    public Integer[] getGainsDecibels() {
        return gainsDecibels;
    }

    public void setGainsDecibels(Integer[] gainsDecibels) {
        this.gainsDecibels = gainsDecibels;
    }
}
