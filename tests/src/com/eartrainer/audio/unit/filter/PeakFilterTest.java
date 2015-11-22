package com.eartrainer.audio.unit.filter;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import com.eartrainer.audio.unit.filter.PeakFilter;

import java.util.Random;

public class PeakFilterTest extends AndroidTestCase {

    @Override
    protected void setUp() {
    }

    @SmallTest
    public void filterTestZeroGain() {

        float gain = 0;
        float[] buffer = new float[100];
        float[] buffercopy = new float[100];
        float err = 0;
        Random rand = new Random();
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = rand.nextFloat();
        }
        System.arraycopy(buffer ,0,buffercopy,0,buffer.length );
        PeakFilter peak  = new PeakFilter(44100, 100, gain);
        peak.process(buffer);

        for (int i = 0; i < buffer.length; i++) {
            err += Math.abs(buffer[i] - buffercopy[i]);
        }
        assertTrue(err<0.0000001);
    }
}



