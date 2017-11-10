package edu.eku.mrawlings.assignment7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View
{
    public Bitmap[] rawBitmaps = new Bitmap[3];
    public Bitmap bitmap;

    private Paint paint;
    private Point point;
    private int size;
    private int index = 0;

    public MyView(Context context)
    {
        super(context);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(5f);
        paint.setColor(0xFFFF0000);

        point = new Point();

        rawBitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.img_snake, new BitmapFactory.Options());
        rawBitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.img_bird, new BitmapFactory.Options());
        rawBitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.img_turtle, new BitmapFactory.Options());

        addOnLayoutChangeListener(new View.OnLayoutChangeListener()
        {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
            {
                size = Math.min(bottom - top, right - left);
                bitmap = Bitmap.createScaledBitmap(rawBitmaps[index], size, size, true);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        setBackgroundColor(Color.WHITE);

        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                point.x = (int) x;
                point.y = (int) y;

                System.out.println("Pressed");
                final int targetColor = bitmap.getPixel((int) x, (int) y);
                final int replacementColor = paint.getColor();
                new DrawTask(bitmap, point, targetColor, replacementColor).execute();
                invalidate();
        }

        return true;
    }

    public void changePaintColor(int color)
    {
        paint.setColor(color);
    }

    public void clear()
    {
        bitmap = Bitmap.createScaledBitmap(rawBitmaps[index], size, size, true);
        invalidate();
    }

    public void changeImage(int i)
    {
        index = i;
        clear();
    }

    class DrawTask extends AsyncTask<Void, Integer, Void>
    {
        Bitmap bitmap;
        Point point;
        int targetColor, replacementColor;

        public DrawTask(Bitmap b, Point p, int targetColor, int replacementColor)
        {
            bitmap = b;
            point = p;
            this.replacementColor = replacementColor;
            this.targetColor = targetColor;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            FloodFill f = new FloodFill();
            f.fill(bitmap, point, targetColor, replacementColor);

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            invalidate();
        }
    }
}
