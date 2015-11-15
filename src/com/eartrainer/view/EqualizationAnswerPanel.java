package com.eartrainer.view;

import android.content.Context;
import com.eartrainer.core.EqualizationQAType;
import com.eartrainer.core.QAType;
import com.eartrainer.core.EQRecognitionSettings;
import com.eartrainer.utils.Utils;


public class EqualizationAnswerPanel extends AnswerSelectionPanel {
    public EqualizationAnswerPanel(Context context, EQRecognitionSettings eqSettings) {
        super(context, createGainStrings(eqSettings.getGainsDecibels()), Utils.toStringArray(eqSettings.getFrequencies()));
    }

    @Override
    public QAType getSelectedAnswer() {
        String selectedGainStr = getSelectedRadioButtonContent();
        String selectedFrequencyStr = getSelectedGridButtonContent();
        if (selectedGainStr != null && selectedFrequencyStr != null) {
            // strip "dB" and "+"
            selectedGainStr = selectedGainStr.replace("dB", "");
            selectedGainStr = selectedGainStr.replace("+", "");
            Integer gain = Integer.parseInt(selectedGainStr);
            Integer frequency = Integer.parseInt(selectedFrequencyStr);
            return new EqualizationQAType(frequency, gain);
        }
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
