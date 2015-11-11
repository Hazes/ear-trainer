package com.eartrainer.audio.unit;


public interface AudioUnit {
    void prepare(int sampleRate, int bufferNumSamples);
    void release();
}
