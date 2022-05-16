package com.example.firstmyown.model;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class ConnectionPrimaries implements Serializable {

    private static final long serialVersionUID = 1L;

    private int vocabularyid;
    private int wordid;


    public ConnectionPrimaries() {

    }

    public ConnectionPrimaries(int vocabularyid, int wordid) {
        this.vocabularyid = vocabularyid;
        this.wordid = wordid;
    }

    public int getVocabularyid() {
        return vocabularyid;
    }

    public void setVocabularyid(int vocabularyid) {
        this.vocabularyid = vocabularyid;
    }

    public int getWordid() {
        return wordid;
    }

    public void setWordid(int wordid) {
        this.wordid = wordid;
    }
}
