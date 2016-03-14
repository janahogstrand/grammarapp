package com.example.teerachel.imagequizactivity;

/**
 * Created by teerachel on 07/03/2016.
 */
public class Image_Quiz_Question {

    /**
     * Creates an array with size 10 slots and then assigns the slots with questions for the game as strings.
     */
    public String[] createArray() {
        String[] QuestionsList = new String[10];
        QuestionsList[0] = "1. Tren";
        QuestionsList[1] = "2. Avion";
        QuestionsList[2] = "3. Coche";
        QuestionsList[3] = "4. Autobus";
        QuestionsList[4] = "5. Barco";
        QuestionsList[5] = "6. Bicicleta";
        return QuestionsList;
    }
}
