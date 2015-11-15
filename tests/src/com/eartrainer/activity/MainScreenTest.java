package com.eartrainer.activity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import com.eartrainer.R;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.eartrainer.activity.MainScreenTest \
 * com.eartrainer.tests/android.test.InstrumentationTestRunner
 */
public class MainScreenTest extends ActivityUnitTestCase<MainScreen> {

    private Intent launchMainIntent;

    public MainScreenTest() {
        super(MainScreen.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        launchMainIntent = new Intent(getInstrumentation().getTargetContext(), MainScreen.class);
        startActivity(launchMainIntent, null, null);
    }

    @MediumTest
    public void testTonesRecognitionActivityWasLaunchedWithIntent() {
        final Button buttonLaunchTonesRecognition = (Button) getActivity().findViewById(R.id.btn_tones);
        buttonLaunchTonesRecognition.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
    }

    @MediumTest
    public void testEqualizationRecognitionActivityWasLaunchedWithIntent() {
        final Button buttonLaunchEqRecognition = (Button) getActivity().findViewById(R.id.btn_eq);
        buttonLaunchEqRecognition.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
    }

    @MediumTest
    public void testViewResultsActivityWasLaunchedWithIntent() {
        final Button buttonLaunchViewResultsActivity = (Button) getActivity().findViewById(R.id.btn_results);
        buttonLaunchViewResultsActivity.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
    }
}
