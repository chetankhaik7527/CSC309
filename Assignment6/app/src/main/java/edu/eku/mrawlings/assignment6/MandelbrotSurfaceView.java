package edu.eku.mrawlings.assignment6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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

    Handler handler;
    SurfaceHolder holder;
    DrawingThread drawingThread;

    int viewSize = 0;

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

            holder.unlockCanvasAndPost(canvas);
            handler.sendEmptyMessageDelayed(MESSAGE_UPDATE, 100);

            return true;
        }
    }
}
