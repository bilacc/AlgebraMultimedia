package hr.algebra.algebramultimedia.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import hr.algebra.algebramultimedia.interfaces.Transformation;

/**
 * Created by Danijel on 10.2.2017..
 */

public class TransformedViewWidget extends View {

    private final Transformation mTransformation;
    private final Drawable mDrawable;

    public TransformedViewWidget(Context context, Transformation xForm) {
        super(context);
        mTransformation = xForm;
        mDrawable = null;
        setMinimumWidth(230);
        setMinimumHeight(190);
    }

    public TransformedViewWidget(Context context, Transformation xForm, Drawable drawable) {
        super(context);
        mDrawable = drawable;
        mTransformation = xForm;
        setMinimumWidth(230);
        setMinimumHeight(190);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.save();
        mTransformation.transform(canvas);
        Paint paint = new Paint();

        if (mDrawable == null) {
            paint.setTextSize(30);
            paint.setColor(Color.GREEN);
            canvas.drawText("Hello", 40, 55, paint);
            paint.setTextSize(50);
            paint.setColor(Color.RED);
            canvas.drawText("Android", 35, 100, paint);
            canvas.restore();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            Rect rect = canvas.getClipBounds();
            canvas.drawRect(rect, paint);
            paint.setTextSize(14);
            paint.setColor(Color.BLUE);
            canvas.drawText(mTransformation.describe(), 4, 180, paint);
        } else {
            mDrawable.draw(canvas);
            canvas.restore();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            Rect rect = canvas.getClipBounds();
            canvas.drawRect(rect, paint);
            paint.setTextSize(14);
            paint.setColor(Color.BLUE);
            canvas.drawText(mTransformation.describe(), 4, getMeasuredHeight() - 4, paint);
        }
    }
}
