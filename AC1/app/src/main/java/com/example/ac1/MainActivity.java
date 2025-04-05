package com.example.ac1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ac1.db.SQLHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTitulo, editAno, editNota, editGenero, editVisualizacao;
    private Button btnVisualizar;
    private SQLHelper sqlHelper =  new SQLHelper(this);
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listaFilmes;
    private ArrayList<Integer> listaIds;


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

        Button registrarFilme = findViewById(R.id.adicionarButton);

        registrarFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FilmeFormActivity.class);
                startActivity(intent);
            }
        });

//        sqlHelper.inserirFilme("filme", 2025, 5, "Ação", false);
        carregarFilmes();

    }

    public void carregarFilmes() {
        ListView listViewFilmes = findViewById(R.id.listView1);
        Cursor cursor = sqlHelper.listarFilmes();
        listaFilmes = new ArrayList<>();
        listaIds = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
            Integer id = cursor.getInt(0);
            String titulo = cursor.getString(1);
            Integer ano = cursor.getInt(2);
            Double nota = cursor.getDouble(3);
            String genero = cursor.getString(4);
            boolean visualizacao = cursor.getInt(5) != 0;

            String assistiu = "Não assistido";

            if (visualizacao) {
            assistiu = "Assistido";
            }

            listaFilmes.add(id + " - " + titulo + " - " + ano + " - " + nota + " - " + genero + " - "
            + assistiu + ".");
            listaIds.add(id);
            } while (cursor.moveToNext());

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaFilmes);
            listViewFilmes.setAdapter(adapter);

            listViewFilmes.setOnItemLongClickListener((parent, view, position, id) -> {
                int filmeId = listaIds.get(position);

                int deletado = sqlHelper.excluirFilme(filmeId);
                if (deletado > 0) {
                Toast.makeText(this, "Filme excluído!", Toast.LENGTH_SHORT).show();
                carregarFilmes();
                } else {
                    Toast.makeText(this, "Erro ao excluir filme!", Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            listViewFilmes.setOnItemClickListener((parent, view, position, id) -> {
                int filmeId = listaIds.get(position);

                Toast.makeText(this, "Edição!", Toast.LENGTH_LONG).show();
            });
        }
    }
}









