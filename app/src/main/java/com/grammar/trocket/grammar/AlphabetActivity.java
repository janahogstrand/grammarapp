package com.grammar.trocket.grammar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AlphabetActivity extends AppCompatActivity {

    public Button btnA;                 public Button btnLL;
    public Button btnB;                 public Button btnM;
    public Button btnChe;               public Button btnN;
    public Button btnC;                 public Button btnNN;
    public Button btnD;                 public Button btnO;
    public Button btnE;                 public Button btnP;
    public Button btnF;                 public Button btnQ;
    public Button btnG;                 public Button btnR;
    public Button btnH;                 public Button btnS;
    public Button btnI;                 public Button btnT;
    public Button btnJ;                 public Button btnU;
    public Button btnK;                 public Button btnV;
    public Button btnL;                 public Button btnW;
    public Button btnZ;                 public Button btnX;
    public Button btnInfo;              public Button btnY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        //Before you call this method you have to find the btns ids
        //assignTextView();

    }

    public void assignTextView(){

        btnA.setText("A a");
        btnB.setText("B b");
        btnChe.setText("C c");
        btnC.setText("C Che");
        btnD.setText("D d");
        btnE.setText("E e");
        btnF.setText("F f");
        btnG.setText("G g");
        btnH.setText("H h");
        btnI.setText("I i");
        btnJ.setText("J j");
        btnK.setText("K k");
        btnL.setText("L l");
        btnLL.setText("L ll");

        btnM.setText("M m");
        btnN.setText("N n");
        btnNN.setText("Nn o");
        btnO.setText("O o");
        btnP.setText("btnP");
        btnQ.setText("btnQ");
        btnR.setText("btnR");
        btnS.setText("btnS");
        btnT.setText("btnT");
        btnU.setText("btnU");
        btnV.setText("btnV");
        btnW.setText("btnW");
        btnX.setText("btnX");
        btnY.setText("btnY");
        btnZ.setText("btnZ");
        btnInfo.setText("btnInfo");

    }

}
