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

    @Override
    public String toString() {
        return "Connections{" +
                "id=" + id +
                '}';
    }
}
