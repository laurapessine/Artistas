package com.example.artistas;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

public class AdapterArtistas extends CursorAdapter {

    public AdapterArtistas(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.item_lista, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvNomeArtista = view.findViewById(R.id.tvNomeArtista);
        TextView tvGeneroMusical = view.findViewById(R.id.tvGeneroMusical);

        String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
        String genero = cursor.getString(cursor.getColumnIndexOrThrow("genero"));

        tvNomeArtista.setText(nome);
        tvGeneroMusical.setText(genero);
    }
}
