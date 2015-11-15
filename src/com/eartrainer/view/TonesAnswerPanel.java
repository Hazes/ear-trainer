package com.eartrainer.view;

import android.content.Context;
import com.eartrainer.TonesRecognitionSettings;
import com.eartrainer.core.Answer;
import com.eartrainer.utils.Utils;


public class TonesAnswerPanel extends AnswerSelectionPanel {

    public TonesAnswerPanel(Context context, TonesRecognitionSettings tonesRecognitionSettings) {
        super(context, tonesRecognitionSettings.getToneTypes(), Utils.toStringArray(tonesRecognitionSettings.getFrequencies()));
    }

    @Override
    public Answer getSelectedAnswer() {
        return null;
    }
}
