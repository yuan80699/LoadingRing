package cn.com.loadingring.view;

import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import cn.com.loadingring.R;
import cn.com.loadingring.utils.ToolsUtils;

public class LoadingRingView extends View {
	/** 圆环上的各点 */
	private List<Point> points = new LinkedList<Point>();
	/** 画笔 */
	private Paint paint = new Paint();
	/** 圆环半径 */
	private float radius = 200;
	/** 圆环的宽度 */
	private float ringWidth = 5;
	/** 指针宽度 */
	private float pointerWidth = 5;
	/** 背景色 */
	private int backgroundColor = Color.WHITE;
	/** 圆环的颜色 */
	private int ringColor = Color.BLUE;
	/** 指针的颜色 */
	private int pointerColor = Color.BLUE;
	/**
	 * 指针选中速度，越小越快
	 */
	private int speed = 10;
	/** 是否旋转 */
	private boolean running = true;
	/** 圆心横轴坐标 */
	private float centerX = 202.5f;
	/** 圆心纵轴坐标 */
	private float centerY = 202.5f;
	/** 当前旋转角度 */
	private int degree = 0;
	
	private TestThread thread;

	public LoadingRingView(Context context) {
		super(context);
		
		init();
	}
	
	public LoadingRingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initParameter(context, attrs);
		init();
	}

	public LoadingRingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		initParameter(context, attrs);
		init();
	}
	/**
	 * 初始化参数
	 * @param context
	 * @param attrs
	 */
	@SuppressLint("Recycle")
	private void initParameter(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingRing);

    	radius = typedArray.getDimension(R.styleable.LoadingRing_radius, radius);
    	ringWidth = typedArray.getDimension(R.styleable.LoadingRing_ringWidth, ringWidth);
    	pointerWidth = typedArray.getDimension(R.styleable.LoadingRing_pointerWidth, pointerWidth);
    	backgroundColor = typedArray.getColor(R.styleable.LoadingRing_backgroundColor, backgroundColor);
    	ringColor = typedArray.getColor(R.styleable.LoadingRing_ringColor, ringColor);
    	pointerColor = typedArray.getColor(R.styleable.LoadingRing_pointerColor, pointerColor);
    	speed = typedArray.getInteger(R.styleable.LoadingRing_speed, speed);
    	running = typedArray.getBoolean(R.styleable.LoadingRing_running, running);
    	typedArray.recycle();

    	centerX = radius + 1 + ringWidth / 2f;
    	centerY = radius + 1 + ringWidth / 2f;
	}
	/**
	 * 初始化
	 */
	private void init(){
		setBackgroundColor(backgroundColor);
		points = ToolsUtils.initPointsCircular(centerX, centerY, radius);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		paint.setColor(ringColor);
		paint.setStrokeWidth(ringWidth);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(centerX, centerY, radius, paint);

		paint.setColor(pointerColor);
		paint.setStrokeWidth(pointerWidth);
		if(thread == null){
			thread = new TestThread();
			thread.start();
		}else{
			canvas.drawLine(centerX, centerY, points.get(degree).x, points.get(degree).y, paint);
		}
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
		
		if(this.running == true){
			thread = new TestThread();
			thread.start();
		}
	}

	class TestThread extends Thread{
		public void run() {
			while(running){
				try {
					sleep(speed);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(degree == 359){
					degree = 0;
				}else{
					degree++;
				}
				postInvalidate();
			}
		}
	};
	
	@Override 
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {  
	    setMeasuredDimension((int) (2 * centerX), (int) (2 * centerY));
	}
}