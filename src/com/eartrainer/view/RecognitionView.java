package com.eartrainer.view;


import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Space;

public class RecognitionView extends LinearLayout {

    private Button buttonNext;
    private Button buttonAnswer;
    private ProgressBar progressBar;
    private AnswerSelectionPanel answerPanel;
    private TextView textViewCorrect;

    public RecognitionView(Context context, String nextButtonText, AnswerSelectionPanel answerPanel) {
        super(context);

        setOrientation(LinearLayout.VERTICAL);

        buttonNext = new Button(context);
        buttonNext.setText(nextButtonText);
        addView(buttonNext);

        int style = android.R.style.Widget_ProgressBar_Horizontal;
        progressBar = new ProgressBar(new ContextThemeWrapper(context, style), null, style);
        addView(progressBar);

        // add some space between "progress" bar and answer panel
        addView(new Space(context), new LayoutParams(30, 30));

        this.answerPanel = answerPanel;
        addView(answerPanel);

        buttonAnswer = new Button(context);
        buttonAnswer.setText("Answer");
        addView(buttonAnswer);

        textViewCorrect = new TextView(context);
        textViewCorrect.setGravity(Gravity.CENTER_HORIZONTAL);
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
