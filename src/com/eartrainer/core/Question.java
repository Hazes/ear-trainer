package com.eartrainer.core;


import com.eartrainer.audio.unit.source.AudioSource;

public class Question {

    private AudioSource audioSource;
    private Answer answer;

    public Question(AudioSource audioSource, Answer answer) {
        this.audioSource = audioSource;
        this.answer = answer;
    }

    public AudioSource getAudioSource() {
        return audioSource;
    }

    public Answer getAnswer() {
        return answer;
    }
}
