package com.eartrainer.activity;

import android.os.Bundle;

import android.widget.LinearLayout;
import com.eartrainer.core.RecognitionSettings;
import com.eartrainer.core.Question;
import com.eartrainer.core.TonesAnswer;
import com.eartrainer.utils.ArrayRandomizer;
import com.eartrainer.R;
import com.eartrainer.view.RecognitionView;
import com.eartrainer.view.TonesAnswerPanel;
import com.eartrainer.core.TonesRecognitionSettings;
import com.eartrainer.audio.unit.source.osc.*;


public class TonesRecognition extends BaseRecognitionActivity {

    private TonesRecognitionSettings tonesSettings;
    private ArrayRandomizer<ToneType> toneTypeRandomizer;
    private ArrayRandomizer<Integer> frequencyRandomizer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Integer[] frequencies = new Integer[]{63, 125, 250, 500, 1000, 2000, 4000};
        tonesSettings = new TonesRecognitionSettings(4000, ToneType.values(), frequencies);
        toneTypeRandomizer = new ArrayRandomizer<>(ToneType.values());
        frequencyRandomizer = new ArrayRandomizer<>(tonesSettings.getFrequencies());
        initializeControls();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.tones;
    }

    @Override
    protected RecognitionSettings getRecognitionSettings() {
        return tonesSettings;
    }

    @Override
    protected Question createRandomQuestion() {
        int frequency = frequencyRandomizer.randomize();
        ToneType type = toneTypeRandomizer.randomize();
        return new Question(createOsc(type, frequency), new TonesAnswer(type, frequency));
    }

    private void initializeControls() {
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.layout_root_tones);
        TonesAnswerPanel tonesAnswerPanel = new TonesAnswerPanel(rootLayout.getContext(), tonesSettings);
        RecognitionView recognitionView = new RecognitionView(rootLayout.getContext(), "Next random tone", tonesAnswerPanel);
        rootLayout.addView(recognitionView);
        setRecognitionView(recognitionView);
    }

    private Osc createOsc(ToneType type, Integer frequency) {
        switch (type) {
            case SINE:
                return new SineOsc(frequency);
            case TRIANGLE:
                return new TriangleOsc(frequency);
            case SQUARE:
                return new SquareOsc(frequency);
            case SAW:
                return new SawOsc(frequency);
            default:
                throw new AssertionError("Unknown osc type");
        }
    }
}