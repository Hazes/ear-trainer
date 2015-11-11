package com.eartrainer.audio.unit.source.osc;


public class SineOsc extends Osc {

    private int phase;
    private int sampleRate;

    public SineOsc(float frequencyHz) {
        this(frequencyHz, 0.8f);
    }

    public SineOsc(float frequencyHz, float amplitude) {
        super(frequencyHz, amplitude);
    }

    @Override
    public void generate(float[] audioBuffer) {
        float freq = getFrequency();
        for (int i = 0; i < audioBuffer.length; ++i) {
            audioBuffer[i] = getAmplitude() * (float)Math.sin(2 * Math.PI * phase * freq / sampleRate);
            phase++;
        }
    }

    @Override
    public void prepare(int sampleRate, int numSamples) {
        this.sampleRate = sampleRate;
        phase = 0;
    }

    @Override
    public void release() {
        // does nothing
    }
}
