package com.example.androidtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidtest.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout linearL = findViewById(R.id.linearLayout);
        String[] cores = getResources().getStringArray(R.array.cores);

        for (int i = 0; i < cores.length; i++) {
            CheckBox novaCheckBox = new CheckBox(this);
            novaCheckBox.setText(cores[i]);
            linearL.addView(novaCheckBox);
        }

        Button button = findViewById(R.id.buttonCadastrar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nomeCampo = findViewById(R.id.editTextNome);
                EditText idadeCampo = findViewById(R.id.editTextIdade);
                EditText ufCampo = findViewById(R.id.editTextUF);
                EditText cidadeCampo = findViewById(R.id.editTextCidade);
                EditText telefoneCampo = findViewById(R.id.editTextTelefone);
                EditText emailCampo = findViewById(R.id.editTextTextEmailAddress);

                RadioGroup tamanhoGroup = findViewById(R.id.radioGroup);
                LinearLayout coresLayout = findViewById(R.id.linearLayout);

                String nome = nomeCampo.getText().toString();
                Integer idade = Integer.parseInt(idadeCampo.getText().toString());
                String uf = ufCampo.getText().toString();
                String cidade = cidadeCampo.getText().toString();
                String telefone = telefoneCampo.getText().toString();
                String email = emailCampo.getText().toString();

                Integer idSelecionado = tamanhoGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(idSelecionado);
                String tamanho = radioButton.getText().toString();

                ArrayList<String> cores = new ArrayList<>();

                for (int i = 0; i < linearL.getChildCount(); i++) {
                    CheckBox checkBox = (CheckBox) linearL.getChildAt(i);

                    if (checkBox.isChecked()) {
                        cores.add(checkBox.getText().toString());
                    }
                }


                Usuario usuario = new Usuario(nome, idade, uf, cidade, telefone, email, tamanho, cores);

                TextView result = findViewById(R.id.textVIewResult);
                String textResult = "Usuário registrado! \n" + usuario.toString();

                result.setText(textResult);

            }
        });
    }
}