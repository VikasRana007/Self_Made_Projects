package com.me.customviews.my_own_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private static final int SQUARE_SIZE = 400;
    private Rect rect;
    private Paint paint;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    public void swapColor(){
        paint.setColor(paint.getColor() == Color.BLUE ? Color.CYAN : Color.BLUE);
//        invalidate();  this is synchronous call so it may block the UI
        postInvalidate(); // it will just notify the custom View to update the view using invoke the onDraw() function when it can
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//            canvas.drawColor(Color.GREEN);
        // draw rect
        rect.left = 150;
        rect.top = 150;
        rect.right = rect.left + SQUARE_SIZE;
        rect.bottom = rect.top+ SQUARE_SIZE;
        canvas.drawRect(rect,paint );
    }
}
