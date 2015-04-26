package com.hczdmr.Splash;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by HaciOzdemir on 26/04/15.
 */
public class Accelerate extends Activity implements SensorEventListener{
    Bitmap ball;
    float x,y,sensorX,sensorY;
    SensorManager sm;
    HaciOzdemirSurface ourSurfaceView;
    public class HaciOzdemirSurface extends SurfaceView implements Runnable{

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public HaciOzdemirSurface(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        public void pause(){
            isRunning = false;
            while (true){
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        public void resume(){
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while (isRunning){
                if(!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(02,02,150);
                float centerX = canvas.getWidth()/2;
                float centerY = canvas.getHeight()/2;
                canvas.drawBitmap(ball,centerX + sensorX*20,centerY + sensorY*20,null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() !=0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s , SensorManager.SENSOR_DELAY_NORMAL);
        }
        ball = BitmapFactory.decodeResource(getResources(),R.drawable.gball);
        x = y = sensorX = sensorY = 0;
        ourSurfaceView = new HaciOzdemirSurface(this);
        ourSurfaceView.resume();
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //values[0] x ekseni icin values[1] y , values[2] z ekseni icin
        sensorX = event.values[0];
        sensorY = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
