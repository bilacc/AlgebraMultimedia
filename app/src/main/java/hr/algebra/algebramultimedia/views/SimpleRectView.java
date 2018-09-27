package hr.algebra.algebramultimedia.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

/**
 * Created by Danijel on 8.2.2017..
 */

public class SimpleRectView extends View {

    private ShapeDrawable mDrawable;

    public SimpleRectView(Context context) {
        super(context);
        setFocusable(true);
        mDrawable = new ShapeDrawable(new RectShape());
        mDrawable.getPaint().setColor(0xFFFF0000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x = 10;
        int y = 10;
        int width = 300;
        int height = 50;
        mDrawable.setBounds(x, y, x + width, y + height);
        mDrawable.draw(canvas);
    }
}
