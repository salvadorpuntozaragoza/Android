package com.example.boletos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ViewCountsActivity extends AppCompatActivity {

    Cursor cursor;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_counts);
    }

    public void onDisplayCount(View view){

        FechaHora date = new FechaHora();
        Spinner startMont = (Spinner)findViewById(R.id.startMonth);
        Spinner startYeay = (Spinner)findViewById(R.id.startYear);

        TextView dinero = (TextView)findViewById(R.id.textCount);
        int money = 0;

        try {
            SQLiteOpenHelper helper = new TicketsDatabaseHelper(this);
            db = helper.getReadableDatabase();
            cursor = db.query("TICKETS", new String[]{"PRECIO"}, "MES = ? AND ANIO = ?",
                    new String[]{String.valueOf(date.indexOf(String.valueOf(startMont.getSelectedItem()))),
                            String.valueOf(startYeay.getSelectedItem())},
                    null, null, null);
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Error el leer los datos de la DB", Toast.LENGTH_SHORT);
            toast.show();
        }

        System.out.println(money);
        if(cursor.moveToFirst()) {
            System.out.println("Movido al first");
            money += cursor.getInt(0);
            while (cursor.moveToNext()) {
                System.out.println("Dentro del bucle");
                money += cursor.getInt(0);
            }
        }

        dinero.setText(Integer.toString(money));
        cursor.close();
        db.close();
    }
}
