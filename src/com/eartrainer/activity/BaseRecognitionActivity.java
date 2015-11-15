package com.eartrainer.activity;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import com.eartrainer.core.*;
import com.eartrainer.audio.AudioPlayer;
import com.eartrainer.controller.RecognitionViewController;
import com.eartrainer.session.Session;
import com.eartrainer.view.RecognitionView;

public abstract class BaseRecognitionActivity extends Activity {

    protected AudioPlayer audioPlayer;
    private RecognitionView recognitionView;
    private RecognitionViewController viewController;
    private AudioQuestion currentQuestion;
    private CountDownTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        audioPlayer = new AudioPlayer(null, 44100);
    }

    @Override
    public void onStop() {
        super.onStop();
        audioPlayer.stop();
    }

    protected abstract int getLayoutResourceId();

    protected abstract RecognitionSettings getRecognitionSettings();

    protected abstract AudioQuestion createRandomQuestion();

    protected void setRecognitionView(RecognitionView recognitionView) {
        this.recognitionView = recognitionView;
        recognitionView.getButtonNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClicked();
            }
        });
        recognitionView.getButtonAnswer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerClicked();
            }
        });

        viewController = new RecognitionViewController(recognitionView);
        viewController.update(RecognitionState.IDLE);
    }

    private void onNextClicked() {
        enterState(RecognitionState.PLAYING);
    }

    private void playRandomQuestion() {
        long playLengthMs = getRecognitionSettings().getPlayLength();
        long intervalMs = 1000;
        recognitionView.getProgressBar().setMax((int)(playLengthMs / intervalMs));
        currentQuestion = createRandomQuestion();
        audioPlayer.setAudioSource(currentQuestion.getAudioSource());
        timer = new CountDownTimer(playLengthMs, intervalMs - 100) {
            private int tick = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                recognitionView.getProgressBar().setProgress(tick++);
            }

            @Override
            public void onFinish() {
                enterState(RecognitionState.WAITING);
            }
        }.start();
        audioPlayer.start();
    }

    private void onAnswerClicked() {
        enterState(RecognitionState.ANSWERED);
    }

    private void enterState(RecognitionState nextState) {
        switch (nextState) {
            case PLAYING:
                playRandomQuestion();
                break;
            case WAITING:
                stopPlaying();
                break;
            case ANSWERED:
                stopPlaying();
                evaluateAnswer();
                break;
        }
        viewController.update(nextState);
    }

    private void stopPlaying() {
        audioPlayer.stop();
        timer.cancel();
    }

    private void evaluateAnswer() {
        QAType answer = recognitionView.getAnswerPanel().getSelectedAnswer();
        QAType question = currentQuestion.getQuestion();
        Session.getInstance().getQAHistory().add(new QAPair(question, answer));

        TextView textViewCorrect = recognitionView.getTextViewCorrect();
        if (answer == null || !answer.equals(question)) {
            textViewCorrect.setTextColor(Color.RED);
            textViewCorrect.setText("Incorrect :( " + currentQuestion.getQuestion().asString());
        } else {
            textViewCorrect.setTextColor(Color.GREEN);
            textViewCorrect.setText("Correct :)");
        }
    }
}
