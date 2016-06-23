package com.iyuba.myapplication.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.iyuba.myapplication.ColorUtil;
import com.iyuba.myapplication.R;

/**
 * 作者：mingyu on 16-6-23 13:54
 * <p/>
 * 邮箱：Howard9891@163.com
 */
public class ColorLoadingView extends View {

    private int count = 9;
    private Paint paint;
    private Context mContext;
    boolean isBg = true;
    int j = 0;
    private float angle;
    private int[] colors = new int[]{Color.RED,Color.GREEN,Color.CYAN,Color.RED,Color.GREEN,Color.CYAN,Color.RED,Color.GREEN,Color.CYAN};
    public ColorLoadingView(Context context) {
        this(context, null);
    }
    public ColorLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ColorLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int dot_radius = 6;
        int radius = getWidth() / 2 - dot_radius;
        if(isBg){
            for(int i=0;i<count;i++){
                paint.setColor(Color.GRAY);
                canvas.drawCircle((float) (getWidth() / 2 + radius * Math.cos(i * angle)), (float) (getWidth() / 2 - radius * Math.sin(i * angle)), dot_radius, paint);
            }

        }else{
            for(int i=0;i<count;i++){
                if(i==j){
                    paint.setColor(colors[j]);
                }else{
                    paint.setColor(Color.GRAY);
                }
                canvas.drawCircle((float) (getWidth() / 2 + radius * Math.cos(i * angle)), (float) (getWidth() / 2 - radius * Math.sin(i * angle)), dot_radius, paint);
            }
        }
        j++;
        j = j%count;
        handler.sendEmptyMessageDelayed(0,100);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
            isBg =false;
        }
    };
}
