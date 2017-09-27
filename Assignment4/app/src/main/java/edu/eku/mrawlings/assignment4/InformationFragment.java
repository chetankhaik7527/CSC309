package edu.eku.mrawlings.assignment4;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class InformationFragment extends Fragment
{
    // Hold info
    private double factor = 0.0;
    private String unit = "km";
    TextView tv_Title;

    public InformationFragment()
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
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        final TextView tv_Value = view.findViewById(R.id.tv_Value);
        final EditText et_Input = view.findViewById(R.id.et_Input);
        Button btn_Calculate = view.findViewById(R.id.btn_Calculate);
        btn_Calculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                double value = Double.parseDouble(et_Input.getText().toString());
                value *= factor;

                tv_Value.setText("" + value + " " + unit);
            }
        });

        return view;
    }

    // Set conversion mode for fragment
    public void setConversion(int conversion)
    {
        tv_Title = getView().findViewById(R.id.tv_ConversionTitle);
        TextView tv_Value = getView().findViewById(R.id.tv_Value);
        tv_Value.setText("0");
        EditText et_Input = getView().findViewById(R.id.et_Input);
        et_Input.setText("");

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
