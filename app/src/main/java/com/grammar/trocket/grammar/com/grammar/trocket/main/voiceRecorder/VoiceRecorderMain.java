package com.grammar.trocket.grammar.com.grammar.trocket.main.voiceRecorder;

import android.content.Context;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.grammar.trocket.grammar.R;

import java.io.File;

/**
 * Created by Sam on 04/03/2016.
 */
public class VoiceRecorderMain extends AppCompatActivity {

    private static final String LOG_TAG="AudioRecordTest";
    private static String mFileName;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;

    private PlayButton mPlayButton;
    private RecordButton mRecordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recorder);
        LinearLayout layout = new LinearLayout(this);
        mRecordButton = new RecordButton(this);
        layout.addView(mRecordButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));

        mPlayButton = new PlayButton(this);
        layout.addView(mPlayButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));

        setContentView(layout);



    }
    //Method used for pausing the media.
    @Override
    protected void onPause(){
        super.onPause();
        if(mRecorder != null)
        {
            mRecorder.release();
            mRecorder = null;
        }
    }

    //Base class for button that starts and stops the recording
    class RecordButton extends Button{
        public RecordButton(Context context) {
            super(context);
            setText("Start Recording");
            setOnClickListener(click);
        }

        boolean mStartRecording = true;
        OnClickListener click = new OnClickListener() {
            @Override
            public void onClick(View v) {
                OnRecord(mStartRecording);
                if(mStartRecording){
                    setText("Stop Recording");
                }
                else{
                    setText("Start Recording");
                }

                mStartRecording = !mStartRecording;

            }
        };


    }

    private void OnRecord(boolean start){
        if(start){
            startRecording();
        }
        else{
            stopRecording();
        }
    }

    private void stopRecording() {
        try {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void startRecording() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File externalRoot = Environment.getExternalStorageDirectory();
            File tempDir = new File(externalRoot, ".audioTemp");
            mFileName = tempDir.getAbsolutePath();
        }
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try{
            mRecorder.prepare();
            mRecorder.start();
        } catch (Exception e)
        {
            Log.e(LOG_TAG, "PREPARE() FAILED");
            e.printStackTrace();
        }
    }

    class PlayButton extends Button{

        public PlayButton(Context context) {
            super(context);
            setText("Start Playing");
            setOnClickListener(click);
        }

        OnClickListener click = new OnClickListener() {

            private boolean mStartPlaying;

            @Override
            public void onClick(View v) {
                mStartPlaying = !mStartPlaying;
                onPlay(mStartPlaying);
                if(mStartPlaying){
                    setText("Stop Playing");
                }
                else{
                    setText("Start Playing");
                }
            }
        };


    }
    private void onPlay(boolean start) {

        if(start){
            startPlaying();
        }
        else
        {
            stopPlaying();
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try{
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();

        }catch (Exception e)
        {
            Log.e(LOG_TAG, "Prepare() Failed");
        }
    }
}
