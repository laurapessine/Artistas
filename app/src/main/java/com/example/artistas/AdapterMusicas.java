package com.example.artistas;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AdapterMusicas extends CursorAdapter {
    public AdapterMusicas(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemLista = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return itemLista;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text1 = view.findViewById(android.R.id.text1);
        String musica = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
        text1.setText(musica);
    }
}
