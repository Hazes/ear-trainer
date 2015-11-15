package com.eartrainer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.eartrainer.R;


public class MainScreen
        extends Activity
        implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        InitializeControls();
    }

    private void InitializeControls() {
        Button btnTones = (Button) findViewById(R.id.btn_tones);
        Button btnEq = (Button) findViewById(R.id.btn_eq);
        Button btnResults = (Button) findViewById(R.id.btn_results);
        btnResults.setOnClickListener(this);
        btnTones.setOnClickListener(this);
        btnEq.setOnClickListener(this);
    }

    private void onBtnTonesClicked() {
        Intent myIntent = new Intent(MainScreen.this, TonesRecognition.class);
        startActivity(myIntent);
    }

    private void onBtnEqualizationClicked() {
        Intent myIntent = new Intent(MainScreen.this, EqualizationRecognition.class);
        startActivity(myIntent);
    }

    private void onBtnResultsClicked() {
        Intent myIntent = new Intent(MainScreen.this, ViewResults.class);
        startActivity(myIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tones:
                onBtnTonesClicked();
                break;
            case R.id.btn_eq:
                onBtnEqualizationClicked();
                break;
            case R.id.btn_results:
                onBtnResultsClicked();
                break;
        }
    }
}
