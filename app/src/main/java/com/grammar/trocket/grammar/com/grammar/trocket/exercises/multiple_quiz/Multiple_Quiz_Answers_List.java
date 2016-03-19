package com.grammar.trocket.grammar.com.grammar.trocket.exercises.multiple_quiz;

/**
 * Created by firasAltayeb on 15/02/2016.
 */
public class Multiple_Quiz_Answers_List {

    Multiple_Quiz_Questions_List qusListClass = new Multiple_Quiz_Questions_List();
    String[] qusList = qusListClass.createArray();

    /**
     The getCorrectAnswer method returns the current question's correct answer
     by comparing the current question with the qusList array strings, if the current question
     matches a question in the qusList array the matched question's answer is returned as string */
    public String[] getCorrectAnswer(String Question){
        String[] answerList = new String[6];
        if (Question.equals(qusList[0])){
            answerList[0] = "Blue";
            answerList[1] = "Red";
            answerList[2] = "Yellow";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[1])){
            answerList[0] = "Car";
            answerList[1] = "Bike";
            answerList[2] = "Plane";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[2])){
            answerList[0] = "Cat";
            answerList[1] = "Dog";
            answerList[2] = "Fish";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[3])){
            answerList[0] = "Water";
            answerList[1] = "Tea";
            answerList[2] = "Coffee";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[4])){
            answerList[0] = "Earth";
            answerList[1] = "Wind";
            answerList[2] = "Fire";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[5])){
            answerList[0] = "Dragon Ball";
            answerList[1] = "Dragon Ball Z";
            answerList[2] = "Dragon Ball Super";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[6])){
            answerList[0] = "나비";
            answerList[1] = "사자";
            answerList[2] = "개";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[7])){
            answerList[0] = "Brazil";
            answerList[1] = "Peru";
            answerList[2] = "Colombia";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[8])){
            answerList[0] = "Look at me";
            answerList[1] = "Ooohhh can do";
            answerList[2] = "WHY'D YOU EVEN ROPE ME INTO THIS!";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[9])){
            answerList[0] = "Squirtle";
            answerList[1] = "Charmander";
            answerList[2] = "Bulbasaur";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        if (Question.equals(qusList[10])){
            answerList[0] = "Blue";
            answerList[1] = "Red";
            answerList[2] = "Yellow";
            answerList[3] = "";
            answerList[4] = "";
            answerList[5] = "";
        }
        return answerList;

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
            ans1 = "Blue";
            ans2 = "Red";
            ans3 = "Yellow";
            ans4 = "Purple";
            ans5 = "Orange";
            ans6 = "Green";
        }
        if (Question.equals(qusList[1])){
            ans1 = "Bike";
            ans2 = "Dog";
            ans3 = "Plane";
            ans4 = "Anne Robinson";
            ans5 = "Car";
            ans6 = "Burrito";
        }
        if (Question.equals(qusList[2])){
            ans1 = "Frogmen";
            ans2 = "Newtmen";
            ans3 = "Cat";
            ans4 = "Fish";
            ans5 = "Dog";
            ans6 = "Cthulu";
        }
        if (Question.equals(qusList[3])){
            ans1 = "Water";
            ans2 = "Dirt";
            ans3 = "Grass";
            ans4 = "Tea";
            ans5 = "Herring";
            ans6 = "Coffee";
        }
        if (Question.equals(qusList[4])){
            ans1 = "Fire";
            ans2 = "Earth";
            ans3 = "Rice";
            ans4 = "Constantinople";
            ans5 = "Wind";
            ans6 = "Duck";
        }
        if (Question.equals(qusList[5])){
            ans1 = "Dragon Ball";
            ans2 = "Dragoon";
            ans3 = "Dragon Ball Z";
            ans4 = "Mr Popo";
            ans5 = "Dragon Ball Super";
            ans6 = "Dragon Ball GT";
        }
        if (Question.equals(qusList[6])){
            ans1 = "나비";
            ans2 = "사자";
            ans3 = "개";
            ans4 = "공부";
            ans5 = "년";
            ans6 = "안녕";
        }
        if (Question.equals(qusList[7])){
            ans1 = "Brazil";
            ans2 = "Papua New Guinea";
            ans3 = "Peru";
            ans4 = "New Zealand";
            ans5 = "Colombia";
            ans6 = "Sealand";
        }
        if (Question.equals(qusList[8])){
            ans1 = "Look at me";
            ans2 = "WUBBA LUBBA DUB DUBS!!!";
            ans3 = "Ooohhh can do";
            ans4 = "Ohh yea, you gotta get schwifty";
            ans5 = "WHY'D YOU EVEN ROPE ME INTO THIS!";
            ans6 = "Don't be trippin dog we got you";
        }
        if (Question.equals(qusList[9])){
            ans1 = "Squirtle";
            ans2 = "Mew";
            ans3 = "Tauros";
            ans4 = "Charmander";
            ans5 = "Bulbasaur";
            ans6 = "Beedrill";
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
