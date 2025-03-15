package com.example.androidtest;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinner);
        Button btnCheck = findViewById(R.id.btnCheck);

        String[] opcoes = getResources().getStringArray(R.array.opcoes_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 = findViewById(R.id.editTextNum1);
                EditText editText2 = findViewById(R.id.editTextNum2);

                Integer num1 = Integer.parseInt(editText1.getText().toString());
                Integer num2 = Integer.parseInt(editText2.getText().toString());

                String operacao = spinner.getSelectedItem().toString();

                try {
                    Integer result = calcular(operacao, num1, num2);

                    Toast.makeText(MainActivity2.this, result.toString(), Toast.LENGTH_LONG).show();
                } catch (RuntimeException e) {
                    Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private Integer calcular(String operacao, Integer num1, Integer num2) {
        Integer result = 0;

        switch (operacao.toLowerCase()) {
            case "soma":
            result = num1 + num2;
                break;
            case "subtração":
                result = num1 - num2;
                break;
            case "multiplicação":
                result = num1 * num2;
                break;
            case "divisão":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("O divisor não pode ser igual a zero:");
                }
                break;
        }

        return result;
    }
}
