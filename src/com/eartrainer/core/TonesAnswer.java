package com.eartrainer.core;

import com.eartrainer.activity.ToneType;
import com.eartrainer.core.Answer;

public class TonesAnswer implements Answer {

    private ToneType toneType;
    private Integer frequency;

    public TonesAnswer(ToneType toneType, Integer frequency) {
        this.toneType = toneType;
        this.frequency = frequency;
    }

    @Override
    public String asString() {
        return toneType.toString() + " " + frequency.toString() + "Hz";
    }

    public ToneType getToneType() {
        return toneType;
    }

    public Integer getFrequency() {
        return frequency;
    }
}
