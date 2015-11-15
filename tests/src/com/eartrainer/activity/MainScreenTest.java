package com.eartrainer.activity;

import android.test.ActivityUnitTestCase;

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

    public MainScreenTest() {
        super(MainScreen.class);
    }

}
