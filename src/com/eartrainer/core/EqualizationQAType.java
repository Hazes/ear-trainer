package com.eartrainer.core;


public class EqualizationQAType extends QAType {
    private Integer gain;

    public EqualizationQAType(Integer frequency, Integer gainDecibels) {
        super(frequency);
        this.gain = gainDecibels;
    }

    @Override
    public String asString() {
        return super.asString() + " " + ((gain > 0)? "+" : "") + gain + "dB";
    }
}
