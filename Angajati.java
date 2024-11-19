package org.example;

import java.time.LocalDate;

public class Angajati {
    private String nume;
    private String postul;


    private LocalDate dataAngajarii;
    private float salariul;

    public Angajati() {
    }

    public Angajati(String nume, String postul, LocalDate dataAngajarii, float salariul) {
        this.nume = nume;
        this.postul = postul;
        this.dataAngajarii = dataAngajarii;
        this.salariul = salariul;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPostul() {
        return postul;
    }

    public void setPostul(String postul) {
        this.postul = postul;
    }

    public LocalDate getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(LocalDate dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    public float getSalariul() {
        return salariul;
    }

    public void setSalariul(float salariul) {
        this.salariul = salariul;
    }

    @Override
    public String toString() {
        return "Angajati{" +
                "nume='" + nume + '\'' +
                ", postul='" + postul + '\'' +
                ", dataAngajarii=" + dataAngajarii +
                ", salariul=" + salariul +
                '}';
    }
}
