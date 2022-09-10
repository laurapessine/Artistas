package com.example.artistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MusicasActivity extends AppCompatActivity {

    Cursor cursorMusicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas);

        TextView tvArtista = findViewById(R.id.textView3);
        EditText txtMusica = findViewById(R.id.txtMusica);
        Button btnAdicionaMusica = findViewById(R.id.btnAdicionaMusica);
        ListView listaMusicas = findViewById(R.id.listaMusicas);

        SQLiteDatabase bd = openOrCreateDatabase("artistasmusicas", MODE_PRIVATE, null);
        Intent intent = getIntent();
        int idArtista = intent.getIntExtra("id", 0);
        tvArtista.setText(intent.getStringExtra("nome"));

        cursorMusicas = bd.rawQuery("SELECT _rowid_ _id, titulo FROM musicas WHERE id_artista = " + idArtista, null);
        AdapterMusicas adapterMusicas = new AdapterMusicas(this, cursorMusicas);
        listaMusicas.setAdapter(adapterMusicas);

        btnAdicionaMusica.setOnClickListener(view -> {
            String musica = txtMusica.getText().toString();
            bd.execSQL("INSERT INTO musicas(id_artista, titulo) VALUES(" + idArtista + ", '" + musica + "')");
            txtMusica.setText("");
            cursorMusicas = bd.rawQuery("SELECT _rowid_ _id, titulo FROM musicas WHERE id_artista = " + idArtista, null);
            adapterMusicas.changeCursor(cursorMusicas);
        });
    }
}