package com.eartrainer.audio.sources;

import com.eartrainer.audio.AudioUnit;


public interface AudioSource extends AudioUnit {
    float[] generate(int numSamples);
}
