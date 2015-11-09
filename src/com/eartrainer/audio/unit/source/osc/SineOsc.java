package com.eartrainer.audio.unit.source.osc;


public class SineOsc extends Osc {

    private float phase;
    private int sampleRate;

    public SineOsc(float frequencyHz) {
        super(frequencyHz);
    }

    @Override
    public float[] generate(int numSamples) {
        float[] out = new float[numSamples];
        float freq = getFrequency();
        for (int i = 0; i < numSamples; ++i) {
            out[i] = (float)Math.sin(2 * Math.PI * phase * freq / sampleRate);
            phase++;
        }
        return out;
    }

    @Override
    public void prepare(int sampleRate, int numSamples) {
        this.sampleRate = sampleRate;
    }

    @Override
    public void release() {
        // does nothing
    }
}
