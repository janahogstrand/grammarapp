package com.grammar.trocket.grammingo.resources.alphabetAndDictionary;

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
public class DictionaryItemsAdapter extends RecyclerView.Adapter<DictionaryItemsAdapter.DictionaryViewHolder> {

    private ArrayList<DictionaryItem> wordList;
    private int itemPosition = 0;

    public DictionaryItemsAdapter(ArrayList<DictionaryItem> wordList){
        this.wordList = wordList;
    }

    @Override
    public DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_alphabet, parent, false);

//        ++itemPosition;
//        if (itemPosition%2==0) {
//            view.setBackgroundResource(R.drawable.quizz_rounded_button_primary);
//        } else {
//            view.setBackgroundResource(R.drawable.quizz_rounded_button_secondary);
//        }

        view.setBackgroundResource(R.drawable.quiz_rounded_button_primary);

        DictionaryViewHolder viewHolder = new DictionaryViewHolder(view, wordList);
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
        //Locale language = new Locale("es", "ES");
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
            //DictionaryItemsList.textToSpeech.setLanguage(language);
            DictionaryItemsList.textToSpeech.speak(phrase, TextToSpeech.QUEUE_FLUSH, null);
        }

        /**
         * Stops all current sound
         **/
        private void stopAllSound() {
            if (DictionaryItemsList.textToSpeech != null) {
                DictionaryItemsList.textToSpeech.stop();
                Log.w("TTS stopped", "TTS stop");
            }
        }
    }
}
