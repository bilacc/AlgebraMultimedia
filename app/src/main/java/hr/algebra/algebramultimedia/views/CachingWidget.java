package hr.algebra.algebramultimedia.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import hr.algebra.algebramultimedia.R;

/**
 * Created by Danijel on 10.2.2017..
 */

public class CachingWidget extends View {

    private Bitmap mCache;
    private Paint mPaint;

    public CachingWidget(Context context) {
        super(context);
        setMinimumWidth(200);
        setMinimumHeight(200);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
    }

    public void invalidateCache() {
        mCache = null;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null == mCache) {
            mCache = Bitmap.createBitmap(
                    getMeasuredWidth(),
                    getMeasuredHeight(),
                    Bitmap.Config.ARGB_8888);
            drawCachedBitmap(new Canvas(mCache));
        }

        canvas.drawBitmap(mCache, 0, 0, new Paint());
        canvas.drawLine(0, 0, mCache.getWidth(), 0, mPaint);
        canvas.drawLine(0, 0, 0, mCache.getHeight(), mPaint);
        canvas.drawLine(0, mCache.getHeight(), mCache.getWidth(), mCache.getHeight(), mPaint);
        canvas.drawLine(mCache.getWidth(), 0, mCache.getWidth(), mCache.getHeight(), mPaint);
        canvas.drawLine(0, 0, mCache.getWidth(), mCache.getHeight(), mPaint);
    }

    private void drawCachedBitmap(Canvas canvas) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(image, 0, 0, new Paint());
    }
}
