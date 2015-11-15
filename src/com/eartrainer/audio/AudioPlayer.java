package com.eartrainer.audio;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import java.util.Arrays;

import com.eartrainer.audio.unit.filter.AudioFilter;
import com.eartrainer.audio.unit.source.AudioSource;

public class AudioPlayer {

    private boolean stopFlag;
    private AudioTrack audioTrack;
    private AudioSource audioSource;
    private AudioFilter audioFilter;
    private int numSamples;
    private int sampleRate;

    public AudioPlayer(AudioSource audioSource, int sampleRate) {
        this.audioSource = audioSource;
        this.setSampleRate(sampleRate);
        AudioFormat format = new AudioFormat.Builder()
                .setSampleRate(sampleRate)
                .setEncoding(AudioFormat.ENCODING_PCM_FLOAT)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .build();

        int bufferSizeBytes = AudioTrack.getMinBufferSize(format.getSampleRate(), format.getChannelMask(), format.getEncoding());
        audioTrack = new AudioTrack(
                AudioManager.STREAM_MUSIC,
                format.getSampleRate(),
                format.getChannelMask(),
                format.getEncoding(),
                bufferSizeBytes,
                AudioTrack.MODE_STREAM);
        numSamples = bufferSizeBytes / 4;
    }

    public void setAudioSource(AudioSource audioSource) {
        this.audioSource = audioSource;
    }

    public void setAudioFilter(AudioFilter audioFilter) {
        this.audioFilter = audioFilter;
    }

    public void start() {
        Runnable audioPlayerRunnable = new Runnable() {
            @Override
            public void run() {
                stopFlag = false;
                Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

                if (audioSource != null) {
                    float[] buffer = new float[numSamples];
                    audioSource.prepare(getSampleRate(), numSamples);
                    audioTrack.play();
                    while (!stopFlag) {
                        audioSource.generate(buffer);
                        if (audioFilter != null)
                            audioFilter.process(buffer);
                        audioTrack.write(buffer, 0, buffer.length, AudioTrack.WRITE_BLOCKING);
                        Arrays.fill(buffer, 0);
                    }
                    audioTrack.stop();
                    audioSource.release();
                }
            }
        };
        Thread audioThread = new Thread(audioPlayerRunnable, "AudioPlayer Thread");
        audioThread.start();
    }

    public void stop() {
        stopFlag = true;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }
}
