package com.eartrainer.view;

import android.content.Context;
import com.eartrainer.core.Answer;
import com.eartrainer.core.EQRecognitionSettings;
import com.eartrainer.utils.Utils;


public class EQAnswerPanel extends AnswerSelectionPanel {
    public EQAnswerPanel(Context context, EQRecognitionSettings eqSettings) {
        super(context, createGainStrings(eqSettings.getGainsDecibels()), Utils.toStringArray(eqSettings.getFrequencies()));
    }

    @Override
    public Answer getSelectedAnswer() {
        return null;
    }

    private static String[] createGainStrings(Integer[] gains) {
        String[] gainStrings = new String[gains.length];
        for (int i = 0; i < gains.length; ++i) {
            Integer gain = gains[i];
            gainStrings[i] = ((gain > 0)? "+" : "") + gain.toString() + "dB";
        }
        return gainStrings;
    }
}
