package edu.eku.mrawlings.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    // Tax brackets (lower and middle)
    private int taxLevel1 = 10000;
    private int taxLevel2 = 100000;

    // Tax rates in decimal value
    private double taxRate1 = 0.7;
    private double taxRate2 = 0.9;
    private double taxRate3 = 1.1;

    // View objects
    EditText et_LowerBracketTax;
    EditText et_MiddleBracketTax;

    EditText et_LowerBracketRate;
    EditText et_MiddleBracketRate;
    EditText et_UpperBracketRate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        taxLevel1 = intent.getIntExtra(MainActivity.EXTRA_LOWERTAXBRACKET, 10000);
        taxLevel2 = intent.getIntExtra(MainActivity.EXTRA_MIDDLETAXBRACKET, 100000);

        taxRate1 = intent.getDoubleExtra(MainActivity.EXTRA_TAXRATE1, 0.7);
        taxRate2 = intent.getDoubleExtra(MainActivity.EXTRA_TAXRATE2, 0.9);
        taxRate3 = intent.getDoubleExtra(MainActivity.EXTRA_TAXRATE3, 1.1);

        et_LowerBracketTax = (EditText)findViewById(R.id.et_LowerTaxBracket);
        et_MiddleBracketTax = (EditText)findViewById(R.id.et_MiddleTaxBracket);

        et_LowerBracketRate = (EditText)findViewById(R.id.et_LowerBracketRate);
        et_MiddleBracketRate = (EditText)findViewById(R.id.et_MiddleBracketRate);
        et_UpperBracketRate = (EditText)findViewById(R.id.et_UpperBracketRate);

        // Set current values
        et_LowerBracketTax.setText("" + taxLevel1);
        et_MiddleBracketTax.setText("" + taxLevel2);

        et_LowerBracketRate.setText(String.format("%.2f", taxRate1 * 100));
        et_MiddleBracketRate.setText(String.format("%.2f", taxRate2 * 100));
        et_UpperBracketRate.setText(String.format("%.2f", taxRate3 * 100));

        // Save button
        Button btn_save = (Button)findViewById(R.id.btn_Save);
        btn_save.setOnClickListener(saveSettings);

        Button btn_cancel = (Button)findViewById(R.id.btn_Cancel);
        btn_cancel.setOnClickListener(cancelSettings);
    }

    // Save button
    private final View.OnClickListener saveSettings =
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    taxLevel1 = Integer.parseInt(et_LowerBracketTax.getText().toString());
                    taxLevel2 = Integer.parseInt(et_MiddleBracketTax.getText().toString());

                    // Keep 2 dec places
                    taxRate1 = Math.floor(Double.parseDouble(et_LowerBracketRate.getText().toString()) * 100) / 100;
                    taxRate2 = Math.floor(Double.parseDouble(et_MiddleBracketRate.getText().toString()) * 100) / 100;
                    taxRate3 = Math.floor(Double.parseDouble(et_UpperBracketRate.getText().toString()) * 100) / 100;

                    if (!checkTaxLevels(taxLevel1, taxLevel2) || !checkTaxRates(taxRate1, taxRate2, taxRate3))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Could not save. Check your settings.", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }

                    Intent intent = getIntent();
                    intent.putExtra(MainActivity.EXTRA_LOWERTAXBRACKET, taxLevel1);
                    intent.putExtra(MainActivity.EXTRA_MIDDLETAXBRACKET, taxLevel2);
                    intent.putExtra(MainActivity.EXTRA_TAXRATE1, taxRate1);
                    intent.putExtra(MainActivity.EXTRA_TAXRATE2, taxRate2);
                    intent.putExtra(MainActivity.EXTRA_TAXRATE3, taxRate3);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            };

    private final View.OnClickListener cancelSettings =
            new View.OnClickListener()
            {
              public void onClick(View view)
              {
                  Intent intent = getIntent();
                  setResult(RESULT_CANCELED, intent);
                  finish();
              }
            };

//    // Check for useless/incorrect settings
//    private boolean checkParameters()
//    {
//        // Bracket check
//        if (taxLevel1 > taxLevel2)
//            return false;
//
//        // Rate check
//        if (taxRate1 == taxRate2 || taxRate2 == taxRate3 || taxRate1 == taxRate3)
//            return false;
//
//        return true;
//    }

    public boolean checkTaxLevels(int level1, int level2)
    {
        if (level1 > level2)
            return false;

        return true;
    }

    public boolean checkTaxRates(double rate1, double rate2, double rate3)
    {
        if (rate1 == rate2 || rate2 == rate3 || rate1 == rate3)
            return false;

        return true;
    }
}
