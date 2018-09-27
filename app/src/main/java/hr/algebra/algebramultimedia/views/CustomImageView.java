package hr.algebra.algebramultimedia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomImageView extends ImageView {

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 996;
        int desiredHeight = 924;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int desiredSize;
//        float aspect;
//
//        Drawable d = getDrawable();
//
//        if (d == null) {
//            desiredSize = 0;
//            aspect = 1f;
//        } else {
//            desiredSize = d.getIntrinsicWidth();
//            aspect = (float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight();
//        }
//
//        int widthSize = View.resolveSize(desiredSize, widthMeasureSpec);
//        int heightSize = (int) (widthSize / aspect);
//        int specMode = MeasureSpec.getMode(heightMeasureSpec);
//        int specSize = MeasureSpec.getSize(heightMeasureSpec);
//        if (specMode == MeasureSpec.AT_MOST || specMode == MeasureSpec.EXACTLY) {
//            if (heightSize > specSize) {
//                heightSize = specSize;
//                widthSize = (int) (heightSize * aspect);
//            }
//        }
//
//        setMeasuredDimension(widthSize, heightSize);
//    }


}