package edu.eku.mrawlings.assignment4;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ListFragment extends Fragment
{
    private MainActivity mainActivity;

    public void setActivity(MainActivity activity)
    {
        mainActivity = activity;
    }

    public ListFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button btn_MileToKilometer = view.findViewById(R.id.btn_MileToKilometer);
        btn_MileToKilometer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mainActivity.onConversionButtonSelected(MainActivity.CONVERSION.MILE_TO_KILOMETER);
            }
        });

        Button btn_PoundToKilogram = view.findViewById(R.id.btn_PoundToKilogram);
        btn_PoundToKilogram.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mainActivity.onConversionButtonSelected(MainActivity.CONVERSION.POUND_TO_KILOGRAM);
            }
        });

        Button btn_RadianToDegree = view.findViewById(R.id.btn_RadianToDegree);
        btn_RadianToDegree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mainActivity.onConversionButtonSelected(MainActivity.CONVERSION.RADIAN_TO_DEGREE);
            }
        });

        return view;
    }
}
