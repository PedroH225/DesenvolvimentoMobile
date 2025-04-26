package com.example.ac2_treinos;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ac2_treinos.db.BancoHelper;

import java.util.ArrayList;

public class TreinoActivity extends AppCompatActivity {
    Button buttonDescanso;
    TextView textViewTreino;
    TextView textViewExecucao;
    TextView textViewDescanso;

    Integer descanso;

    Cursor cursor;

    Notification notification = new Notification();

    BancoHelper databaseHelper = new BancoHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_treino);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonDescanso = findViewById(R.id.buttonDescanso);
        textViewTreino = findViewById(R.id.textViewTreino);
        textViewExecucao = findViewById(R.id.textViewExecucao);
        textViewDescanso = findViewById(R.id.textViewDescanso);

        cursor = databaseHelper.listarExercicios();
        cursor.moveToFirst();

        carregarTreino();
    }

    private void carregarTreino() {
        textViewDescanso.setText("");
            String treino = "Treino: " +  cursor.getString(1);
            String execucao = "Execução: "  +  cursor.getString(3);
            textViewTreino.setText(treino);
            textViewExecucao.setText(execucao);

            this.descanso = cursor.getInt(2);

    }

    public void iniciarDescanso(View view) {
        long descansoInicial = descanso * 1000L;
        textViewDescanso.setText(String.valueOf(descansoInicial));
        buttonDescanso.setEnabled(false);

        CountDownTimer countDownTimer = new CountDownTimer(descansoInicial, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            String tempoRestante = String.valueOf(millisUntilFinished / 1000);
            textViewDescanso.setText(tempoRestante);
            }

            @Override
            public void onFinish() {
            if (cursor.isLast()) {
                Intent intent = new Intent(TreinoActivity.this, MainActivity.class);

                startActivity(intent);
                Toast.makeText(TreinoActivity.this, "Treino concluído, Parabéns!", Toast.LENGTH_SHORT).show();
            } else {
                cursor.moveToNext();
                carregarTreino();
                buttonDescanso.setEnabled(true);
                Toast.makeText(TreinoActivity.this, "Proximo treino.", Toast.LENGTH_SHORT).show();
            }
            }
        };
        countDownTimer.start();
    }
}


