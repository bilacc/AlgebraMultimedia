package hr.algebra.algebramultimedia.interfaces;

import android.graphics.Canvas;

public interface Transformation {
    void transform(Canvas canvas);

    String describe();
}
