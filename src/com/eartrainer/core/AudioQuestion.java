package com.eartrainer.core;


import com.eartrainer.audio.unit.source.AudioSource;

public class AudioQuestion {

    private AudioSource audioSource;
    private QAType question;

    public AudioQuestion(AudioSource audioSource, QAType question) {
        this.audioSource = audioSource;
        this.question = question;
    }

    public AudioSource getAudioSource() {
        return audioSource;
    }

    public QAType getQuestion() {
        return question;
    }
}
