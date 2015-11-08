package com.eartrainer.audio.sources;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class AudioFileSource implements AudioSource {

    private InputStream inStream;
    private BufferedInputStream bufferedInStream;
    private byte[] buffer;
    private ByteBuffer byteBuffer;

    public AudioFileSource(Context context, int resourceId) {
        inStream = context.getResources().openRawResource(resourceId);
    }

    @Override
    public void prepare(int sampleRate, int numSamples) {
        int bufferSizeBytes = numSamples * 4;
        buffer = new byte[bufferSizeBytes];
        bufferedInStream = new BufferedInputStream(inStream, bufferSizeBytes);
        byteBuffer = ByteBuffer.wrap(buffer);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public void release() {
        try {
            bufferedInStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public float[] generate(int numSamples) {
        float[] samples = new float[numSamples];
        try {
            int bytesRead;
            byteBuffer.rewind();
            if ((bytesRead = bufferedInStream.read(buffer)) > -1) {
                int samplesRead = bytesRead * 4;
                for (int i = 0; i < samplesRead; ++i) {
                    samples[i] = byteBuffer.getFloat();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return samples;
    }
}
