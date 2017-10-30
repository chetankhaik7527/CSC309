package edu.eku.mrawlings.assignment6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    MandelbrotSurfaceView mandelbrotSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mandelbrotSurfaceView = new MandelbrotSurfaceView(this);
        setContentView(mandelbrotSurfaceView);
    }
}
