package com.eartrainer;


import com.eartrainer.activity.ToneType;

public class TonesRecognitionSettings {
    private long playLength;
    private ToneType[] toneTypes;
    private Integer[] frequencies;

    public TonesRecognitionSettings(long playLength, ToneType[] toneTypes, Integer[] frequencies) {
        this.playLength = playLength;
        this.toneTypes = toneTypes;
        this.frequencies = frequencies;
    }

    public long getPlayLength() {
        return playLength;
    }

    public void setPlayLength(long playLength) {
        this.playLength = playLength;
    }

    public ToneType[] getToneTypes() {
        return toneTypes;
    }

    public void setToneTypes(ToneType[] toneTypes) {
        this.toneTypes = toneTypes;
    }

    public Integer[] getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(Integer[] frequencies) {
        this.frequencies = frequencies;
    }
}
