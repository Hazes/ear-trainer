package com.eartrainer.audio.unit.source.osc;


import com.eartrainer.audio.unit.source.AudioSource;

public abstract class Osc implements AudioSource {

    private float frequency;
    private float amplitude;

    public Osc (float frequencyHz, float amplitude) {
        setFrequency(frequencyHz);
        setAmplitude(amplitude);
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequencyHz) {
        this.frequency = frequencyHz;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }
}
