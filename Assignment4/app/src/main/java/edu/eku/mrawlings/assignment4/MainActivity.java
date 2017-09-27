package edu.eku.mrawlings.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity
{
    public enum CONVERSION
    {
        MILE_TO_KILOMETER,
        POUND_TO_KILOGRAM,
        RADIAN_TO_DEGREE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        // link the list fragment and the main activity
        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.fragment1);
        listFragment.setActivity(this);
    }

    public void onConversionButtonSelected(CONVERSION conversion)
    {
        InformationFragment infoFrag = (InformationFragment)getFragmentManager().findFragmentById(R.id.fragment2);

        if (infoFrag != null && infoFrag.isInLayout())
        {
            System.out.println("pressed");
            switch(conversion)
            {
                case MILE_TO_KILOMETER:
                    infoFrag.setConversion(0);
                    break;

                case POUND_TO_KILOGRAM:
                    infoFrag.setConversion(1);
                    break;

                case RADIAN_TO_DEGREE:
                    infoFrag.setConversion(2);
                    break;

                default:
                    infoFrag.setConversion(0);
            }
        }
        else
        {
            System.out.println("PRESSED OMG!");
            Intent infoIntent = new Intent(this, InformationActivity.class);

            switch(conversion)
            {
                case MILE_TO_KILOMETER:
                    infoIntent.putExtra("conversion", 0);
                    break;

                case POUND_TO_KILOGRAM:
                    infoIntent.putExtra("conversion", 1);
                    break;

                case RADIAN_TO_DEGREE:
                    infoIntent.putExtra("conversion", 2);
                    break;

                default:
                    infoIntent.putExtra("conversion", 0);
            }
            startActivity(infoIntent);
        }
    }
}
