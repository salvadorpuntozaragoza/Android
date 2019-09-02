package com.example.boletos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCreateTicket(View view){
        Intent intent = new Intent(this, CreateTicketActivity.class);
        startActivity(intent);
    }

    public void onViewTickets(View view){
        Intent intent = new Intent(this, ViewTicketsActivity.class);
        startActivity(intent);
    }

    public void onCalculateTickets(View view){
        Intent intent = new Intent(this, ViewCountsActivity.class);
        startActivity(intent);
    }
}
