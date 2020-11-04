package com.example.aula_bancodedados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aula_bancodedados.bancodedados.TbAluno;
import com.example.aula_bancodedados.classes.Aluno;

public class CadAlunoActivity extends AppCompatActivity {

    EditText edtNome, edtRa, edtCidade;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_aluno);
        edtNome = findViewById(R.id.edtNome);
        edtRa = findViewById(R.id.edtRa);
        edtCidade = findViewById(R.id.edtCidade);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNome.getText().toString().isEmpty()) {
                    mostrarMensagem("Campo nome obrigatorio");
                    return;
                }
                if (edtRa.getText().toString().isEmpty()) {
                    mostrarMensagem("Campo RA obrigatorio");
                    return;
                }
                if (edtCidade.getText().toString().isEmpty()) {
                    mostrarMensagem("Campo cidade obrigatorio");
                    return;
                }
                try {
                    Aluno aluno = new Aluno();
                    aluno.id = 0;
                    aluno.nome = edtNome.getText().toString();
                    aluno.ra = edtRa.getText().toString();
                    aluno.cidade = edtCidade.getText().toString();
                    TbAluno tbAluno = new TbAluno(CadAlunoActivity.this);
                    tbAluno.salvar(aluno);
                    onBackPressed();
                } catch (Exception e) {
                    mostrarMensagem("Erro ao cadastrar o aluno" + e.getMessage());
                }
            }
        });
    }

    private void mostrarMensagem(String texto) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(CadAlunoActivity.this);
        alerta.setTitle("Atençao");
        alerta.setMessage(texto);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }
}