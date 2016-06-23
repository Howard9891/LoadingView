package com.iyuba.myapplication.View;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private int count = 9;
    private Paint paint;
    private Context mContext;
    boolean isBg = true;
    int j = 0;
    private float angle;
    private int startColor;
    private int endColor;
    public LoadingView(Context context) {
        this(context, null);
    }
    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray typedArray =context.getTheme().obtainStyledAttributes(attrs,R.styleable.loadView,defStyleAttr,0);
        int n = typedArray.getIndexCount();
        for(int i=0;i<n;i++){
            int attr =typedArray.getIndex(i);
            switch (attr){
                case R.styleable.loadView_startColor:
                    startColor = typedArray.getColor(attr,Color.BLUE);
                    break;
                case R.styleable.loadView_endColor:
                    endColor   = typedArray.getColor(attr,Color.TRANSPARENT);
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();
        init();
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
        for (int i = 0; i < count; i++) {
            paint.setColor(ColorUtil.getNewColorByStartEndColor(mContext, j * 1.0f / count,startColor,endColor));
            canvas.drawCircle((float) (getWidth() / 2 + radius * Math.cos(i * angle)), (float) (getWidth() / 2 - radius * Math.sin(i * angle)), dot_radius, paint);
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
            isBg =false;
        }
    };
}

