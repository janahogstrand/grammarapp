package com.grammar.trocket.trocket.exercises;

import android.app.Activity;
import com.grammar.trocket.trocket.backend.GetJSON;
import com.grammar.trocket.trocket.backend.TableNames;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by firasAltayeb on 15/02/2016.
 */
public class QuizzesAnswers {

    public ArrayList<Question> questionsList;
    public Activity callingActivity;
    public String selectedQuizType;

    public QuizzesAnswers(Activity callingActivity, ArrayList<Question> questionsList, String selectedQuizType){

        this.callingActivity = callingActivity;
        this.questionsList = questionsList;
        this.selectedQuizType = selectedQuizType;
    }


    public String getCorrectAnswer(Question questions){

        String topLevelIdString = "";
        int correctOrNot = 0;
        String correctAnswer = "";

        GetJSON getTopCats = new GetJSON(callingActivity, TableNames.QUIZANSWER_TABLE,
                                        "parentId", (questions.getId() + ""));
        try
        {

            topLevelIdString = getTopCats.execute().get();
            JSONArray jsonArray = new JSONArray(topLevelIdString);

            if(selectedQuizType.equals("Text") || selectedQuizType.equals("Audio")) {

                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject jObject = jsonArray.getJSONObject(j);

                    correctOrNot = Integer.parseInt(jObject.get(TableNames.QUIZANSWER_CORRECT).toString());
                    if(correctOrNot == 1) {
                        correctAnswer = jObject.get(TableNames.QUIZANSWER_TEXT).toString();
                        break;
                    }

                }

            }
            else if(selectedQuizType.equals("Picture")){

                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject jObject = jsonArray.getJSONObject(j);

                    correctOrNot = Integer.parseInt(jObject.get(TableNames.QUIZANSWER_CORRECT).toString());
                    if(correctOrNot == 1) {
                        correctAnswer = jObject.get(TableNames.QUIZANSWER_IMAGEURL).toString();
                        break;
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
        return correctAnswer;

    }

    public ArrayList<String> getCorrectAnswerList(Question currentQuestion, String[] answerOptionArray){

        String topLevelIdString = "";
        int correctOrNot = 0;
        ArrayList<String> correctAnswerList = new ArrayList<>();

        GetJSON getTopCats = new GetJSON(callingActivity, TableNames.QUIZANSWER_TABLE,
                "parentId", (currentQuestion.getId() + ""));
        try
        {

            topLevelIdString = getTopCats.execute().get();
            JSONArray jsonArray = new JSONArray(topLevelIdString);

                for (int j = 0; j < answerOptionArray.length; ++j) {
                    JSONObject jObject = jsonArray.getJSONObject(j);

                    correctOrNot = Integer.parseInt(jObject.get(TableNames.QUIZANSWER_CORRECT).toString());

                    if(correctOrNot == 1) {
                        correctAnswerList.add(jObject.get(TableNames.QUIZANSWER_TEXT).toString());
                        break;
                    }

                }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return correctAnswerList;

    }


    public String[] getAnswerOptions(Question questions){

        String[] options = new String[6];
        String topLevelIdString = "";

        GetJSON getTopCats = new GetJSON(callingActivity, TableNames.QUIZANSWER_TABLE,
                "parentId", (questions.getId() + ""));

        try {
            topLevelIdString = getTopCats.execute().get();
            JSONArray jsonArray = new JSONArray(topLevelIdString);

            if(selectedQuizType.equals("Text") || selectedQuizType.equals("Audio")) {

                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject jObject = jsonArray.getJSONObject(j);

                    if(j == 0){ options[0] = jObject.get(TableNames.QUIZANSWER_TEXT).toString();}
                    if(j == 1){ options[1] = jObject.get(TableNames.QUIZANSWER_TEXT).toString();}
                    if(j == 2){ options[2] = jObject.get(TableNames.QUIZANSWER_TEXT).toString();}
                    if(j == 3){ options[3] = jObject.get(TableNames.QUIZANSWER_TEXT).toString();}
                    if(j == 4){ options[4] = jObject.get(TableNames.QUIZANSWER_TEXT).toString();}
                    if(j == 5){ options[5] = jObject.get(TableNames.QUIZANSWER_TEXT).toString();}

                }

            }
            else if(selectedQuizType.equals("Picture")){
                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject jObject = jsonArray.getJSONObject(j);

                    if(j == 0){ options[0] = jObject.get(TableNames.QUIZANSWER_IMAGEURL).toString();}
                    if(j == 1){ options[1] = jObject.get(TableNames.QUIZANSWER_IMAGEURL).toString();}
                    if(j == 2){ options[2] = jObject.get(TableNames.QUIZANSWER_IMAGEURL).toString();}
                    if(j == 3){ options[3] = jObject.get(TableNames.QUIZANSWER_IMAGEURL).toString();}
                    if(j == 4){ options[4] = jObject.get(TableNames.QUIZANSWER_IMAGEURL).toString();}
                    if(j == 5){ options[5] = jObject.get(TableNames.QUIZANSWER_IMAGEURL).toString();}

                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return options;
    }
}
