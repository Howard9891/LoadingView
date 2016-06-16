package com.iyuba.myapplication.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.iyuba.myapplication.ColorUtil;
import com.iyuba.myapplication.R;

/**
 * 作者：mingyu on 16-6-16 09:33
 * <p/>
 * 邮箱：Howard9891@163.com
 */
public class LoadingView extends View {
    private int count = 12;
    private Paint paint;
    private Context mContext;
    int j = 0;
    private float angle;
    public LoadingView(Context context) {
        this(context, null);
    }
    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        angle = (float) (2 * Math.PI / count);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Point center = new Point(getHeight() / 2, getWidth() / 2);
        int sradius = 6;
        int radius = getWidth() / 2 - sradius;
        for (int i = 0; i < count; i++) {
            paint.setColor(ColorUtil.getNewColorByStartEndColor(mContext, j * 1.0f / count,R.color.blue,R.color.translant));
            canvas.drawCircle((float) (getWidth() / 2 + radius * Math.cos(i * angle)), (float) (getWidth() / 2 - radius * Math.sin(i * angle)), 6, paint);
            j++;
            j =j%count;
        }
        j++;
        handler.sendEmptyMessageDelayed(0,100);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };
}

