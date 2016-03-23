package com.grammar.trocket.grammar.com.grammar.trocket.exercises.text_quiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.QuizDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;

import java.util.ArrayList;


/**
 * Created by firasAltayeb on 15/02/2016.
 */
public class TextQuizQuestionsList {

    Cursor allQuizDetails;
    Cursor currentTextQuizDetails;
    Cursor currentQuestionAnswer;
    ArrayList<String> questions;
    ArrayList<Integer> questionsID;
    int parentId;

    public  TextQuizQuestionsList(int parentId) {
        this.allQuizDetails = QuizDialog.allQuizDetails;
        this.parentId = parentId;
        SQLiteDatabase myDatabase = MainMenu.db.getWritableDatabase();

        questions = new ArrayList<>();
        questionsID = new ArrayList<>();


        currentTextQuizDetails = myDatabase.rawQuery(
                       "SELECT * " +
                        "FROM " + MainMenu.db.QUIZQUESTION_TABLE + " " +
                        "WHERE " + MainMenu.db.QUIZQUESTION_QUIZID + " = " + parentId, null);

        while (currentTextQuizDetails.moveToNext()){
            questions.add(currentTextQuizDetails.getString(currentTextQuizDetails.getColumnIndex(MainMenu.db.QUIZQUESTION_TEXT)));
            questionsID.add(currentTextQuizDetails.getInt(currentTextQuizDetails.getColumnIndex(MainMenu.db.QUIZQUESTION_ID)));
        }
        currentTextQuizDetails.move(-1);

        for(Integer questionId : questionsID){
            currentQuestionAnswer = myDatabase.rawQuery(
                     "SELECT * " +
                     "FROM " + MainMenu.db.QUIZANSWER_TABLE + " " +
                     "WHERE " + MainMenu.db.QUIZANSWER_QUIZQUESTIONID + " = " + questionId, null);
        }


        while (currentQuestionAnswer.moveToNext()) {
            String name = currentQuestionAnswer.getString(currentQuestionAnswer.
                    getColumnIndex(MainMenu.db.QUIZANSWER_TEXT));
            Log.w("222222222222222", name);
        }
        currentQuestionAnswer.move(-1);


    }


    public  TextQuizQuestionsList() {}

    /**
     * Creates an array with size 48 slots and then assigns the slots with questions for the game as strings.
     */
    public String[] createArray(){
        String[] QuestionsList = new String[48];
        QuestionsList[0] = "Which disease devastated livestock across the UK during 2001?";
        QuestionsList[1] = "Which of these kills its victims by constriction?";
        QuestionsList[2] = "Which of these might be used in underwater naval operations?";
        QuestionsList[3] = "A 'cuppa' is an informal term for what?";
        QuestionsList[4] = "What is the meaning of the colloquial expression 'in the bag'?";
        QuestionsList[5] = "Which activity would you most associate with a mole?";
        QuestionsList[6] = "What is the name of the instrument panel in a car?";
        QuestionsList[7] = "What type of protective headgear do motorcyclists wear?";
        QuestionsList[8] = "Which of these refers to an alcoholic drink served with ice?";
        QuestionsList[9] = "Which of these is an obstruction built across a river?";
        QuestionsList[10] = "Which of these is an adolescent romantic attachment?";
        return QuestionsList;
    }
}
