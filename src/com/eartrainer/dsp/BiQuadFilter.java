package com.eartrainer.dsp;

import java.lang.Math;


public class BiQuadFilter {
    private float a1, a2, b0, b1, b2;
    private float z1, z2;
    private float sampleRate;

    public BiQuadFilter(float sampleRate) {
        this.sampleRate = sampleRate;
    }

    public void setupPeaking(float centerFreq, float gainDecibels, float bandwidthOctaves) {
        float w0 = 2.0f * (float)Math.PI * centerFreq / sampleRate;
        float gain = (float)Math.pow(10.0f, gainDecibels * 0.025f); // convert from dB to unit gain
        double Q = Math.sqrt(Math.pow(2, bandwidthOctaves)) / (Math.pow(2, bandwidthOctaves) - 1); // BW to Q factor
        float alpha = (float)Math.sin(w0) / (2.0f * (float)Q);
        b0 = 1.0f + alpha * gain;
        b1 = -2.0f * (float)Math.cos(w0);
        b2 = 1.0f - alpha * gain;
        float a0 = 1.0f + alpha / gain;
        a1 = -2.0f * (float)Math.cos(w0);
        a2 = 1.0f - alpha / gain;
        // normalization by a0
        float inv_a0 = 1.0f / a0;
        a1 *= inv_a0;
        a2 *= inv_a0;
        b0 *= inv_a0;
        b1 *= inv_a0;
        b2 *= inv_a0;
    }

    public float process(float in) {
        float iir = in - a1 * z1 - a2 * z2;
        float fir = b0 * iir + b1 * z1 + b2 * z2;
        z2 = z1;
        z1 = iir;
        return fir;
    }

    public void process(float[] in) {
        for (int i = 0; i < in.length; ++i)
            in[i] = process(in[i]);
    }

    public void reset() {
        z1 = z2 = 0;
    }
}
