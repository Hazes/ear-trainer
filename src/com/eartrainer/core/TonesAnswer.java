package com.eartrainer.core;

import com.eartrainer.activity.ToneType;
import com.eartrainer.core.Answer;

public class TonesAnswer extends Answer {

    private ToneType toneType;

    public TonesAnswer(ToneType toneType, Integer frequency) {
        super(frequency);
        this.toneType = toneType;
    }

    @Override
    public String asString() {
        return toneType.toString() + " " + super.asString();
    }
}
