package com.grammar.trocket.grammar.com.grammar.trocket.exercises;


import android.app.Activity;

import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.QuizDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by firasAltayeb on 15/02/2016.
 */
public class QuizzesQuestions {


    public Quiz selectedQuiz;
    public String selectedQuizType;
    public int selectedQuizPosition;
    public ArrayList<Quiz> quizList;
    public Activity callingActivity;

    public ArrayList<Question> allQuestions;
    public ArrayList<String> currentQuestions;


    public QuizzesQuestions(Activity callingActivity, int selectedQuizPosition, String selectedQuizType) {
        allQuestions = new ArrayList<>();
        currentQuestions = new ArrayList<>();
        this.quizList = QuizDialog.quizList;

        this.callingActivity = callingActivity;
        this.selectedQuizPosition = selectedQuizPosition;
        this.selectedQuizType = selectedQuizType;
    }


    public  ArrayList<Question> getQuizQuestions(){
        allQuestions.clear();
        String topLevelIdString = "";
        selectedQuiz = quizList.get(selectedQuizPosition);

//        Log.d("1111111111111", selectedQuiz.getName());
//        Log.d("1111111111111", "1111111111111");
//        Log.d("1111111111111", selectedQuiz.getQuizType());
//        Log.d("1111111111111", "1111111111111");

        ArrayList<Question> qusList = new ArrayList<Question>();
        GetJSON getTopCats = new GetJSON(callingActivity, TableNames.QUIZQUESTION_TABLE, "parentId", (selectedQuiz.getId() + ""));

        try
        {
                if(selectedQuiz.getQuizType().equals("Text") || selectedQuiz.getQuizType().equals("Picture")) {

                    topLevelIdString = getTopCats.execute().get();
                    JSONArray jsonArray = new JSONArray(topLevelIdString);

                    for (int j = 0; j < jsonArray.length(); ++j) {
                        JSONObject jObject = jsonArray.getJSONObject(j);

                        String questionText = jObject.get(TableNames.QUIZQUESTION_TEXT).toString();
                        int questionId = Integer.parseInt(jObject.get(TableNames.QUIZQUESTION_ID).toString());

                        allQuestions.add(new Question(questionText, questionId));

                    }
                }
                else if(selectedQuiz.getQuizType().equals("Audio") || selectedQuiz.getQuizType().equals("Multiple")){

                        topLevelIdString = getTopCats.execute().get();
                        JSONArray jsonArray = new JSONArray(topLevelIdString);

                        for (int j = 0; j < jsonArray.length(); ++j) {
                            JSONObject jObject = jsonArray.getJSONObject(j);

                            String questionText = jObject.get(TableNames.QUIZQUESTION_TEXT).toString();
                            int questionId = Integer.parseInt(jObject.get(TableNames.QUIZQUESTION_ID).toString());

                            String audioURL = jObject.get(TableNames.QUIZQUESTION_AUDIOURL).toString();
                            if(!(audioURL == null)){
                                audioURL = audioURL.substring(0, audioURL.length()-4) + "raw=1";
                                allQuestions.add(new Question(audioURL, questionId));
                            }
                            else if (audioURL == null) {
                                allQuestions.add(new Question(questionText, questionId));
                            }

                        }
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
