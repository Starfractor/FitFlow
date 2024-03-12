package com.example.fitflow;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


import android.app.NotificationChannel;

public class stepService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    private int sensorType = Sensor.TYPE_STEP_COUNTER;
    private boolean emulator = true;
    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(emulator){
            sensorType = Sensor.TYPE_GYROSCOPE;
        }
        sensor = sensorManager.getDefaultSensor(sensorType);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Step Service Channel";
            String description = "Step Service";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        startForeground(1, new NotificationCompat.Builder(this, "1")
                .setContentTitle("Service")
                .setContentText("step Data")
                .build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == sensorType) {
            if(Sensor.TYPE_GYROSCOPE == sensorType){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                float totalRotation = Math.abs(x) + Math.abs(y) + Math.abs(z);
                if (totalRotation > 0.25) {
                    //Emulate steps with gyroscope changes
                    activeLog.userInfo.steps++;
                    activeLog.userInfo.saveInfo(this);
                    Log.e("Steps", "Steps: " + activeLog.userInfo.steps);
                }
            }else{
                activeLog.userInfo.steps++;
                activeLog.userInfo.saveInfo(this);
                Log.e("Steps", "Steps: " + activeLog.userInfo.steps);
            }
    }}

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}
