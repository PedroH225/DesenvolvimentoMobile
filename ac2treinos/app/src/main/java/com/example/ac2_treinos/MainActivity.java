package com.example.ac2_treinos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ac2_treinos.db.BancoHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewTreinos;
    BancoHelper databaseHelper = new BancoHelper(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> listaExercicios;
    ArrayList<Integer> listaIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //databaseHelper.inserirExercicio("exercicio", 30, "execução");

        listViewTreinos = findViewById(R.id.listView);

        carregarExercicios();

        Button button = findViewById(R.id.button);

        Button buttonIniciar = findViewById(R.id.button2);

        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TreinoActivity.class);

                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdicionarTreinoActivity.class);

                startActivity(intent);
            }
        });
    }

    public void carregarExercicios() {
        Cursor cursor = databaseHelper.listarExercicios();
        listaExercicios = new ArrayList<>();
        listaIds = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                int duracao = cursor.getInt(2);
                String execucao = cursor.getString(3);
                listaExercicios.add(id + " - " + nome + " - " + duracao + " segundos - " + execucao);
                listaIds.add(id);
            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaExercicios);
        listViewTreinos.setAdapter(adapter);
    }

}