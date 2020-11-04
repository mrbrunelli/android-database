package com.example.aula_bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import com.example.aula_bancodedados.bancodedados.TbAluno;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    Button btnAdicionar;
    ArrayList<HashMap<String, String>> listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadAlunoActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscarAlunos();
    }

    private void buscarAlunos() {
        TbAluno tbAluno = new TbAluno(MainActivity.this);
        listaAlunos = tbAluno.buscar();
        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this,
                listaAlunos,
                R.layout.lista_modelo,
                new String[]{"nome", "ra", "cidade"},
                new int[]{R.id.lblNome, R.id.lblRa, R.id.lblCidade});
        setListAdapter(adapter);
    }
}