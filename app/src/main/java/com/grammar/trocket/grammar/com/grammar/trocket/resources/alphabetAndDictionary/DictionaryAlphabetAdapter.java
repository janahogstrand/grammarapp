package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class DictionaryAlphabetAdapter extends RecyclerView.Adapter<DictionaryAlphabetAdapter.AlphabetViewHolder> {

    private ArrayList<AlphabetItem> alphabetList;
    private int itemPositionAlphabet = 0;
    private int itemPositionDictionary = 0;
    private String callerName;
    Intent intent;

    public DictionaryAlphabetAdapter(ArrayList<AlphabetItem> alphabetList, String callerName) {
        this.alphabetList = alphabetList;
        this.callerName = callerName;
    }

    public class AlphabetViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        Locale language = new Locale("es", "ES");
        ArrayList<AlphabetItem> alphabetList;

        /**
         * Plays auudio if alphabet
         * otherwise goes to new DictionaryItemsList activity
         * @see DictionaryItemsList
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

                        intent = new Intent(v.getContext(), DictionaryItemsList.class);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_alphabet, parent, false);

//        if(callerName.equals("Alphabet")) {
//            ++itemPositionAlphabet;
//            if (itemPositionAlphabet % 2 == 0) {
//                view.setBackgroundResource(R.drawable.rounded_button_primary);
//            } else {
//                view.setBackgroundResource(R.drawable.rounded_button_secondary);
//            }
//        }
//        else if(callerName.equals("DictionaryItemsList")) {
//            ++itemPositionDictionary;
//            if (itemPositionDictionary % 2 == 0) {
//                view.setBackgroundResource(R.drawable.rounded_button_primary);
//            } else {
//                view.setBackgroundResource(R.drawable.rounded_button_secondary);
//            }
//        }

        if(callerName.equals("Alphabet")) {
                view.setBackgroundResource(R.drawable.rounded_button_primary);
        }
        else if(callerName.equals("DictionaryItemsList")) {
                view.setBackgroundResource(R.drawable.rounded_button_secondary);

        }

            AlphabetViewHolder viewHolder = new AlphabetViewHolder(view, alphabetList);
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
