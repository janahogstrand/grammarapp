package com.grammar.trocket.grammar.com.grammar.trocket.exercises.audio_quiz;

public class AudioQuizAnswerList {
    AudioQuizQuestionsList audioQuestionListClass = new AudioQuizQuestionsList();
    String[] qusList = audioQuestionListClass.createArray();

    /**
     The getCorrectAnswer method returns the current question's correct answer
     by comparing the current question with the qusList array strings, if the current question
     matches a question in the qusList array the matched question's answer is returned as string */
    public String getCorrectAnswer(String Question){
        String correctAnswer = "";
        if (Question.equals(qusList[0])){
            correctAnswer = "Hand-and-foot";
        }
        else if (Question.equals(qusList[1])){
            correctAnswer = "Annerobinson";
        }
        else if (Question.equals(qusList[2])){
            correctAnswer = "Protos";
        }
        else if (Question.equals(qusList[3])){
            correctAnswer = "Policeman";
        }
        else if (Question.equals(qusList[4])){
            correctAnswer = "Hippo";
        }
        else if (Question.equals(qusList[5])){
            correctAnswer = "Horse";
        }
        return correctAnswer;

    }

    /**
     * The getOptions method returns the current question's options
     by comparing the current question with the qusList array strings, if the current question
     matches a question in the qusList array the matched question's option is returned as an array */
    public String[] getAnswerOptions(String Question){
        String ans1 = "";
        String ans2 = "";
        String ans3 = "";
        String ans4 = "";
        String ans5 = "";
        String ans6 = "";

        String[] options = new String[6];
        if (Question.equals(qusList[0])){
            ans1 = "Hand-and-foot";
            ans2 = "Foot-in-mouth";
            ans3 = "Hand-to-mouth";
            ans4 = "Cat";
            ans5 = "mouth-and-mouth";
            ans6 = "Foot-and-Foot";
        }
        else if (Question.equals(qusList[1])){
            ans1 = "Andalucia";
            ans2 = "Dog";
            ans3 = "Andypandy";
            ans4 = "Annerobinson";
            ans5 = "robinson";
            ans6 = "Anne";
        }
        else if (Question.equals(qusList[2])){
            ans1 = "Fish";
            ans2 = "Newtmen";
            ans3 = "Protos";
            ans4 = "Lamborghini";
            ans5 = "borghini";
            ans6 = "Lamb";
        }
        else if (Question.equals(qusList[3])){
            ans1 = "Policeman";
            ans2 = "Guinea Pig";
            ans3 = "2p coin";
            ans4 = "Smoked herring";
            ans5 = "herring";
            ans6 = "Smoked";
        }
        else if (Question.equals(qusList[4])){
            ans1 = "Hippo";
            ans2 = "Newly bought";
            ans3 = "Freshly cooked";
            ans4 = "Recently stolen";
            ans5 = "Firas";
            ans6 = "duck";
        }
        else if (Question.equals(qusList[5])){
            ans1 = "Horse";
            ans2 = "Climbing";
            ans3 = "Swimming";
            ans4 = "Flying";
            ans5 = "cat";
            ans6 = "Jana";
        }

        options[0] = ans1;
        options[1] = ans2;
        options[2] = ans3;
        options[3] = ans4;
        options[4] = ans5;
        options[5] = ans6;

        return options;
    }

}
