package com.grammar.trocket.grammar.com.grammar.trocket.resources.audio_quiz;

/**
 * Created by Sam on 06/03/2016.
 */
public class AudioQuizQuestionsList {

    /**
     * creates the words for the audio player
     * @return
     */

    public String[] createArray() {
        String[] QuestionsList = new String[48];
        QuestionsList[0] = "el gato"; //cat
        QuestionsList[1] = "el perro"; //dog
        QuestionsList[2] = "el pez"; //fish
        QuestionsList[3] = "la cobaya"; //guinea pig
        QuestionsList[4] = "el hipopótamo"; //hippo
        QuestionsList[5] =  "el caballo"; //horse
        QuestionsList[6] = "el ratón"; //mouse
        QuestionsList[7] = "el cerdo"; //pig
        QuestionsList[8] = "el tiburón"; //shark
        QuestionsList[9] = "el tigre"; //tiger
        QuestionsList[10] = "la tortuga"; //turtle
        return QuestionsList;
    }

}
