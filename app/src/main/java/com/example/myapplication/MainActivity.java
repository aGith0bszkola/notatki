package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private List<Ksiazki> ksiazki = new ArrayList<>();
    private int aktualnaKsiazka = 0;
    Button nastepny, poprzedni;
    TextView Tytul, data_Publikacji, tresc;
    ImageView okladka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nastepny = findViewById(R.id.button2);
        poprzedni = findViewById(R.id.button);
        Tytul = findViewById(R.id.tytul);
        tresc = findViewById(R.id.tresc);
        data_Publikacji = findViewById(R.id.textView2);
        okladka = findViewById(R.id.okladka);
        nastepny.setOnClickListener(e -> {
            if (!ksiazki.isEmpty()) {
                aktualnaKsiazka++;

                if (aktualnaKsiazka >= ksiazki.size()) {
                    aktualnaKsiazka = 0;
                }

                pokazKsiazke();
            }
        });

        poprzedni.setOnClickListener(e -> {
            if (!ksiazki.isEmpty()) {
                aktualnaKsiazka--;

                if (aktualnaKsiazka < 0) {
                    aktualnaKsiazka = ksiazki.size() - 1;
                }

                pokazKsiazke();
            }
        });


        pobierzKsiazki();

    }

    public void pobierzKsiazki() {
        Retrofit rf = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/aGith0bszkola/database_1/main/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = rf.create(JsonPlaceHolder.class);
        Call<List<Ksiazki>> call = jsonPlaceHolder.getKsiazki();

        call.enqueue(new Callback<List<Ksiazki>>() {
            @Override
            public void onResponse(Call<List<Ksiazki>> call, Response<List<Ksiazki>> response) {
                if (!response.isSuccessful() || Objects.isNull(response.body())) {
                    Toast.makeText(MainActivity.this, "Blad serwera: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                ksiazki = response.body();
                aktualnaKsiazka = 0;
                pokazKsiazke();
            }
            @Override
            public void onFailure(Call<List<Ksiazki>> call, Throwable t) {

            }

        });

    }

    private void pokazKsiazke() {
        if(ksiazki.isEmpty()) {
            Toast.makeText(this, "blad listy", Toast.LENGTH_SHORT).show();
            return;
        }else {
            Ksiazki ksiazka = ksiazki.get(aktualnaKsiazka);
            Tytul.setText("Tytul: " + ksiazki.get(aktualnaKsiazka).tytul);
            tresc.setText("Tytul: " + ksiazki.get(aktualnaKsiazka).Tresc);
            data_Publikacji.setText("Data publikacji: "+ ksiazki.get(aktualnaKsiazka).DataPublikacji);
            if(aktualnaKsiazka==0){
                okladka.setImageResource(R.drawable.harrypoter1);
            }else if(aktualnaKsiazka==1){
                okladka.setImageResource(R.drawable.harrypoter2);
            }else{
                okladka.setImageResource(R.drawable.harrypoter3);
            }



        }
    }
}