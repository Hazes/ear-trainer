package com.eartrainer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import android.widget.ProgressBar;
import com.eartrainer.ArrayRandomizer;
import com.eartrainer.R;
import com.eartrainer.TonesRecognitionConsts;
import com.eartrainer.TonesRecognitionSettings;
import com.eartrainer.audio.AudioPlayer;
import com.eartrainer.audio.unit.source.osc.Osc;
import com.eartrainer.audio.unit.source.osc.SineOsc;


enum ToneType {
    SINE, TRIANGLE, SQUARE, SAW
}

public class TonesRecognition
        extends Activity
        implements View.OnClickListener {

    private Button btnNext;
    private Button btnAnswer;
    private ProgressBar progressBarCountdown;

    private TonesRecognitionSettings tonesSettings;
    private ArrayRandomizer<ToneType> toneTypeRandomizer;
    private ArrayRandomizer<Integer> frequencyRandomizer;
    private AudioPlayer audioPlayer;
    private Thread audioPlayerThread;
    private CountDownTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tones);

        audioPlayer = new AudioPlayer(null, 44100);
        tonesSettings = new TonesRecognitionSettings(4000);
        toneTypeRandomizer = new ArrayRandomizer<>(ToneType.values());
        frequencyRandomizer = new ArrayRandomizer<>(TonesRecognitionConsts.frequencies);
        InitializeControls();
    }

    private void InitializeControls() {
        btnNext = (Button)findViewById(R.id.btn_next_TonesRecognition);
        btnNext.setOnClickListener(this);
        btnAnswer = (Button)findViewById(R.id.btn_answer_TonesRecognition);
        btnAnswer.setOnClickListener(this);
        btnAnswer.setEnabled(false);

        progressBarCountdown = (ProgressBar)findViewById(R.id.progressbar_TonesRecognition);
        progressBarCountdown.setMax((int)tonesSettings.getTonePlayLength() / 1000);
    }

    private Osc createRandomOsc() {
        int frequency = frequencyRandomizer.randomize();
        ToneType type = toneTypeRandomizer.randomize();
        switch (type) {
            default: // only sine osc supported for now
                return new SineOsc(frequency);
        }
    }

    private void onBtnNextClicked() {
        btnNext.setEnabled(false);
        btnAnswer.setEnabled(true);

        playRandomTone();
    }

    private void playRandomTone() {
        long playLengthMs = tonesSettings.getTonePlayLength();
        long intervalMs = 1000;

        audioPlayer.setAudioSource(createRandomOsc());
        audioPlayerThread = new Thread(audioPlayer, "AudioPlayer Thread");
        audioPlayerThread.start();
        timer = new CountDownTimer(playLengthMs, intervalMs - 100) {
            private int tick = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                progressBarCountdown.setProgress(tick++);
            }

            @Override
            public void onFinish() {
                onPlayFinished();
            }
        }.start();
    }

    private void onPlayFinished() {
        audioPlayer.stop();
        progressBarCountdown.setProgress(0);
    }

    private void onBtnAnswerClicked() {
        btnNext.setEnabled(true);
        btnAnswer.setEnabled(false);

        if (audioPlayerThread.isAlive()) {
            timer.cancel();
            onPlayFinished();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next_TonesRecognition:
                onBtnNextClicked();
                break;
            case R.id.btn_answer_TonesRecognition:
                onBtnAnswerClicked();
                break;
        }
    }
}