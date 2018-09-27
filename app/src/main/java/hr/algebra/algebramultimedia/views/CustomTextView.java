package hr.algebra.algebramultimedia.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Danijel on 9.2.2017..
 */

public class CustomTextView extends View {

    public CustomTextView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(100);
        canvas.drawText("Android", 100, 100, paint);
        Path path = new Path();
        path.addArc(new RectF(0, 200, 550, 600), 230, 90);
        paint.setColor(Color.CYAN);
        canvas.drawTextOnPath("Android", path, 0, 0, paint);
        float[] pos = new float[]{
                100, 350,
                170, 390,
                220, 350,
                280, 390,
                320, 350,
                375, 390,
                400, 350};
        paint.setColor(Color.GREEN);
        canvas.drawPosText("Android", pos, paint);
    }


}
