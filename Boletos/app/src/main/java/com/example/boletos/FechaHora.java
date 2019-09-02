package com.example.boletos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class FechaHora {

    private String[] meses;
    private int dia;
    private int anio;
    private int month;
    private String mes;

    public FechaHora(){
        init();
    }

    public void init(){
        meses = new String[12];
        meses[0] = "Enero";
        meses[1] = "Febrero";
        meses[2] = "Marzo";
        meses[3] = "Abril";
        meses[4] = "Mayo";
        meses[5] = "Junio";
        meses[6] = "Julio";
        meses[7] = "Agosto";
        meses[8] = "Septiembre";
        meses[9] = "Octubre";
        meses[10] = "Noviembre";
        meses[11] = "Diciembre";

        anio = Calendar.getInstance().get(Calendar.YEAR);
        mes = meses[Calendar.getInstance().get(Calendar.MONTH)];
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public int indexOf(String mes){
        int i = 0;

        while(!meses[i].equals(mes)) i++;

        return (i + 1);
    }

    public int getAnio() {
        return anio;
    }

    public int getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    } //String

    public int getMonth(){ return month; } //Int


}
