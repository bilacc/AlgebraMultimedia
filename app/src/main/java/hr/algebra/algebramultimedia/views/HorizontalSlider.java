package hr.algebra.algebramultimedia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ProgressBar;

import hr.algebra.algebramultimedia.interfaces.OnProgressChangeListener;

/**
 * Created by Danijel on 8.2.2017..
 */

public class HorizontalSlider extends ProgressBar {

    private OnProgressChangeListener listener; //koristi naš interface, komunikacija između naše komponente i interfacea
                                                //ako horizontal slider zeli komunicirati s nasim activityem
    public HorizontalSlider(Context context) {
        super(context);
    }

    public HorizontalSlider(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.progressBarStyleHorizontal);
    }

    public HorizontalSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnProgressChangeListener(OnProgressChangeListener l) {
        listener = l;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            float x_mouse = event.getX();
            float width = getWidth();
            int progress = Math.round((float) getMax() * (x_mouse / width));
            if (progress < 0) {
                progress = 0;
            }
            if (progress > 100) {
                progress = 100;
            }
            this.setProgress(progress);
            if (listener != null) {
                listener.onProgressChanged(this, progress);
            }
        }
        return true;
    }


}


