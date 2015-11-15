package com.eartrainer.audio.unit.filter;

import com.eartrainer.dsp.BiQuadFilter;


public class PeakFilter
        implements AudioFilter {

    private BiQuadFilter biQuad;
    private float centerFrequency;
    private float gain;
    private float bandwidth;

    public PeakFilter(float sampleRate, float centerFrequency, float gain) {
        this(sampleRate, centerFrequency, gain, 1.f);
    }

    public PeakFilter(float sampleRate, float centerFrequency, float gain, float bandwidthOctaves) {
        this.centerFrequency = centerFrequency;
        this.gain = gain;
        this.bandwidth = bandwidthOctaves;
        biQuad = new BiQuadFilter(sampleRate);
        biQuad.setupPeaking(centerFrequency, gain, bandwidth);
    }

    @Override
    public void process(float[] buffer) {
        biQuad.process(buffer);
    }

    @Override
    public void prepare(int sampleRate, int numSamples) {
        biQuad = new BiQuadFilter((float)sampleRate);
    }

    @Override
    public void release() {
        // not implemented
    }

    public float getCenterFrequency() {
        return centerFrequency;
    }

    public float getGain() {
        return gain;
    }

    public float getBandwidth() {
        return bandwidth;
    }
}
