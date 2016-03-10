package com.grammar.trocket.grammar.com.grammar.trocket.resources;


import android.os.Bundle;
import android.widget.Button;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;

public class Alphabet extends BaseActivityDrawer {

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
        setContentView(R.layout.alphabet_main_menu);
        super.onCreateDrawer();


        /**
         * Buttons assigned to their respective ids
         */
        btnA = (Button) findViewById (R.id.btnA);
        btnB = (Button) findViewById (R.id.btnB);
        btnChe = (Button) findViewById (R.id.btnChe);
        btnC = (Button) findViewById (R.id.btnc);
        btnD = (Button) findViewById (R.id.btnD);
        btnE = (Button) findViewById (R.id.btnE);
        btnF = (Button) findViewById (R.id.btnF);
        btnG = (Button) findViewById (R.id.btnG);
        btnH = (Button) findViewById (R.id.btnH);
        btnI = (Button) findViewById (R.id.btnI);
        btnJ = (Button) findViewById (R.id.btnJ);
        btnK = (Button) findViewById (R.id.btnK);
        btnL = (Button) findViewById (R.id.btnL);
        btnLL = (Button) findViewById (R.id.btnLL);
        btnM = (Button) findViewById (R.id.btnM);
        btnN = (Button) findViewById (R.id.btnN);
        btnNN = (Button) findViewById (R.id.btnn);
        btnO = (Button) findViewById (R.id.btnO);
        btnP = (Button) findViewById (R.id.btnP);
        btnQ = (Button) findViewById (R.id.btnQ);
        btnR = (Button) findViewById (R.id.btnR);
        btnS = (Button) findViewById (R.id.btnS);
        btnT = (Button) findViewById (R.id.btnT);
        btnU = (Button) findViewById (R.id.btnU);
        btnV = (Button) findViewById (R.id.btnV);
        btnW = (Button) findViewById (R.id.btnW);
        btnX = (Button) findViewById (R.id.btnX);
        btnY = (Button) findViewById (R.id.btnY);
        btnZ = (Button) findViewById (R.id.btnZ);
        btnInfo = (Button) findViewById (R.id.btninfo);

        assignTextView();
    }

    /** assignTextView() is a method which sets
     * the text of each button.
     */
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
        btnP.setText("P p");
        btnQ.setText("Q q");
        btnR.setText("R r");
        btnS.setText("S s");
        btnT.setText("T t");
        btnU.setText("U u");
        btnV.setText("V v");
        btnW.setText("W w");
        btnX.setText("X x");
        btnY.setText("Y y");
        btnZ.setText("Z z");
        btnInfo.setText("Info");

    }


}
