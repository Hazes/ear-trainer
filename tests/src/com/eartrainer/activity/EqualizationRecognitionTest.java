package com.eartrainer.activity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;


public class EqualizationRecognitionTest extends ActivityUnitTestCase<EqualizationRecognition> {
    private Intent launchIntent;

    public EqualizationRecognitionTest() {
        super(EqualizationRecognition.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        launchIntent = new Intent(getInstrumentation().getTargetContext(), ViewResults.class);
    }
}
