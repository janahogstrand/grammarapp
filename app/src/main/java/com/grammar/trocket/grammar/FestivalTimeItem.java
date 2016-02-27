package com.grammar.trocket.grammar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jamiemoreland on 24/02/16.
 */
public class FestivalTimeItem implements Parcelable {
    String spanishName;
    String englishName;
    int picture;

    /**
     * @param spanishName Name of festival or time in spanish
     * @param englishName Name of festival or time in english
     * @param picture Picture of festival
     * **/
    public FestivalTimeItem(String spanishName, String englishName, int picture){
        this.spanishName = spanishName;
        this.englishName = englishName;
        this.picture = picture;
    }

    public String getSpanishName(){
        return spanishName;
    }
    public String getEnglishName(){
        return englishName;
    }
    public int getPhoto(){
        return picture;
    }

    public FestivalTimeItem(Parcel in){
        String data[] = new String[3];

        in.readStringArray(data);
        this.spanishName = data[0];
        this.englishName = data[1];
        this.picture = Integer.parseInt(data[2]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.spanishName, this.englishName, ("" + this.picture)});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public FestivalTimeItem createFromParcel(Parcel in){
            return  new FestivalTimeItem(in);
        }
        public FestivalTimeItem[] newArray(int size){
            return new FestivalTimeItem[size];
        }
    };
}
