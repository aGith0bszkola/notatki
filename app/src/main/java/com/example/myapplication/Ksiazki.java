package com.example.myapplication;



import com.google.gson.annotations.SerializedName;

import kotlin.jvm.internal.SerializedIr;

public class Notatki {
    @SerializedName("Tytul")
    private String tytul;
    @SerializedName("Tresc")
    private  String tresc;

    public int getData_publikacji() {
        return data_publikacji;
    }

    public void setData_publikacji(int data_publikacji) {
        this.data_publikacji = data_publikacji;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @SerializedName("Data_publikacji")
    private int data_publikacji;

    public Notatki(String tytul, String tresc, int data_publikacji) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.data_publikacji = data_publikacji;
    }
}
