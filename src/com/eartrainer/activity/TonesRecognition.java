package com.eartrainer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.eartrainer.R;
import com.eartrainer.audio.AudioPlayer;


public class TonesRecognition extends Activity
implements View.OnClickListener {

    private AudioPlayer audioPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tones);

        audioPlayer = new AudioPlayer(null, 44100);
    }

    @Override
    public void onClick(View v) {
    }
}