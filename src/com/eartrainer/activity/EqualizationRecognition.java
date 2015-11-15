package com.eartrainer.activity;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.eartrainer.R;
import com.eartrainer.audio.unit.filter.PeakFilter;
import com.eartrainer.audio.unit.source.AudioRawFileSource;
import com.eartrainer.audio.unit.source.AudioSource;
import com.eartrainer.core.EQAnswer;
import com.eartrainer.core.EQRecognitionSettings;
import com.eartrainer.core.RecognitionSettings;
import com.eartrainer.core.Question;
import com.eartrainer.utils.ArrayRandomizer;
import com.eartrainer.view.EQAnswerPanel;
import com.eartrainer.view.RecognitionView;

import java.util.HashMap;


public class EqualizationRecognition extends BaseRecognitionActivity {

    private Spinner spinnerTracks;
    private HashMap<String, Integer> trackNameToId = new HashMap<>();
    private EQRecognitionSettings eqSettings;
    private ArrayRandomizer<Integer> gainRandomizer;
    private ArrayRandomizer<Integer> frequencyRandomizer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int playLength = 12 * 1000; // tracks are 12s long
        Integer[] gains = new Integer[]{-12, -6, 0, 6, +12};
        Integer[] frequencies = new Integer[]{63, 125, 250, 500, 1000, 2000, 4000, 8000, 16000};
        eqSettings = new EQRecognitionSettings(playLength, gains, frequencies);
        gainRandomizer = new ArrayRandomizer<>(gains);
        frequencyRandomizer = new ArrayRandomizer<>(frequencies);

        initializeControls();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.equalization;
    }

    @Override
    protected RecognitionSettings getRecognitionSettings() {
        return eqSettings;
    }

    @Override
    protected Question createRandomQuestion() {
        int frequency = frequencyRandomizer.randomize();
        int gain = gainRandomizer.randomize();
        audioPlayer.setAudioFilter(new PeakFilter(audioPlayer.getSampleRate(), frequency, gain));
        AudioSource audioSource = new AudioRawFileSource(getApplicationContext(), trackNameToId.get(spinnerTracks.getSelectedItem().toString()));
        return new Question(audioSource, new EQAnswer(frequency, gain));
    }

    private void initializeControls() {
        spinnerSetup();

        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.layout_root_equalization);
        EQAnswerPanel tonesAnswerPanel = new EQAnswerPanel(rootLayout.getContext(), eqSettings);
        RecognitionView recognitionView = new RecognitionView(rootLayout.getContext(), "Next random equalization", tonesAnswerPanel);
        rootLayout.addView(recognitionView);
        setRecognitionView(recognitionView);
    }

    private void spinnerSetup() {
        trackNameToId.put("pink noise", R.raw.pinknoise);
        trackNameToId.put("white noise", R.raw.whitenoise);
        trackNameToId.put("downtown", R.raw.downtown);
        trackNameToId.put("orchestral", R.raw.orchestral);
        trackNameToId.put("liquid spirit", R.raw.liquidspirit);

        spinnerTracks = (Spinner) findViewById(R.id.spinner_equalization);
        Object[] songs = trackNameToId.keySet().toArray();
        ArrayAdapter<Object> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, songs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTracks.setAdapter(dataAdapter);
    }
}