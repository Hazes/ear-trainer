package com.eartrainer.core;


public class EQAnswer extends Answer {
    private Integer gain;

    public EQAnswer(Integer frequency, Integer gainDecibels) {
        super(frequency);
        this.gain = gainDecibels;
    }

    @Override
    public String asString() {
        return super.asString() + " " + ((gain > 0)? "+" : "") + gain + "dB";
    }
}
