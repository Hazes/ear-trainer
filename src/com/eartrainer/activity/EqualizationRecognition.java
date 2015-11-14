package com.eartrainer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.eartrainer.R;
import com.eartrainer.audio.AudioPlayer;
import com.eartrainer.audio.unit.source.AudioFileSource;
import java.util.HashMap;
import java.util.Map;


public class EqualizationRecognition
        extends Activity
        implements View.OnClickListener {

    private Button btnNext;
    private Button btnAnswer;
    private Spinner spinner;
    private AudioPlayer audioPlayer;
    private Thread audioPlayerThread;
    private Map songNameToId = new HashMap<String, Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equalization);
        audioPlayer = new AudioPlayer(null, 44100);
        InitializeControls();
        SpinnerSetup();
    }

    private void InitializeControls() {

        btnNext = (Button)findViewById(R.id.btn_next_EqualizationRecognition);
        btnNext.setOnClickListener(this);
        btnAnswer = (Button)findViewById(R.id.btn_answer_EqualizationRecognition);
        btnAnswer.setOnClickListener(this);
        btnAnswer.setEnabled(false);

    }

    private void SpinnerSetup() {

        songNameToId.put("liquid spirit", R.raw.pinknoise);
        songNameToId.put("liquid spirit", R.raw.whitenoise);
        songNameToId.put("downtown", R.raw.downtown);
        songNameToId.put("orchestral", R.raw.orchestral);
        songNameToId.put("smooth", R.raw.song1);
        songNameToId.put("liquid spirit", R.raw.liquidspirit);

        spinner = (Spinner) findViewById(R.id.spinner);
        Object[] songs;
        songs = songNameToId.keySet().toArray();

        ArrayAdapter<Object> dataAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, songs);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

    private void onBtnNextClicked() {
        btnNext.setEnabled(true);
        btnAnswer.setEnabled(false);

        playSong();
    }

    private void playSong() {
        AudioFileSource in = new AudioFileSource(getApplicationContext(), (int) songNameToId.get(spinner.getSelectedItem()));
        audioPlayer = new AudioPlayer(in, 44100);
        audioPlayerThread = new Thread(audioPlayer, "AudioPlayer Thread");
        audioPlayerThread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next_EqualizationRecognition:
                onBtnNextClicked();
                break;
            case R.id.btn_answer_EqualizationRecognition:
                onBtnAnswerClicked();
                break;
        }
    }

    private void onBtnAnswerClicked() {
        btnNext.setEnabled(true);
        btnAnswer.setEnabled(false);
    }
}