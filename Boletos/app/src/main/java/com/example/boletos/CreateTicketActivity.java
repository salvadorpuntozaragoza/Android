package com.example.boletos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);
    }

    public void onSaveTicket(View view){
        FechaHora date = new FechaHora();

        EditText ruta = (EditText)findViewById(R.id.textRuta);
        EditText precio = (EditText)findViewById(R.id.textPrecio);
        EditText boleto = (EditText)findViewById(R.id.textNumero);

        if(ruta.getText().toString().equals("") || precio.getText().toString().equals("") || boleto.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "Llena todos los campos!!!", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            String route = ruta.getText().toString();
            int price = Integer.parseInt(precio.getText().toString());
            int ticketNumber = Integer.parseInt(boleto.getText().toString());

            SQLiteOpenHelper helper = new TicketsDatabaseHelper(this);
            try{
                SQLiteDatabase db = helper.getWritableDatabase();
                insertTicket(db, route, price, date.getDia(), date.getMonth(), date.getAnio(), ticketNumber);
                db.close();

                ruta.setText("");
                precio.setText("");
                boleto.setText("");

                Toast toast = Toast.makeText(this, "Boleto guardado", Toast.LENGTH_SHORT);
                toast.show();
            }catch (SQLiteException e){
                Toast toast = Toast.makeText(this, "Base de datos no disponible", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void insertTicket(SQLiteDatabase db, String ruta, int precio, int dia, int mes, int anio, int boleto){
        ContentValues ticketValues = new ContentValues();

        ticketValues.put("RUTA", ruta);
        ticketValues.put("PRECIO", precio);
        ticketValues.put("DIA", dia);
        ticketValues.put("MES", mes);
        ticketValues.put("ANIO", anio);
        ticketValues.put("BOLETO", boleto);

        db.insert("TICKETS", null, ticketValues);
    }
}
