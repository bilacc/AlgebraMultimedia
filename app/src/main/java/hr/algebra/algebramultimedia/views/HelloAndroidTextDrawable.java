package hr.algebra.algebramultimedia.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class HelloAndroidTextDrawable extends Drawable {

    private ColorFilter mColorFilter;
    private int mOpacity;

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColorFilter(mColorFilter);
        paint.setAlpha(mOpacity);
        paint.setTextSize(30);
        paint.setColor(Color.GRAY);
        canvas.drawText("Hello", 40, 55, paint);
        paint.setTextSize(50);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("Android", 35, 100, paint);
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}

