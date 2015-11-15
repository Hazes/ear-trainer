package com.eartrainer.controller;


import android.view.View;
import com.eartrainer.core.RecognitionState;
import com.eartrainer.view.RecognitionView;

public class RecognitionViewController {

    private RecognitionView recognitionView;

    public RecognitionViewController(RecognitionView recognitionView) {
        this.recognitionView = recognitionView;
    }

    public void update(RecognitionState state) {
        switch (state) {
            default:
            case IDLE:
                onIdle();
                break;
            case PLAYING:
                onPlaying();
                break;
            case WAITING:
                onWaiting();
                break;
            case ANSWERED:
                onAnswered();
                break;
        }
    }

    private void onIdle() {
        recognitionView.getButtonNext().setEnabled(true);
        recognitionView.getProgressBar().setProgress(0);
        recognitionView.getAnswerPanel().setEnabled(false);
        recognitionView.getButtonAnswer().setEnabled(false);
        recognitionView.getTextViewCorrect().setVisibility(View.INVISIBLE);
    }

    private void onPlaying() {
        recognitionView.getButtonNext().setEnabled(false);
        recognitionView.getAnswerPanel().setEnabled(true);
        recognitionView.getButtonAnswer().setEnabled(true);
        recognitionView.getTextViewCorrect().setVisibility(View.INVISIBLE);
    }

    private void onWaiting() {
        // not implemented
    }

    private void onAnswered() {
        recognitionView.getButtonNext().setEnabled(true);
        recognitionView.getProgressBar().setProgress(0);
        recognitionView.getAnswerPanel().setEnabled(false);
        recognitionView.getButtonAnswer().setEnabled(false);
        recognitionView.getTextViewCorrect().setVisibility(View.VISIBLE);
    }
}
