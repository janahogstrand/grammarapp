package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.app.Activity;
import android.util.Log;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by firasAltayeb on 09/03/2016.
 */
public class ListViewActivityItems {

    ArrayList<NumberCalendarItem> arrayList = new ArrayList<>();
    Activity activity;
    int id;

    public ListViewActivityItems(Activity activity, int id) {
        this.activity = activity;
        this.id = id;
    }

    public ArrayList<NumberCalendarItem> getArray(){

        String tapString = "";
        GetJSON getTap = new GetJSON(activity, TableNames.TAP_TABLE, "parentId", (id + ""));
        try {
            tapString = getTap.execute().get();
            Log.w("Categories", tapString);

            JSONArray jsonArray = new JSONArray(tapString);
            JSONObject tap = jsonArray.getJSONObject(0);
            String tapId = tap.get(TableNames.TAP_ID).toString();

            String tapItems = "";
            GetJSON getTaps = new GetJSON(activity, TableNames.TAPITEM_TABLE, "parentId", tapId);
            tapItems = getTaps.execute().get();
            Log.w("Festivals", tapItems);
            JSONArray tapArray = new JSONArray(tapItems);
            for(int i = 0; i< tapArray.length(); ++i){
                JSONObject jObject = tapArray.getJSONObject(i);
                String label = jObject.get(TableNames.TAPITEM_LABEL).toString();
                String pronunciation = jObject.get(TableNames.TAPITEM_PRONUNCIATION).toString();
                String url = jObject.get(TableNames.TAPITEM_AUDIOURL).toString();
                arrayList.add(new NumberCalendarItem(label,pronunciation,url));
            }

//            for (int j = 0; j < jsonArray.length(); ++j) {
//                JSONObject jObject = jsonArray.getJSONObject(j);

//                String letter = jObject.get(TableNames.DICTIONARYLETTER_LABEL).toString();
//                int id = Integer.parseInt(jObject.get(TableNames.DICTIONARYLETTER_ID).toString());
//                alphabetList.add(new AlphabetItem(letter, true, id));
//                Collections.sort(alphabetList,
//                        new Comparator<AlphabetItem>() {
//                            public int compare(AlphabetItem letter1, AlphabetItem letter2) {
//                                return letter1.getLetter().toUpperCase().compareTo(letter2.getLetter().toUpperCase());
//                            }
//                        });
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;

//        arrayList.add("1");
//        arrayList.add("2");
//        arrayList.add("3");
//        arrayList.add("4");
//        arrayList.add("5");
//        arrayList.add("6");
//        arrayList.add("7");
//        arrayList.add("8");
//        arrayList.add("9");
//        arrayList.add("10");
//        arrayList.add("11");
//        arrayList.add("12");
//        arrayList.add("13");
//        arrayList.add("14");
//        arrayList.add("15");
//        arrayList.add("16");
//        arrayList.add("17");
//        arrayList.add("18");
//        arrayList.add("19");
//        arrayList.add("20");
//        arrayList.add("30");
//        arrayList.add("40");
//        arrayList.add("50");
//        arrayList.add("60");
//        arrayList.add("70");
//        arrayList.add("80");
//        arrayList.add("90");
//        arrayList.add("100");
//        arrayList.add("1000");
//        arrayList.add("10,000");
//        arrayList.add("100,000");
//        arrayList.add("1,000,000");

    }

    public ArrayList<NumberCalendarItem> getCalendarArray(){

//        arrayList.add("1\nuno");
//        arrayList.add("2\ndos");
//        arrayList.add("3\ntres");
//        arrayList.add("4\ncuatro");
//        arrayList.add("5\ncinco");
//        arrayList.add("6\nseis");
//        arrayList.add("7\nsiete");
//        arrayList.add("8\nocho");
//        arrayList.add("9\nnueve");
//        arrayList.add("10\ndiez");
//        arrayList.add("11\nonce");
//        arrayList.add("12\ndoce");
//        arrayList.add("13\ntrece");
//        arrayList.add("14\ncatorce");
//        arrayList.add("15\nquince");
//        arrayList.add("16\ndiez y seis diecises");
//        arrayList.add("17\ndiez y seis diecisite");
//        arrayList.add("18\ndiez y seis diecioho");
//        arrayList.add("19\ndiez y seis diecinueve");
//        arrayList.add("20\nveinte");
//        arrayList.add("21\nveintiuno");
//        arrayList.add("22\nveintidos");
//        arrayList.add("23\nveinitires");
//        arrayList.add("24\nveinticuatro");
//        arrayList.add("25\nveinticino");
//        arrayList.add("26\nveintiseis");
//        arrayList.add("27\nveintisiete");
//        arrayList.add("28\nveintiocho");
//        arrayList.add("29\nveintinueve");
//        arrayList.add("30\ntreinta");
//        arrayList.add("31\ntreinta y uno");
//
        return arrayList;
    }
}
