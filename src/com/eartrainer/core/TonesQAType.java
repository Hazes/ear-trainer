package com.eartrainer.core;

import com.eartrainer.activity.ToneType;

public class TonesQAType extends QAType {

    private ToneType toneType;

    public TonesQAType(ToneType toneType, Integer frequency) {
        super(frequency);
        this.toneType = toneType;
    }

    @Override
    public String asString() {
        return toneType.toString() + " " + super.asString();
    }
}
