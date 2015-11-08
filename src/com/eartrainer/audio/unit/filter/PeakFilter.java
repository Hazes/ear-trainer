package com.eartrainer.audio.unit.filter;

import com.eartrainer.dsp.BiQuadFilter;


public class PeakFilter
        implements AudioFilter {

    private BiQuadFilter biQuad;
    private float centerFrequency;
    private float gain;
    private float bandwidth;

    @Override
    public void process(float[] buffer) {
        for (int i = 0; i < buffer.length; ++i) {
            buffer[i] = biQuad.process(i);
        }
    }

    @Override
    public void prepare(int sampleRate, int numSamples) {
        biQuad = new BiQuadFilter((float)sampleRate);
    }

    @Override
    public void release() {
        // nothing needed
    }

    public float getCenterFrequency() {
        return centerFrequency;
    }

    public void setCenterFrequency(float centerFrequency) {
        this.centerFrequency = centerFrequency;
        updateFilter();
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float gain) {
        this.gain = gain;
        updateFilter();
    }

    public float getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(float bandwidth) {
        this.bandwidth = bandwidth;
        updateFilter();
    }

    private void updateFilter() {
        biQuad.setupPeaking(getCenterFrequency(), getGain(), getBandwidth());
    }
}
