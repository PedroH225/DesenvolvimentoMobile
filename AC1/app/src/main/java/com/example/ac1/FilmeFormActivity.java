package com.example.ac1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ac1.db.SQLHelper;

public class FilmeFormActivity extends AppCompatActivity {

    private SQLHelper sqlHelper =  new SQLHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filme_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.buttonSubmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextTitulo = findViewById(R.id.editTextTitulo);
                EditText editTextAno = findViewById(R.id.editTextNumber);
                RatingBar editTextNota = findViewById(R.id.ratingBar);
                EditText editTextGenero = findViewById(R.id.editTextGenero);
                RadioGroup radioGroup = findViewById(R.id.radioGroup);

                String titulo = editTextTitulo.getText().toString();
                String genero = editTextGenero.getText().toString();
                Integer ano = Integer.parseInt(editTextAno.getText().toString());
                Double nota = (double) editTextNota.getRating();
                boolean visualizacao = false;

                RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                String assistiu = selectedRadioButton.getText().toString();

                if (assistiu.equals("Sim")) {
                visualizacao = true;
                }

                long result = sqlHelper.inserirFilme(titulo, ano, nota, genero, visualizacao);

                if (result != 0L) {
                    Toast.makeText(FilmeFormActivity.this, "Filme adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.carregarFilmes();
                } else {
                    Toast.makeText(FilmeFormActivity.this, "Erro ao adicionar o filme!", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}