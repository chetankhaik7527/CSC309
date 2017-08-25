package edu.eku.mrawlings.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    // View objects
    private TextView taxDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set title
        setTitle("Tisroc of Calormen Calculator");

        // Get tax text view
        taxDisplay = (TextView)findViewById(R.id.tv_tax);

        // Set button listener
        findViewById(R.id.btn_calculate).setOnClickListener(calculateIncome);
    }

    private final View.OnClickListener calculateIncome =
            new View.OnClickListener()
            {
                public void onClick(View button)
                {
                    // Get edit text view object
                    EditText et_income = (EditText)findViewById(R.id.et_income);
                    int income;
                    double tax;

                    try
                    {
                        // Try to parse value to int
                        income = Integer.parseInt(et_income.getText().toString());
                    }
                    catch(Exception e)
                    {
                        // Otherwise set it to 0 to prevent app from crashing
                        income = 0;
                    }

                    // Calculate tax for income below or equal to $10,000
                    if (income <= 10000)
                    {
                        tax = (income * .7);
                    }
                    // Calculate tax for income between $10,000 and $100,000
                    else if (income <= 100000)
                    {
                        tax = ((income - 10000) * .9 + 7000);
                    }
                    // Calculate tax for income above $100,000
                    else if (income > 100000)
                    {
                        tax = ((income - 100000) * 1.1 + 88000);
                    }
                    // In case other cases fails
                    else
                    {
                        tax = 0;
                    }

                    // Format and display tax
                    taxDisplay.setText("$" + String.format("%.2f", tax));
                }
            };
}
