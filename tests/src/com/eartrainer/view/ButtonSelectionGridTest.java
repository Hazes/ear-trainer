package com.eartrainer.view;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import java.util.Random;


public class ButtonSelectionGridTest extends AndroidTestCase {

    @Override
    protected void setUp() {
    }

    @SmallTest
    public void testSelection() {
        // generate random string contents
        int contentsSize = 100;
        String[] testContents = new String[contentsSize];
        Random rand = new Random();
        for (int i = 0; i < contentsSize; ++i) {
            testContents[i] = String.valueOf(rand.nextInt());
        }
        ButtonSelectionGrid grid = new ButtonSelectionGrid(getContext(), testContents);
        // simulate random button click
        int index = rand.nextInt(contentsSize);
        grid.getButtonAt(index).performClick();
        assertEquals(grid.getSelectedItem(), testContents[index]);
    }
}
