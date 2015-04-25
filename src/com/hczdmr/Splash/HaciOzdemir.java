package com.hczdmr.Splash;

import android.content.Context;
import android.graphics.*;
import android.view.View;

/**
 * Created by HaciOzdemir on 12/04/15.
 */
public class HaciOzdemir extends View{
    Bitmap greenBall;
    float changingY;
    Typeface font;
    public HaciOzdemir(Context context) {
        super(context);
        greenBall = BitmapFactory.decodeResource(getResources(),R.drawable.gball);
        changingY =0;
        font = Typeface.createFromAsset(context.getAssets(),"G-Unit.TTF");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint textPaint = new Paint();
        textPaint.setARGB(50,254,10,50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(75);
        textPaint.setTypeface(font);
        canvas.drawText("Haci OZDEMIR",(canvas.getWidth())/2,600,textPaint);

        canvas.drawBitmap(greenBall,(canvas.getWidth())/2,changingY,null);
        if(changingY<canvas.getHeight()){
            changingY+=10;
        }else {
            changingY=0;
        }
        Rect middleRect = new Rect();
        middleRect.set(0,1200,canvas.getWidth(),1350);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect,ourBlue);
        invalidate();
    }
}
