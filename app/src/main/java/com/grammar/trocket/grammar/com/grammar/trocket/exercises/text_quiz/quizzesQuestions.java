package com.grammar.trocket.grammar.com.grammar.trocket.exercises.text_quiz;


import android.app.Activity;
import android.util.Log;

import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.QuizDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Questions;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Quiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by firasAltayeb on 15/02/2016.
 */
public class quizzesQuestions {

    public Quiz selectedQuiz;
    public JSONArray allQuizDetails;
    public int selectedQuizPosition;
    public ArrayList<Quiz> quizList;
    public Activity callingActivity;

    public ArrayList<Questions> allQuestions;
    public ArrayList<String> currentQuestions;


    public quizzesQuestions(Activity callingActivity, int selectedQuizPosition) {
        currentQuestions = new ArrayList<>();
        allQuestions = new ArrayList<>();
        this.quizList = QuizDialog.quizList;
        this.allQuizDetails = QuizDialog.allQuizDetails;
        this.selectedQuizPosition = selectedQuizPosition;
        this.callingActivity = callingActivity;
    }


    public ArrayList<Questions> getQuizQuestions(){
        allQuestions.clear();
        String topLevelIdString = "";
        selectedQuiz = quizList.get(selectedQuizPosition);
        ArrayList<Questions> qusList = new ArrayList<Questions>();
        GetJSON getTopCats = new GetJSON(callingActivity, TableNames.QUIZQUESTION_TABLE, "parentId", (selectedQuiz.getId() + ""));

        try {
            topLevelIdString = getTopCats.execute().get();
            JSONArray jsonArray = new JSONArray(topLevelIdString);

            for (int j = 0; j < jsonArray.length(); ++j) {
                JSONObject jObject = jsonArray.getJSONObject(j);

                String questionText = jObject.get(TableNames.QUIZQUESTION_TEXT).toString();
                int questionId = Integer.parseInt(jObject.get(TableNames.QUIZQUESTION_ID).toString());

                allQuestions.add(new Questions(questionText, questionId));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allQuestions;
    }



}
