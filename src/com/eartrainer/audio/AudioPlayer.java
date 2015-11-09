package com.eartrainer.audio;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import com.eartrainer.audio.unit.source.AudioSource;

public class AudioPlayer
        implements Runnable {

    private boolean stop;
    private AudioTrack audioTrack;
    private AudioSource audioSource;
    private int numSamples;
    private int sampleRate;

    public AudioPlayer(AudioSource audioSource, int sampleRate) {
        this.audioSource = audioSource;
        this.sampleRate = sampleRate;
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

    @Override
    public void run() {
        stop = false;
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        if (audioSource != null) {
            audioSource.prepare(sampleRate, numSamples);
            audioTrack.play();
            while (!stop) {
                float[] samples = audioSource.generate(numSamples);
                audioTrack.write(samples, 0, samples.length, AudioTrack.WRITE_BLOCKING);
            }
            audioTrack.stop();
        }
    }

    public void stop() {
        stop = true;
    }
}
