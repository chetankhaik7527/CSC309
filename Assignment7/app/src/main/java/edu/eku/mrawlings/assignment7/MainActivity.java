package edu.eku.mrawlings.assignment7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
{
    RelativeLayout dashboard;
    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_red = (Button)findViewById(R.id.btn_red);
        btn_red.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.changePaintColor(0xFFFF0000);
            }
        });

        Button btn_green = (Button)findViewById(R.id.btn_green);
        btn_green.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.changePaintColor(0xFF00FF00);
            }
        });

        Button btn_blue = (Button)findViewById(R.id.btn_blue);
        btn_blue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.changePaintColor(0xFF0000FF);
            }
        });

        Button btn_yellow = (Button)findViewById(R.id.btn_yellow);
        btn_yellow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.changePaintColor(0xFFFFFF00);
            }
        });

        Button btn_pink = (Button)findViewById(R.id.btn_pink);
        btn_pink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.changePaintColor(0xFFFF00FF);
            }
        });

        Button btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.clear();
            }
        });

        Button btn_image1 = (Button)findViewById(R.id.btn_image1);
        btn_image1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.changeImage(0);
            }
        });

        Button btn_image2 = (Button)findViewById(R.id.btn_image2);
        btn_image2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.changeImage(1);
            }
        });

        Button btn_image3 = (Button)findViewById(R.id.btn_image3);
        btn_image3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myView.changeImage(2);
            }
        });

        myView = new MyView(this);
        dashboard = (RelativeLayout)findViewById(R.id.dashboard);
        dashboard.addView(myView);
    }
}
