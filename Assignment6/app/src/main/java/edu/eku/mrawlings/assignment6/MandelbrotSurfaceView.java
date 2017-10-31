package edu.eku.mrawlings.assignment6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MandelbrotSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    // MESSAGES
    final int MESSAGE_QUIT = 0;
    final int MESSAGE_UPDATE = 1;

    //
    final int MAX_ITERATIONS = 500;

    Handler handler;
    SurfaceHolder holder;
    DrawingThread drawingThread;

    // Size of view
    int viewSize = 0;

    //
    final int ZOOM = 150;
    double zX, zY, cX, cY, temp;

    MandelbrotSurfaceView(Context context)
    {
        super(context);

        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder)
    {
        drawingThread = new DrawingThread();
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height)
    {
        holder = surfaceHolder;
        viewSize = Math.min(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder)
    {
        handler.sendEmptyMessage(MESSAGE_QUIT);
    }

    class DrawingThread extends HandlerThread implements Handler.Callback
    {
        public DrawingThread()
        {
            super("DrawingThread");
        }

        protected void onLooperPrepared()
        {
            Looper looper = getLooper();
            handler = new Handler(looper, this);
            handler.sendEmptyMessageDelayed(MESSAGE_UPDATE, 1);
        }

        @Override
        public boolean handleMessage(Message message)
        {
            if (message.what == MESSAGE_QUIT)
            {
                drawingThread.quit();
                return true;
            }

            if (message.what != MESSAGE_UPDATE)
            {
                return false;
            }

            Canvas canvas = holder.lockCanvas();

            if (canvas == null)
            {
                handler.sendEmptyMessageDelayed(MESSAGE_UPDATE, 100);
                return true;
            }

            canvas.drawColor(Color.GREEN);

            System.out.println("MANDLEBROT");

            for (int y = 0; y < viewSize; y++)
            {
                for (int x = 0; x < viewSize; x++)
                {
                    zX = 0;
                    zY = 0;
                    cX = (x - 400) / ZOOM;
                    cY = (y - 300) / ZOOM;
                    int iter = MAX_ITERATIONS;
                    while ((zX * zX + zY * zY) < 4 && iter > 0)
                    {
                        temp = zX * zX - zY * zY + cX;
                        zY = 2.0 * zX * zY + cY;
                        zX = temp;
                        iter--;
                    }
                    Paint paint = new Paint();
                    paint.setColor(iter | (iter << 8));

                    canvas.drawPoint(x, y, paint);
                }
            }

            holder.unlockCanvasAndPost(canvas);
            handler.sendEmptyMessageDelayed(MESSAGE_UPDATE, 100);

            return true;
        }
    }
}
