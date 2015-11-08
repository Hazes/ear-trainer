package com.eartrainer.audio.unit.filter;

import com.eartrainer.audio.unit.AudioUnit;


public interface AudioFilter extends AudioUnit {
    void process(float[] buffer);
}
