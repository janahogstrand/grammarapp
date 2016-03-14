package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Context;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sam on 14/03/2016.
 */
public class VoiceRecording {
    private MediaRecorder audioRecorder;
    private static final String LOG_TAG="AudioRecordTest";
    private static String mFileName;
    private MediaPlayer mPlayer;

    public void OnRecord(boolean start, Context context){
        if(start){
            startRecording(context);
        }
        else{
            stopRecording();
        }

    }

    /*
    This method is used to assign the variables for the audio recorder and player
    path is used to store the file in the cache, so it's not stored permenantly, and is removed if ram is low.
    setAudioSource - Tells the app what device to access to get the audio
    setOutputFormat - Sets the format for file output. THREE GPP allows the inbuilt media player to playback audio
    setAudioEncoder - Sets the encoding method of the audio. AMR_NB is smaller than the others
    setOutputFile - Sets the path for the file (Which is the cache)
    Catch any expecptions for debugging.
     */
    private void startRecording(Context context)
    {
            File externalRoot = context.getCacheDir();
            File tempDir = new File(externalRoot, ".audioTemp");
            mFileName = tempDir.getAbsolutePath();

        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setOutputFile(mFileName);
        audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            audioRecorder.prepare();
            audioRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        .stop() stops the recording
        .release() release the mic from the app (without this, the app will take control of the mic constantly and causes errors within this app and others)
        . = null; Clears the audio recorder
     */
    private void stopRecording() {
        System.out.println("Attempting to stop recording");
        try {
            audioRecorder.stop();
            audioRecorder.release();
            audioRecorder = null;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
