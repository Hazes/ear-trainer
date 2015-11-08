package com.eartrainer.audio;


public interface AudioUnit {
    void prepare(int sampleRate, int numSamples);
    void release();
}
