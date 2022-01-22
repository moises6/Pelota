package com.example.pelota;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


    public class MiPelota extends View implements SensorEventListener {
    Paint pincel = new Paint();
    int alto, ancho;
    int tamanio=55;
    int borde=50;
    float ejeX=0,ejeY=0,ejez=0;
    String x,y,z;

    public MiPelota(Context interfaz) {
        super(interfaz);
        SensorManager smAdministrador = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snsRotacion = smAdministrador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smAdministrador.registerListener (this,snsRotacion, SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho = pantalla.getWidth();
        alto = pantalla.getHeight();

    }

    @Override
    public void onSensorChanged(SensorEvent cambio) {
        ejeX-=cambio.values [0];
        x=Float.toString(ejeX);
        if (ejeX < (tamanio+borde)) {
            ejeX = (tamanio+borde);
        }
        else if (ejeX > (ancho-(tamanio+borde))){
            ejeX = ancho-(tamanio+borde);
        }
        ejeY+=cambio.values[0];
        y=Float.toString(ejeY);
        if (ejeY < (tamanio+borde)){
            ejeY = (tamanio+borde);
        }
        else if (ejeY > (alto-tamanio-500)){
            ejeY = alto-tamanio-500;
        }
        ejez=cambio.values[2];
        z=String.format("%.2f",ejez);
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public void onDraw(Canvas lienzo) {
        pincel.setColor(Color.RED);
        lienzo.drawCircle(ejeX, ejeY, ejez+tamanio, pincel);
        pincel.setColor(Color.WHITE);
        pincel.setTextSize(24);
        lienzo.drawText("Moises", ejeX-30, ejeY+5, pincel);


    }
}
