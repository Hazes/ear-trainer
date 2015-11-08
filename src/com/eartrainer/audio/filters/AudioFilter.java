package com.eartrainer.audio.filters;

import com.eartrainer.audio.AudioUnit;


public interface AudioFilter extends AudioUnit {
    void process(float[] buffer);
}
