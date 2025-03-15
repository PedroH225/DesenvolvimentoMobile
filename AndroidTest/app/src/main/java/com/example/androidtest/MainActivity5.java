package com.example.androidtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirSelecionados();
            }
        });
    }
    private void exibirSelecionados() {
        LinearLayout linearLayout = findViewById(R.id.linearLayout2);
        List<String> selecionados = new ArrayList<>();

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
            if (checkBox.isChecked()) {
            selecionados.add(checkBox.getText().toString());
            }
        }

        if (!selecionados.isEmpty()) {
            Toast.makeText(this, "Opções selecionadas: " + selecionados.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Nenhuma opção selecionada.", Toast.LENGTH_LONG).show();

        }
    }
}



