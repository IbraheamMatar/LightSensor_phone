package com.example.lightsensorinphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor =null;
    Sensor sensor2;
    ConstraintLayout lym;
    MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       lym = findViewById(R.id.lymain);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) !=null ) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }else {
            Toast.makeText(this, "is not found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            int val = (int)sensorEvent.values[0];
            if(val>50){

            player = MediaPlayer.create(this,R.raw.morning);
            player.setVolume(100,100);
            player.start();
                lym.setBackgroundResource(R.drawable.morning);



            }else {

                player = MediaPlayer.create(this,R.raw.night);
                player.setVolume(100,100);
                player.start();
                lym.setBackgroundResource(R.drawable.night);
            }


        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);

    }

}