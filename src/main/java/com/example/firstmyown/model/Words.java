package com.example.firstmyown.model;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String angol;
    private String magyar;

    public Words() {

    }

    public Words(int id, String angol, String magyar) {
        this.id = id;
        this.angol = angol;
        this.magyar = magyar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAngol() {
        return angol;
    }

    public void setAngol(String angol) {
        this.angol = angol;
    }

    public String getMagyar() {
        return magyar;
    }

    public void setMagyar(String magyar) {
        this.magyar = magyar;
    }
}
