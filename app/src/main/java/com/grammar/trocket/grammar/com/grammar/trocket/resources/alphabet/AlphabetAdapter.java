package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabet;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.AlphabetDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.AlphabetViewHolder> {

    private ArrayList<AlphabetItem> alphabetList;
    Intent intent;

    public AlphabetAdapter(ArrayList<AlphabetItem> alphabetList) {
        this.alphabetList = alphabetList;
    }

    public class AlphabetViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        Locale language = new Locale("es", "ES");
        ArrayList<AlphabetItem> alphabetList;

        /**
         * Plays auudio if alphabet
         * otherwise goes to new Dictionary activity
         * @see Dictionary
         **/
        public AlphabetViewHolder(final View itemView, final ArrayList<AlphabetItem> alphabetList) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            this.alphabetList = alphabetList;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Check if it is a dictionary item
                    String letter = alphabetList.get(getAdapterPosition()).getLetter();

                    if(alphabetList.get(getAdapterPosition()).getIsDictionary()){

                        intent = new Intent(v.getContext(), Dictionary.class);
                        intent.putExtra(DialectDialog.DIALECT_INFO, language);
                        intent.putExtra(Alphabet.LETTER, letter.toUpperCase());
                        v.getContext().startActivity(intent);

                    }else {
                        playAudio();
                    }
                }
            });


        }

        /**
         * Plays from audio file if not found
         * then plays textToSpeech
         **/
        private void playAudio() {
            stopAllSound();
            String phrase = alphabetList.get(getAdapterPosition()).getLetter();
            Alphabet.textToSpeech.setLanguage(language);
            Alphabet.textToSpeech.speak(phrase, TextToSpeech.QUEUE_FLUSH, null);
        }

        /**
         * Stops all current sound
         **/
        private void stopAllSound() {
            if (Alphabet.textToSpeech != null) {
                Alphabet.textToSpeech.stop();
                Log.w("TTS stopped", "TTS stop");
            }
        }
    }

    /**
     * Alphabet view inflated with card_alphabet
     **/
    @Override
    public AlphabetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_alphabet, parent, false);
        AlphabetViewHolder viewHolder = new AlphabetViewHolder(v, alphabetList);
        return viewHolder;
    }

    /**
     * Binds Alphabet
     **/
    @Override
    public void onBindViewHolder(AlphabetViewHolder holder, int position) {
        TextView title = holder.title;
        title.setText(alphabetList.get(position).getLetter());
    }


    @Override
    public int getItemCount() {
        return alphabetList.size();
    }
}