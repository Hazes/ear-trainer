package com.eartrainer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.eartrainer.R;
import com.eartrainer.core.EqualizationQAType;
import com.eartrainer.core.QAPair;
import com.eartrainer.core.TonesQAType;
import com.eartrainer.session.Session;

import java.util.ArrayList;


public class ViewResults extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        updateResults();
    }

    private void updateResults() {
        ArrayList<QAPair> qaHistory = Session.getInstance().getQAHistory();
        updateTonesRecognitionResults(qaHistory);
        updateEqRecognitionResults(qaHistory);
    }

    private void updateTonesRecognitionResults(ArrayList<QAPair> qaHistory) {
        // search through all tones recognition QA's
        int numTotal = 0;
        int numCorrect = 0;
        for (QAPair qaPair : qaHistory) {
            if (qaPair.getQuestion() instanceof TonesQAType) {
                numTotal++;
                if (qaPair.isAnswerCorrect())
                    numCorrect++;
            }
        }
        TextView toneResults = (TextView)findViewById(R.id.text_tones_results);
        toneResults.setText(String.valueOf(numCorrect) + "/" + String.valueOf(numTotal));
    }

    private void updateEqRecognitionResults(ArrayList<QAPair> qaHistory) {
        // search through all tones recognition QA's
        int numTotal = 0;
        int numCorrect = 0;
        for (QAPair qaPair : qaHistory) {
            if (qaPair.getQuestion() instanceof EqualizationQAType) {
                numTotal++;
                if (qaPair.isAnswerCorrect())
                    numCorrect++;
            }
        }
        TextView eqResults = (TextView)findViewById(R.id.text_eq_results);
        eqResults.setText(String.valueOf(numCorrect) + "/" + String.valueOf(numTotal));
    }
}



