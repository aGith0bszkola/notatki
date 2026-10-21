package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Ksiazki {
    @SerializedName("Tytul")
    String tytul;
    @SerializedName("Tresc")
    String Tresc;
    @SerializedName("Data_Publickaji")
    int DataPublikacji;

    public Ksiazki(String tytul, String tresc, int dataPublikacji) {
        this.tytul = tytul;
        this.Tresc = tresc;
        DataPublikacji = dataPublikacji;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return Tresc;
    }

    public void setTresc(String tresc) {
        Tresc = tresc;
    }

    public int getDataPublikacji() {
        return DataPublikacji;
    }

    public void setDataPublikacji(int dataPublikacji) {
        DataPublikacji = dataPublikacji;
    }
}
