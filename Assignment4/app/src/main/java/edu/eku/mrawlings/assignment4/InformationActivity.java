package edu.eku.mrawlings.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InformationActivity extends Activity
{
    private double factor = 0.0;
    private String unit = "KM";
    TextView tv_Title;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_information);

        Intent intent = getIntent();
        int conversion = intent.getIntExtra("conversion", 0);

        tv_Title = findViewById(R.id.tv_ConversionTitle);

        setConversion(conversion);

        Button btn_Calculate = findViewById(R.id.btn_Calculate);
        btn_Calculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onCalculateButtonPress();
            }
        });
    }

    public void onCalculateButtonPress()
    {
        TextView tv_Value = findViewById(R.id.tv_Value);
        EditText et_Input = findViewById(R.id.et_Input);

        double value = Double.parseDouble(et_Input.getText().toString());
        value *= factor;

        tv_Value.setText("" + value + " " + unit);
    }

    public void setConversion(int conversion)
    {
        switch(conversion)
        {
            case 0:
                tv_Title.setText("Miles to Kilometers");
                factor = 1.60934;
                unit = "km";
                break;

            case 1:
                tv_Title.setText("Pounds to Kilograms");
                factor = 0.453592;
                unit = "kg";
                break;

            case 2:
                tv_Title.setText("Radians to Degrees");
                factor = 57.2958;
                unit = "deg";
                break;

            default:
                tv_Title.setText("Miles to Kilometers");
                factor = 1.60934;
                unit = "km";
                break;
        }
    }
}
