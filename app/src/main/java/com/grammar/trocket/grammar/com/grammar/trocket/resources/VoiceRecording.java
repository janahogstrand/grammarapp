package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Context;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sam on 14/03/2016.
 */
public class VoiceRecording {
    MediaRecorder audioRecorder = new MediaRecorder();


    /*
    This method is used to assign the variables for the audio recorder and player
    path is used to store the file in the cache, so it's not stored permenantly, and is removed if ram is low.
    setAudioSource - Tells the app what device to access to get the audio
    setOutputFormat - Sets the format for file output. THREE GPP allows the inbuilt media player to playback audio
    setAudioEncoder - Sets the encoding method of the audio. AMR_NB is smaller than the others
    setOutputFile - Sets the path for the file (Which is the cache)
    Catch any expecptions for debugging.
     */
    public void assignVariables(Context context)
    {

        try
        {
            File cache = context.getCacheDir();
            File tempDir = new File(cache, ".audioTemp");
            String path = tempDir.getAbsolutePath();




            audioRecorder.reset();
            audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            audioRecorder.setOutputFile(path);
            audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);


        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void recordAudio()
    {
        try {
            audioRecorder.prepare();
            audioRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
    }
}
