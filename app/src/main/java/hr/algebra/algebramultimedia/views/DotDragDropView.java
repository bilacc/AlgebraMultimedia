package hr.algebra.algebramultimedia.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import hr.algebra.algebramultimedia.activities.MainActivity;

/**
 * Created by Danijel on 10.2.2017..
 */

public class DotDragDropView extends View {

    private static final String TAG = "TouchDrag";
    private float left = 0;
    private float top = 0;
    private float radius = 20;
    private float offsetX;
    private float offsetY;
    private Paint mPaint;
    private Context mContext;

    public DotDragDropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(left + radius, top + radius, radius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float eventX = event.getX();
        float eventY = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!(left - 20 < eventX && eventX < left + radius * 2 + 20 &&
                        top - 20 < eventY && eventY < top + radius * 2 + 20)) {
                    return false;
                }
                offsetX = eventX - left;
                offsetY = eventY - top;
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                left = eventX - offsetX;
                top = eventY - offsetY;
                if (action == MotionEvent.ACTION_UP) {
                    checkDrop(eventX, eventY);
                }
                break;
        }
        invalidate();
        return true;
    }

    private void checkDrop(float x, float y) {
        Log.v(TAG, "provjera drop cilja za " + x + ", " + y);
        int viewCount = MainActivity.counterLayout.getChildCount();
        for (int i = 0; i < viewCount; i++) {
            View view = MainActivity.counterLayout.getChildAt(i);
            Log.v(TAG, "je li to drop desno od " + (view.getLeft() - 20));
            Log.v(TAG, " i vertikalno između " + (view.getTop() - 20) + " i " + (view.getBottom() + 20) + "?");
            if (x > view.getLeft() - 20 && view.getTop() - 20 < y && y < view.getBottom() + 20) {
                Log.v(TAG, "Da, tako je.");
                int count = Integer.parseInt(((TextView) view).getText().toString());
                ((TextView) view).setText(String.valueOf(++count));
                left = top = 0;
                break;
            }
        }
    }


}
