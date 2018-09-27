package hr.algebra.algebramultimedia.views;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by online4 on 10.2.2017..
 */

public class DotInFragmentView extends View implements View.OnDragListener {

    private static View.OnLongClickListener lcListener = new View.OnLongClickListener() {

        private boolean mDragInProgress;

        @Override
        public boolean onLongClick(View view) {
            ClipData data = ClipData.newPlainText("DragData", (String) view.getTag());
            mDragInProgress = view.startDrag(data, new View.DragShadowBuilder(view), (Object) view, 0);
            return true;
        }
    };
    private Paint mNormalPaint;
    private Paint mDraggingPaint;
    private int mColor = Color.RED;
    private int mRadius = 20;
    private boolean inDrag;

    public DotInFragmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mNormalPaint = new Paint();
        mNormalPaint.setColor(mColor);
        mNormalPaint.setAntiAlias(true);
        mDraggingPaint = new Paint();
        mDraggingPaint.setColor(Color.MAGENTA);
        mDraggingPaint.setAntiAlias(true);
        setOnLongClickListener(lcListener);
        setOnDragListener(this);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float cx = this.getWidth() / 2 + getLeftPaddingOffset();
        float cy = this.getHeight() / 2 + getTopPaddingOffset();
        Paint paint = new Paint();
        if (inDrag) {
            paint = mDraggingPaint;
        }
        canvas.drawCircle(cx, cy, mRadius, paint);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = 2 * mRadius + getPaddingLeft() + getPaddingRight();
        setMeasuredDimension(size, size);
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        if (dragEvent.getLocalState() != this) {
            return false;
        }
        boolean result = true;
        int action = dragEvent.getAction();
        float x = dragEvent.getX();
        float y = dragEvent.getY();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                inDrag = true;
            case DragEvent.ACTION_DRAG_LOCATION:
            case DragEvent.ACTION_DRAG_ENTERED:
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                result = false;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                inDrag = false;
                break;
            default:
                result = false;
                break;
        }

        return result;
    }
}
