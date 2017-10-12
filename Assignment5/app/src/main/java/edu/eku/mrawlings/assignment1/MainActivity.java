package edu.eku.mrawlings.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    //
    public static String EXTRA_LOWERTAXBRACKET = "lowertaxbracket";
    public static String EXTRA_MIDDLETAXBRACKET = "middletaxbracket";
    public static String EXTRA_TAXRATE1 = "taxrate1";
    public static String EXTRA_TAXRATE2 = "taxrate2";
    public static String EXTRA_TAXRATE3 = "taxrate3";

    // View objects
    private TextView taxDisplay;

    // Tax bracket
    private int taxLevel1 = 10000;
    private int taxLevel2 = 100000;

    // Tax rates
    private double taxRate1 = 0.7;
    private double taxRate2 = 0.9;
    private double taxRate3 = 1.1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taxDisplay = (TextView)findViewById(R.id.tv_tax);

        // Set button listener
        findViewById(R.id.btn_calculate).setOnClickListener(calculateIncome);
        findViewById(R.id.btn_settings).setOnClickListener(goToSettings);
    }

    private final View.OnClickListener calculateIncome =
            new View.OnClickListener()
            {
                public void onClick(View button)
                {
                    EditText et_income = (EditText)findViewById(R.id.et_income);
                    int income;
                    double tax;

                    // Prevent app from crashing on parse
                    try
                    {
                        income = Integer.parseInt(et_income.getText().toString());
                    }
                    catch(Exception e)
                    {
                        income = 0;
                    }

                    // Lower tax bracket
                    if (income <= taxLevel1)
                    {
                        tax = taxRate1 * income;
                    }
                    // Middle tax bracket
                    else if (income <= taxLevel2)
                    {
                        tax = taxRate1 * taxLevel1 + taxRate2 * (income - taxLevel1);
                    }
                    // Upper tax bracket
                    else if (income > taxLevel2)
                    {
                        tax = taxRate1 * taxLevel1 + taxRate2 * (taxLevel2 - taxLevel1) + taxRate3 * (income - taxLevel2);
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

    // Settings button
    private final View.OnClickListener goToSettings =
            new View.OnClickListener()
            {
                public void onClick(View button)
                {
                    Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                    settingsIntent.putExtra(EXTRA_LOWERTAXBRACKET, taxLevel1);
                    settingsIntent.putExtra(EXTRA_MIDDLETAXBRACKET, taxLevel2);
                    settingsIntent.putExtra(EXTRA_TAXRATE1, taxRate1);
                    settingsIntent.putExtra(EXTRA_TAXRATE2, taxRate2);
                    settingsIntent.putExtra(EXTRA_TAXRATE3, taxRate3);
                    startActivityForResult(settingsIntent, 123);
                }
            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        // Save setting success
        if (resultCode == RESULT_OK)
        {
            taxLevel1 = intent.getIntExtra(MainActivity.EXTRA_LOWERTAXBRACKET, 10000);
            taxLevel2 = intent.getIntExtra(MainActivity.EXTRA_MIDDLETAXBRACKET, 100000);

            taxRate1 = intent.getDoubleExtra(MainActivity.EXTRA_TAXRATE1, 70) / 100.0;
            taxRate2 = intent.getDoubleExtra(MainActivity.EXTRA_TAXRATE2, 90) / 100.0;
            taxRate3 = intent.getDoubleExtra(MainActivity.EXTRA_TAXRATE3, 110) / 100.0;
        }
    }
}
