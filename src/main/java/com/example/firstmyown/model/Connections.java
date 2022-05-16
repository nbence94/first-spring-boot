package com.example.firstmyown.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "connection")
public class Connections {

    @EmbeddedId
    private ConnectionPrimaries id;

    public Connections() {
    }

    public Connections(ConnectionPrimaries id) {
        this.id = id;
    }

    public ConnectionPrimaries getId() {
        return id;
    }

    public void setId(ConnectionPrimaries id) {
        this.id = id;
    }

    /*private int vocabulary_id;
    private int word_id;

    public int getVocabulary_id() {
        return vocabulary_id;
    }

    public void setVocabulary_id(int vocabulary_id) {
        this.vocabulary_id = vocabulary_id;
    }

    public int getWord_id() {
        return word_id;
    }

    public void setWord_id(int word_id) {
        this.word_id = word_id;
    }*/
}
