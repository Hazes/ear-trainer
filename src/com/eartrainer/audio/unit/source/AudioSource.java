package com.eartrainer.audio.unit.source;


import com.eartrainer.audio.unit.AudioUnit;

/**
 * Represents an audio source.
 */
public interface AudioSource extends AudioUnit{
    /**
     * Generate audio samples into given buffer.
     * @param audioBuffer Buffer to generate audio samples to. Implementation
     *                    should add, not overwrite, since there may be more than one
     *                    audio source in the chain.
     */
    void generate(float[] audioBuffer);
}
