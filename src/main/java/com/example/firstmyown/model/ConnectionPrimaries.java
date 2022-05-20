package com.example.firstmyown.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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

    public ConnectionPrimaries(int vocabularyid) {
        this.vocabularyid = vocabularyid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionPrimaries that = (ConnectionPrimaries) o;
        return vocabularyid == that.vocabularyid && wordid == that.wordid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vocabularyid, wordid);
    }

    @Override
    public String toString() {
        return "ConnectionPrimaries{" +
                "vocabularyid=" + vocabularyid +
                ", wordid=" + wordid +
                '}';
    }
}
