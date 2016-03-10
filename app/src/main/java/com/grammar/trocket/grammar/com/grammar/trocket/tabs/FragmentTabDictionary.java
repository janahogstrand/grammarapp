package com.grammar.trocket.grammar.com.grammar.trocket.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grammar.trocket.grammar.R;


public class FragmentTabDictionary extends Fragment {

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

    View view;

    /**
     * Inflate fragment tab 3
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_dictionary, container, false);
        findViewByIds();
        assignTextView();
        return view;
    }

    public void findViewByIds(){
        /**
         * Buttons assigned to their respective ids
         */
        btnA = (Button) view.findViewById(R.id.btnA);
        btnB = (Button) view.findViewById(R.id.btnB);
        btnChe = (Button) view.findViewById(R.id.btnChe);
        btnC = (Button) view.findViewById(R.id.btnc);
        btnD = (Button) view.findViewById(R.id.btnD);
        btnE = (Button) view.findViewById(R.id.btnE);
        btnF = (Button) view.findViewById(R.id.btnF);
        btnG = (Button) view.findViewById(R.id.btnG);
        btnH = (Button) view.findViewById(R.id.btnH);
        btnI = (Button) view.findViewById(R.id.btnI);
        btnJ = (Button) view.findViewById(R.id.btnJ);
        btnK = (Button) view.findViewById(R.id.btnK);
        btnL = (Button) view.findViewById(R.id.btnL);
        btnLL = (Button) view.findViewById(R.id.btnLL);
        btnM = (Button) view.findViewById(R.id.btnM);
        btnN = (Button) view.findViewById(R.id.btnN);
        btnNN = (Button) view.findViewById(R.id.btnn);
        btnO = (Button) view.findViewById(R.id.btnO);
        btnP = (Button) view.findViewById(R.id.btnP);
        btnQ = (Button) view.findViewById(R.id.btnQ);
        btnR = (Button) view.findViewById(R.id.btnR);
        btnS = (Button) view.findViewById(R.id.btnS);
        btnT = (Button) view.findViewById(R.id.btnT);
        btnU = (Button) view.findViewById(R.id.btnU);
        btnV = (Button) view.findViewById(R.id.btnV);
        btnW = (Button) view.findViewById(R.id.btnW);
        btnX = (Button) view.findViewById(R.id.btnX);
        btnY = (Button) view.findViewById(R.id.btnY);
        btnZ = (Button) view.findViewById(R.id.btnZ);
        btnInfo = (Button) view.findViewById(R.id.btninfo);
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
