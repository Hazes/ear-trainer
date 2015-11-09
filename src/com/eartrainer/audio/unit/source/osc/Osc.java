package com.eartrainer.audio.unit.source.osc;

import com.eartrainer.audio.unit.source.AudioSource;


public abstract class Osc implements AudioSource {

    private float frequency;
    private float gain;

    public Osc (float frequencyHz) {
        setFrequency(frequencyHz);
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequencyHz) {
        this.frequency = frequencyHz;
    }
}
