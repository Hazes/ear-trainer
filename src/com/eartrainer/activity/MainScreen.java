package com.eartrainer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.eartrainer.R;
import com.eartrainer.audio.AudioPlayer;


public class MainScreen
        extends Activity
        implements View.OnClickListener {

    private Button btnTones;
    private Button btnEq;
    private Button btnResults;
    private AudioPlayer audioPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        InitializeApp();
        audioPlayer = new AudioPlayer(null, 44100);
    }

    private void InitializeApp() {
        btnTones = (Button) findViewById(R.id.btn_tones);
        btnEq = (Button) findViewById(R.id.btn_eq);
        btnResults = (Button) findViewById(R.id.btn_results);
        btnResults.setOnClickListener(this);
        btnTones.setOnClickListener(this);
        btnEq.setOnClickListener(this);
    }

    private void onBtnTonesClicked() {

    }

    private void onBtnEqualizerClicked() {

    }

    private void onBtnResultsClicked() {
        Intent myIntent = new Intent(MainScreen.this, ResultsScreen.class);
        MainScreen.this.startActivity(myIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tones:
                onBtnTonesClicked();
                break;
            case R.id.btn_results:
                onBtnResultsClicked();
                break;
            case R.id.btn_eq:
                onBtnEqualizerClicked();
                break;
        }
    }
}
        
