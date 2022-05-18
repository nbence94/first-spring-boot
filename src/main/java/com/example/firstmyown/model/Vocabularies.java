package com.example.firstmyown.model;

import javax.persistence.*;

@Entity
@Table(name = "vocabulary")
public class Vocabularies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String megnevezes;
    private int szavak;
    private int jatszva;
    private int felhasznaloid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public int getSzavak() {
        return szavak;
    }

    public void setSzavak(int szavak) {
        this.szavak = szavak;
    }

    public int getJatszva() {
        return jatszva;
    }

    public void setJatszva(int jatszva) {
        this.jatszva = jatszva;
    }

    public int getFelhasznaloid() {
        return felhasznaloid;
    }

    public void setFelhasznaloid(int felhasznaloid) {
        this.felhasznaloid = felhasznaloid;
    }
}
