package com.eartrainer.audio.unit.source.osc;


public class SquareOsc extends Osc {
    private float sampleRate;
    private int phase;
    private final int MAX_HARMONIC = 20; // can be lowered for performance

    public SquareOsc(float frequencyHz) {
        this(frequencyHz, 0.8f);
    }

    public SquareOsc(float frequencyHz, float amplitude) {
        super(frequencyHz, amplitude);
    }

    @Override
    public void generate(float[] audioBuffer) {
        float amp = getAmplitude();
        float freq = getFrequency();
        double scale = amp * 4 / Math.PI;
        for (int i = 0; i < audioBuffer.length; ++i) {
            for (int n = 1; n * freq < 0.5 * sampleRate && n < MAX_HARMONIC; n += 2) {
                audioBuffer[i] += scale / n * Math.sin(2 * Math.PI * phase * n * freq / sampleRate);
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
