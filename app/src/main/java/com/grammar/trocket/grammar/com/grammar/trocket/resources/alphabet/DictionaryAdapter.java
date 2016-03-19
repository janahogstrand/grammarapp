package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabet;

import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 19/03/16.
 */
public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {

    private ArrayList<DictionaryItem> wordList;

    public DictionaryAdapter(ArrayList<DictionaryItem> wordList){
        this.wordList = wordList;
    }

    @Override
    public DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_alphabet, parent, false);
        DictionaryViewHolder viewHolder = new DictionaryViewHolder(v, wordList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DictionaryViewHolder holder, int position) {
        TextView title = holder.title;
        TextView title2 = holder.title2;
        title.setText(wordList.get(position).getForeignWord());
        title2.setText(wordList.get(position).getEnglishWord());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class DictionaryViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView title2;
        Locale language = new Locale("es", "ES");
        public DictionaryViewHolder(View itemView, ArrayList<DictionaryItem> wordList) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            title2 = (TextView) itemView.findViewById(R.id.title2);
            title2.setVisibility(View.VISIBLE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playAudio();
                }
            });
        }

        /**
         * Plays from audio file if not found
         * then plays textToSpeech
         **/
        private void playAudio() {
            stopAllSound();
            String phrase = wordList.get(getAdapterPosition()).getForeignWord();
            Dictionary.textToSpeech.setLanguage(language);
            Dictionary.textToSpeech.speak(phrase, TextToSpeech.QUEUE_FLUSH, null);
        }

        /**
         * Stops all current sound
         **/
        private void stopAllSound() {
            if (Dictionary.textToSpeech != null) {
                Dictionary.textToSpeech.stop();
                Log.w("TTS stopped", "TTS stop");
            }
        }
    }
}
