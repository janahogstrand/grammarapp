package com.grammar.trocket.grammingo.resources;

/**
 * Created by ran on 24/03/16.
 */
public class NumberCalendarItem {

    String label;
    String pronunciation;
    String audioUrl;

    public NumberCalendarItem(String label, String pronunciation, String audioUrl) {
        this.label = label;
        this.pronunciation = pronunciation;
        this.audioUrl = audioUrl;
    }

    public String getLabel() {
        return label;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getAudioUrl() {
        return audioUrl;
    }
}
