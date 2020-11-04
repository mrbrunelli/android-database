package com.example.aula_bancodedados.bancodedados;

import android.content.Context;
import android.database.Cursor;
import com.example.aula_bancodedados.classes.Aluno;

import java.util.ArrayList;

public class TbAluno {
    public TbAluno(Context context) {
        BancoDeDados.getInstance().abrirBanco(context);
        String sql = "" +
                "CREATE TABLE IF NOT EXISTS tbAluno (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "ra TEXT, " +
                "cidade TEXT)";
        BancoDeDados.getInstance().execSql(sql);
    }

    public void salvar(Aluno aluno) {
        if (aluno.id > 0) {
            alterar(aluno);
        } else {
            inserir(aluno);
        }
    }

    private void inserir(Aluno aluno) {
        String sql = "INSERT INTO tbAluno (nome, ra, cidade) VALUES (" +
                addAspas(aluno.nome) + ", " +
                addAspas(aluno.ra) + ", " +
                addAspas(aluno.cidade) + ", " +
                ")";
        BancoDeDados.getInstance().execSql(sql);
    }

    private void alterar(Aluno aluno) {
        String sql = "UPDATE tbAluno SET " +
                "nome = " + addAspas(aluno.nome) + ", " +
                "ra = " + addAspas(aluno.ra) + ", " +
                "cidade = " + addAspas(aluno.cidade) +
                "WHERE id = " + aluno.id;
        BancoDeDados.getInstance().execSql(sql);
    }

    public void excluir(Aluno aluno) {
        String sql = "DELETE FROM tbAluno WHERE id = " + aluno.id;
        BancoDeDados.getInstance().execSql(sql);
    }

    public ArrayList<Aluno> buscar() {
        /* Cursor cursor = BancoDeDados.getInstance().getDb().query(
          "tbAluno",
                new String[] {"id", "nome", "ra", "cidade"},
                "",
                null,
                null,
                null,
                "nome",
                null
        ); */
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        Cursor cursor = BancoDeDados.getInstance().execBusca("SELECT * FROM tbAluno ORDER BY nome");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int indiceCampoId = cursor.getColumnIndex("id");
            int indiceCampoNome = cursor.getColumnIndex("nome");
            int indiceCampoRa = cursor.getColumnIndex("ra");
            int indiceCampoCidade = cursor.getColumnIndex("cidade");
            for (int i = 0; i < cursor.getCount(); i++) {
                Aluno aluno = new Aluno();
                aluno.id = cursor.getInt(indiceCampoId);
                aluno.nome = cursor.getString(indiceCampoNome);
                aluno.ra = cursor.getString(indiceCampoRa);
                aluno.cidade = cursor.getString(indiceCampoCidade);
                listaAlunos.add(aluno);
                cursor.moveToNext();
            }
        }
        return listaAlunos;
    }

    private String addAspas(String valor) {
        return "'" + valor + "'";
    }
}
