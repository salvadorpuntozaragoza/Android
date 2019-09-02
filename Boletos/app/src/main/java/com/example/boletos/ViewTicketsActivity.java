package com.example.boletos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewTicketsActivity extends AppCompatActivity {

    private Cursor cursor;
    private SQLiteDatabase db;
    private FechaHora date = new FechaHora();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tickets);

        ListView listRutas = (ListView)findViewById(R.id.list_rutas);
        SQLiteOpenHelper helper = new TicketsDatabaseHelper(this);
        try{
            db = helper.getReadableDatabase();
            cursor = db.query("TICKETS", new String[] {"_id", "RUTA", "PRECIO", "DIA", "MES", "ANIO", "BOLETO"},null, null, null, null, null);
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item_custom1, cursor,
                    new String[] {"RUTA", "PRECIO", "DIA", "MES", "ANIO", "BOLETO"},
                    new int[] {R.id.ruta, R.id.precio, R.id.dia, R.id.mes, R.id.anio, R.id.codigo},
                    0);
            listRutas.setAdapter(adapter);
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Error al conectarse a la DB", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onFilterTickets(View view){
        //db.close();
        //cursor.close();

        Spinner day = (Spinner)findViewById(R.id.spinnerDias);
        Spinner month = (Spinner)findViewById(R.id.spinnerMeses);
        Spinner year = (Spinner)findViewById(R.id.spinnerAnios);

        int mes = date.indexOf(String.valueOf(month.getSelectedItem()));

        System.out.println(String.valueOf(day.getSelectedItem()));
        System.out.println(String.valueOf(month.getSelectedItem()));
        System.out.println(String.valueOf(year.getSelectedItem()));

        ListView listRutas = (ListView)findViewById(R.id.list_rutas);
        SQLiteOpenHelper helper = new TicketsDatabaseHelper(this);
        try{
            //db = helper.getReadableDatabase();
            cursor = db.query("TICKETS", new String[] {"_id", "RUTA", "PRECIO", "DIA", "MES", "ANIO", "BOLETO"}, "DIA=? AND MES=? AND ANIO=?",
                    new String[] {String.valueOf(day.getSelectedItem()), Integer.toString(mes), String.valueOf(year.getSelectedItem())}, null, null, null);
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item_custom1, cursor,
                    new String[] {"RUTA", "PRECIO", "DIA", "MES", "ANIO", "BOLETO"},
                    new int[] {R.id.ruta, R.id.precio, R.id.dia, R.id.mes, R.id.anio, R.id.codigo},
                    0);
            listRutas.setAdapter(adapter);
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Error al conectarse a la DB", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }
}
