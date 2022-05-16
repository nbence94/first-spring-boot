package com.example.firstmyown.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
    @Table(name = "users")
    public class Users {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String nev;
        private String jelszo;
        private String email;

        public int getId() {
            return id;
        }

        public String getNev() {
            return nev;
        }

        public void setNev(String nev) {
            this.nev = nev;
        }

        public String getJelszo() {
            return jelszo;
        }

        public void setJelszo(String jelszo) {
            this.jelszo = jelszo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Users{" +
                    "id=" + id +
                    ", nev='" + nev + '\'' +
                    ", jelszo='" + jelszo + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(nev, users.nev) && Objects.equals(jelszo, users.jelszo) && Objects.equals(email, users.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, jelszo, email);
    }
}
