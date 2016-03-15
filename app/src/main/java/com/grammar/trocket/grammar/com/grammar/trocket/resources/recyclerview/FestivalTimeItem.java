package com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jamiemoreland on 24/02/16.
 * Creates an item to hold data
 * for either a festival or the time
 * Parcelable is used so that items of this type
 * can be parsed to a new intent
 * @see Parcelable
 */
public class FestivalTimeItem implements Parcelable {

    String spanishName;
    String englishName;
    String picture;
    String dialect;

    /**
     * @param spanishName Name of festival or time in spanish
     * @param englishName Name of festival or time in english
     * @param picture     Picture of festival
     * @param dialect     Dialect of phrase
     **/
    public FestivalTimeItem(String spanishName, String englishName, String picture, String dialect) {
        this.spanishName = spanishName;
        this.englishName = englishName;
        this.picture = picture;
        this.dialect = dialect;
    }
    /**
     * Gets name in spanish
     * **/
    public String getSpanishName() {
        return spanishName;
    }
    /**
     * Gets name in english
     * **/
    public String getEnglishName() {
        return englishName;
    }
    /**
     * Gets dialect
     * **/
    public String getDialect() {
        return dialect;
    }
    /**
     * Gets int of photo
     * **/
    public String getPhoto() {
        return picture;
    }
    /**
     * Constructor for parcel
     * data is parsed through
     * @see Parcel
     * **/
    public FestivalTimeItem(Parcel in) {
        String data[] = new String[4];

        in.readStringArray(data);
        this.spanishName = data[0];
        this.englishName = data[1];
        this.picture = data[2];
        this.dialect = data[3];
    }

    /**
     * Used for parcel
     * **/
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * Parses data to parcel
     * **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.spanishName, this.englishName, ("" + this.picture), this.dialect});
    }
    /**
     * Returns FestivalTimeItem with parcel parameter
     * **/
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public FestivalTimeItem createFromParcel(Parcel in) {
            return new FestivalTimeItem(in);
        }

        public FestivalTimeItem[] newArray(int size) {
            return new FestivalTimeItem[size];
        }
    };
}
