package com.example.androidtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nomeCampo = findViewById(R.id.editTextNome3);
                LinearLayout linearL = findViewById(R.id.LinearL);

                String nome = nomeCampo.getText().toString();

                List<String> letras = Arrays.asList(nome.split(""));

                atualizarCheckbox(letras);
            }
        });
    }

    private void atualizarCheckbox(List<String> letras) {
        for (String letra: letras) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(letra);

            LinearLayout l = findViewById(R.id.LinearL);

            l.addView(checkBox);
        }
    }
}