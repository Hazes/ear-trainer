package com.eartrainer.view;

import android.content.Context;
import com.eartrainer.core.TonesQAType;
import com.eartrainer.core.TonesRecognitionSettings;
import com.eartrainer.activity.ToneType;
import com.eartrainer.core.QAType;
import com.eartrainer.utils.Utils;


public class TonesAnswerPanel extends AnswerSelectionPanel {

    public TonesAnswerPanel(Context context, TonesRecognitionSettings tonesSettings) {
        super(context, Utils.toStringArray(tonesSettings.getToneTypes()), Utils.toStringArray(tonesSettings.getFrequencies()));
    }

    @Override
    public QAType getSelectedAnswer() {
        String selectedToneStr = getSelectedRadioButtonContent();
        String selectedFrequencyStr = getSelectedGridButtonContent();
        if (selectedToneStr != null && selectedFrequencyStr != null) {
            ToneType toneType = ToneType.valueOf(selectedToneStr);
            Integer frequency = Integer.parseInt(selectedFrequencyStr);
            return new TonesQAType(toneType, frequency);
        }
        return null;
    }
}
