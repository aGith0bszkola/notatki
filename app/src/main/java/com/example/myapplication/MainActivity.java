package com.example.myapplication;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://raw.githubusercontent.com/aGith0bszkola/database_1/main/database.json";
    private List<Ksiazki> ksiazki = new ArrayList<>();
    private int aktualnaKsiazka = 0;
    Button nastepny, poprzedni;
    TextView Tytul, data_Publikacji, tresc;
    Image okladka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nastepny = findViewById(R.id.button);
        poprzedni = findViewById(R.id.button2);

        nastepny.setOnClickListener(e -> {
            if(aktualnaKsiazka < ksiazki.size() - 1){
                aktualnaKsiazka++;
                pokazKsiazke();
            }
        });

        poprzedni.setOnClickListener(e -> {
            if(aktualnaKsiazka > 0) {
                // ...
            }
        });
    }

    private void pobierzKsiazki() {
        Retrofit rf = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = rf.create(JsonPlaceHolder.class);
        Call<List<Ksiazki>> call = jsonPlaceHolder.getKsiazki();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Ksiazki>> call, Response<List<Ksiazki>> response) {
                if (!response.isSuccessful() || Objects.isNull(response.body())) {
                    Toast.makeText(MainActivity.this, "Blad serwera: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                ksiazki = response.body();
                aktualnaKsiazka = 0;
            }

            @Override
            public void onFailure(Call<List<Ksiazki>> call, Throwable t) {

            }
        });

    }

    private void pokazKsiazke() {
        if(ksiazki.isEmpty())
            return;
        Ksiazki ksiazka = ksiazki.get(aktualnaKsiazka);
        Tytul.setText("Tytuł: " + ksiazka.getTytul());
        tresc.setText("Tresc: " + ksiazka.getTresc());
        data_Publikacji.setText(ksiazka.getDataPublikacji());
    }
}