package com.eartrainer.audio.unit.source;

import com.eartrainer.audio.unit.AudioUnit;


public interface AudioSource extends AudioUnit {
    float[] generate(int numSamples);
}
