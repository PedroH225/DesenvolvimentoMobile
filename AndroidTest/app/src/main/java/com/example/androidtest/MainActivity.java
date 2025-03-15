package com.example.androidtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Button button = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNome = findViewById(R.id.editTextNome);
                String nome = editTextNome.getText().toString();

                EditText editTextIdade = findViewById(R.id.editTextNome2);
                Integer idade = Integer.parseInt(editTextIdade.getText().toString());

                String textResult = "";
                if (idade < 18) {
                    textResult = nome + " é menor de idade";
                } else {
                    textResult = nome + " é maior de idade";

                }

                Toast.makeText(MainActivity.this,  textResult, Toast.LENGTH_SHORT).show();
            }
        });

    }

}