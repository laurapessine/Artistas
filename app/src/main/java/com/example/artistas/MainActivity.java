package com.example.artistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtArtista = findViewById(R.id.txtArtista);
        EditText txtGenero = findViewById(R.id.txtGenero);
        Button btnAdiciona = findViewById(R.id.btnAdiciona);
        ListView lista = findViewById(R.id.lista);

        SQLiteDatabase bd = openOrCreateDatabase("artistasmusicas", MODE_PRIVATE, null);

        bd.execSQL("CREATE TABLE IF NOT EXISTS artistas(id integer PRIMARY KEY AUTOINCREMENT, nome varchar, genero varchar)");
        bd.execSQL("CREATE TABLE IF NOT EXISTS musicas(id integer PRIMARY KEY AUTOINCREMENT, id_artista integer, titulo varchar)");

        Cursor cursorArtistas = bd.rawQuery("SELECT _rowid_ _id, id, nome, genero FROM artistas", null);
        AdapterArtistas adapterArtistas = new AdapterArtistas(this, cursorArtistas);
        lista.setAdapter(adapterArtistas);

        btnAdiciona.setOnClickListener(view -> {
            String artista = txtArtista.getText().toString();
            String genero = txtGenero.getText().toString();

            bd.execSQL("INSERT INTO artistas(nome, genero) VALUES('" + artista + "', '" + genero + "')");

            txtArtista.setText("");
            txtGenero.setText("");

            adapterArtistas.changeCursor(bd.rawQuery("SELECT _rowid_ _id, id, nome, genero FROM artistas", null));
        });

        lista.setOnItemClickListener((adapterView, view, i, l) -> {
            Cursor cursor = (Cursor) adapterArtistas.getItem(i);
            Intent intent = new Intent(getApplicationContext(), MusicasActivity.class);
            intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            intent.putExtra("nome", cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            startActivity(intent);
        });
    }
}