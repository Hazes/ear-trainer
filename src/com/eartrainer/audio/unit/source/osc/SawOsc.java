package com.eartrainer.audio.unit.source.osc;


public class SawOsc extends Osc {

    private float sampleRate;
    private int phase;
    private final int MAX_HARMONIC = 20; // can be lowered for performance

    public SawOsc(float frequencyHz) {
        this(frequencyHz, 0.8f);
    }

    public SawOsc(float frequencyHz, float amplitude) {
        super(frequencyHz, amplitude);
    }

    @Override
    public void generate(float[] audioBuffer) {
        float amp = getAmplitude();
        float freq = getFrequency();
        double scale = amp * 2 / Math.PI;
        for (int i = 0; i < audioBuffer.length; ++i) {
            float sign = -1;
            for (int n = 1; n * freq < 0.5 * sampleRate && n < MAX_HARMONIC; ++n) {
                audioBuffer[i] += sign * scale / n * Math.sin(2 * Math.PI * phase * n * freq / sampleRate);
                sign *= -1;
            }
            phase++;
        }
    }

    @Override
    public void prepare(int sampleRate, int bufferNumSamples) {
        this.sampleRate = sampleRate;
        phase = 0;
    }

    @Override
    public void release() {
        // not implemented
    }
}
