package com.eartrainer.activity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.TextView;
import com.eartrainer.R;
import com.eartrainer.core.QAPair;
import com.eartrainer.core.QAType;
import com.eartrainer.core.TonesQAType;
import com.eartrainer.session.Session;


public class ViewResultsTest extends ActivityUnitTestCase<ViewResults> {

    private Intent launchIntent;

    public ViewResultsTest() {
        super(ViewResults.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        launchIntent = new Intent(getInstrumentation().getTargetContext(), ViewResults.class);
    }

    @MediumTest
    public void testInitial() {
        startActivity(launchIntent, null, null);
        TextView textTonesResults = (TextView) getActivity().findViewById(R.id.text_tones_results);
        TextView textEqResults = (TextView) getActivity().findViewById(R.id.text_eq_results);
        // check the initial text
        String textExpected = "0/0";
        assertEquals(textExpected, textTonesResults.getText().toString());
        assertEquals(textExpected, textEqResults.getText().toString());
    }

    @MediumTest
    public void testNullQA() {
        // nulls shouldn't change text
        Session.getInstance().getQAHistory().add(null);

        startActivity(launchIntent, null, null);
        TextView textTonesResults = (TextView) getActivity().findViewById(R.id.text_tones_results);
        TextView textEqResults = (TextView) getActivity().findViewById(R.id.text_eq_results);
        String textExpected = "0/0";
        assertEquals(textExpected, textTonesResults.getText().toString());
        assertEquals(textExpected, textEqResults.getText().toString());

        Session.getInstance().getQAHistory().clear();
    }

    @MediumTest
    public void testNullAnswers() {
        // nulls shouldn't change text
        Session.getInstance().getQAHistory().add(new QAPair(null, null));

        startActivity(launchIntent, null, null);
        TextView textTonesResults = (TextView) getActivity().findViewById(R.id.text_tones_results);
        TextView textEqResults = (TextView) getActivity().findViewById(R.id.text_eq_results);
        String textExpected = "0/0";
        assertEquals(textExpected, textTonesResults.getText().toString());
        assertEquals(textExpected, textEqResults.getText().toString());

        Session.getInstance().getQAHistory().clear();
    }

    @MediumTest
    public void testCorrect() {
        // check if correctly calculates result
        QAType qa = new TonesQAType(ToneType.SAW, 100);
        Session.getInstance().getQAHistory().add(new QAPair(qa, qa));

        startActivity(launchIntent, null, null);
        TextView textTonesResults = (TextView) getActivity().findViewById(R.id.text_tones_results);
        TextView textEqResults = (TextView) getActivity().findViewById(R.id.text_eq_results);
        String tonesTextExpected = "1/1";
        String eqTextExpected = "0/0";
        assertEquals(tonesTextExpected, textTonesResults.getText().toString());
        assertEquals(eqTextExpected, textEqResults.getText().toString());

        Session.getInstance().getQAHistory().clear();
    }
}
