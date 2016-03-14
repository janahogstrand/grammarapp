package com.grammar.trocket.grammar.com.grammar.trocket.exercises.text_quiz;

/**
 * Created by firasAltayeb on 15/02/2016.
 */
public class TextQuizAnswersList {
    TextQuizQuestionsList qusListClass = new TextQuizQuestionsList();
    String[] qusList = qusListClass.createArray();

    /**
     The getCorrectAnswer method returns the current question's correct answer
     by comparing the current question with the qusList array strings, if the current question
     matches a question in the qusList array the matched question's answer is returned as string */
    public String getCorrectAnswer(String Question){
        String correctAnswer = "";
        if (Question.equals(qusList[0])){
            correctAnswer = "Foot-and-mouth";
        }
        if (Question.equals(qusList[1])){
            correctAnswer = "Anaconda";
        }
        if (Question.equals(qusList[2])){
            correctAnswer = "Frogmen";
        }
        if (Question.equals(qusList[3])){
            correctAnswer = "Cup of tea";
        }
        if (Question.equals(qusList[4])){
            correctAnswer = "Almost certain";
        }
        if (Question.equals(qusList[5])){
            correctAnswer = "Burrowing";
        }
        if (Question.equals(qusList[6])){
            correctAnswer = "Dashboard";
        }
        if (Question.equals(qusList[7])){
            correctAnswer = "Crash helmet";
        }
        if (Question.equals(qusList[8])){
            correctAnswer = "On the rocks";
        }
        if (Question.equals(qusList[9])){
            correctAnswer = "Weir";
        }
        if (Question.equals(qusList[10])){
            correctAnswer = "Puppy love";
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
            ans4 = "Foot-and-mouth";
            ans5 = "mouth-and-mouth";
            ans6 = "Foot-and-Foot";
        }
        if (Question.equals(qusList[1])){
            ans1 = "Andalucia";
            ans2 = "Anaconda";
            ans3 = "Andypandy";
            ans4 = "Annerobinson";
            ans5 = "robinson";
            ans6 = "Anne";
        }
        if (Question.equals(qusList[2])){
            ans1 = "Frogmen";
            ans2 = "Newtmen";
            ans3 = "Protos";
            ans4 = "Lamborghini";
            ans5 = "borghini";
            ans6 = "Lamb";
        }
        if (Question.equals(qusList[3])){
            ans1 = "Policeman";
            ans2 = "Cup of tea";
            ans3 = "2p coin";
            ans4 = "Smoked herring";
            ans5 = "herring";
            ans6 = "Smoked";
        }
        if (Question.equals(qusList[4])){
            ans1 = "Almost certain";
            ans2 = "Newly bought";
            ans3 = "Freshly cooked";
            ans4 = "Recently stolen";
            ans5 = "Firas";
            ans6 = "duck";
        }
        if (Question.equals(qusList[5])){
            ans1 = "Burrowing";
            ans2 = "Climbing";
            ans3 = "Swimming";
            ans4 = "Flying";
            ans5 = "cat";
            ans6 = "Jana";
        }
        if (Question.equals(qusList[6])){
            ans1 = "Chargeboard";
            ans2 = "Sprintboard";
            ans3 = "Dashboard";
            ans4 = "Jogboard";
            ans5 = "Jamie";
            ans6 = "Ran";
        }
        if (Question.equals(qusList[7])){
            ans1 = "Bash helmet";
            ans2 = "Crash helmet";
            ans3 = "Mash helmet";
            ans4 = "Flash helmet";
            ans5 = "Sam";
            ans6 = "Faisal";
        }
        if (Question.equals(qusList[8])){
            ans1 = "Shingled";
            ans2 = "On the rocks";
            ans3 = "Pebbledashed";
            ans4 = "Stoned";
            ans5 = "Shriti";
            ans6 = "Rahel";
        }
        if (Question.equals(qusList[9])){
            ans1 = "Seer";
            ans2 = "Rear";
            ans3 = "Fear";
            ans4 = "Weir";
            ans5 = "king";
            ans6 = "boss";
        }
        if (Question.equals(qusList[10])){
            ans1 = "Puppy love";
            ans2 = "Kitten love";
            ans3 = "Bunny love";
            ans4 = "Piggy love";
            ans5 = "yes";
            ans6 = "no";
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
