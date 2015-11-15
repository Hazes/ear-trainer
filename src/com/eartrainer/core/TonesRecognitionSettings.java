package com.eartrainer.core;


import com.eartrainer.activity.ToneType;

public class TonesRecognitionSettings extends RecognitionSettings {
    private ToneType[] toneTypes;

    public TonesRecognitionSettings(long playLength, ToneType[] toneTypes, Integer[] frequencies) {
        super(playLength, frequencies);
        setToneTypes(toneTypes);
    }

    public ToneType[] getToneTypes() {
        return toneTypes;
    }

    public void setToneTypes(ToneType[] toneTypes) {
        this.toneTypes = toneTypes;
    }
}
