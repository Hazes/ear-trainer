package com.eartrainer.view;


import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.eartrainer.R;

public class RecognitionTrainingView extends LinearLayout {

    private Button buttonNext;
    private Button buttonAnswer;
    private ProgressBar progressBar;
    private AnswerSelectionPanel answerPanel;
    private TextView textViewCorrect;

    public RecognitionTrainingView(Context context, String nextButtonText, AnswerSelectionPanel answerPanel) {
        super(context);

        setOrientation(LinearLayout.VERTICAL);

        buttonNext = new Button(context);
        buttonNext.setText(nextButtonText);
        addView(buttonNext);

        int style = android.R.style.Widget_ProgressBar_Horizontal;
        progressBar = new ProgressBar(new ContextThemeWrapper(context, style), null, style);
        addView(progressBar);

        this.answerPanel = answerPanel;
        addView(answerPanel);

        buttonAnswer = new Button(context);
        buttonAnswer.setText("Answer");
        addView(buttonAnswer);

        textViewCorrect = new TextView(context);
        addView(textViewCorrect);
    }

    public Button getButtonNext() {
        return buttonNext;
    }

    public Button getButtonAnswer() {
        return buttonAnswer;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public AnswerSelectionPanel getAnswerPanel() {
        return answerPanel;
    }

    public TextView getTextViewCorrect() {
        return textViewCorrect;
    }
}
