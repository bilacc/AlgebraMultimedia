package hr.algebra.algebramultimedia.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by online4 on 8.2.2017..
 */

public class CustomLinesView extends View {

    public CustomLinesView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(6);
        canvas.drawLine(100, 0, 100, 200, paint);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        canvas.drawLine(200, 0, 200, 200, paint);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(20);

        for (int y = 30, alpha = 255; alpha > 2; alpha >>= 1, y += 30) {
            paint.setAlpha(alpha);
            canvas.drawLine(40, y, 300, y, paint);
        }

    }
}
