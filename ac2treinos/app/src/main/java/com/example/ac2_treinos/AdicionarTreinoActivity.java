package com.example.ac2_treinos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ac2_treinos.db.BancoHelper;

public class AdicionarTreinoActivity extends AppCompatActivity {
    BancoHelper databaseHelper = new BancoHelper(this);

    MainActivity mainActivity = new MainActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_treino);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = findViewById(R.id.btnSalvar);
        EditText edtNome = findViewById(R.id.inputNome);
        EditText edtDuracao = findViewById(R.id.inputDuracao);
        EditText edtExecucao = findViewById(R.id.inputExecucao);


        button.setOnClickListener(v -> {
            String nome = edtNome.getText().toString();
            String duracao = edtDuracao.getText().toString();
            String execucao = edtExecucao.getText().toString();

            if (!nome.isEmpty() && !duracao.isEmpty() && !execucao.isEmpty()) {
                long resultado = databaseHelper.inserirExercicio(nome, Integer.parseInt(duracao), execucao);

                if (resultado != -1) {
                    Toast.makeText(this, "Treino salvo!", Toast.LENGTH_SHORT).show();
                    edtNome.setText("");
                    edtDuracao.setText("");
                    edtExecucao.setText("");

                    mainActivity.carregarExercicios();

                } else {
                    Toast.makeText(this, "Erro ao salvar treino!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });



    }


}