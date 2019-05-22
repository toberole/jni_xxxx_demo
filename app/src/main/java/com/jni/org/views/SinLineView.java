package com.jni.org.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jni.org.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class SinLineView extends View {
    public static final String TAG = SinLineView.class.getSimpleName();

    private Context ctx;

    private Paint paint;

    private List<Path> paths = new ArrayList<>();

    private int h_line = 2;
    private float h_sin = 1.5f;
    private int fineness = 1;
    private int amplitude = 1;
    private int volume;

    public SinLineView(Context context) {
        this(context, null);
    }

    public SinLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SinLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ctx = context;
        h_line = DisplayUtil.dip2px(ctx, h_line);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(h_line);
        paint.setColor(Color.parseColor("#FF0000"));

        for (int i = 0; i < 20; i++) {
            paths.add(new Path());
        }
    }

    private int width;
    private int height;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        Log.i(TAG, "onDraw width: " + width + " height: " + height);

        drawHorizontalLine(canvas);
        drawSinLine(canvas);
    }

    private void drawHorizontalLine(Canvas canvas) {
        canvas.drawLine(0, height / 2 - h_line / 2, width, height / 2 + h_line / 2, paint);
    }

    private void drawSinLine(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DisplayUtil.dip2px(ctx, 2));
        paint.setColor(Color.parseColor("#00FF00"));

        for (int i = 0; i < paths.size(); i++) {
            paths.get(i).reset();
            paths.get(i).moveTo(0, height / 2);
        }

        int dex = 100;
        int period = 360;
        double PI_I = Math.PI * 2 / 360;
        for (float i = 0; i < period; i++) {
            float sin = (float) Math.sin(i * PI_I);
            for (int j = 0; j < paths.size(); j++) {
                Path path = paths.get(j);
                float x1 = 1.0f * i * width / period;
                float dexY = dex * sin;
                float y1 = height / 2 + dexY;
                path.lineTo(x1, y1);
            }
        }

        for (int n = 0; n < paths.size(); n++) {
            if (n == paths.size() - 1) {
                paint.setAlpha(255);
            }
            canvas.drawPath(paths.get(n), paint);
        }
    }
}
