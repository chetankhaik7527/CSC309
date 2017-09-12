package edu.eku.mrawlings.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
    private int judgeScores[];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_calculate).setOnClickListener(calculateScore);
    }

    private final View.OnClickListener calculateScore =
            new View.OnClickListener()
            {
                public void onClick(View button)
                {
                    judgeScores = new int[7];

                    // Judge 1
                    EditText et_judge1 = (EditText)findViewById(R.id.judge1_score);

                    try
                    {
                        int j1score = Integer.parseInt(et_judge1.getText().toString());
                        if (j1score < 0)
                            judgeScores[0] = 0;
                        else if (j1score > 10)
                            judgeScores[0] = 10;
                        else
                            judgeScores[0] = j1score;

                        et_judge1.setText("" + judgeScores[0]);
                    }
                    catch(Exception e)
                    {
                        judgeScores[0] = 0;
                    }

                    // Judge 2
                    SeekBar sb_judge2 = (SeekBar)findViewById(R.id.judge2_score);
                    judgeScores[1] = sb_judge2.getProgress();

                    // Judge 3
                    Spinner sp_judge3 = (Spinner)findViewById(R.id.judge3_score);
                    judgeScores[2] = Integer.parseInt(sp_judge3.getSelectedItem().toString());

                    // Judge 3
                    Spinner sp_judge4 = (Spinner)findViewById(R.id.judge4_score);
                    judgeScores[3] = Integer.parseInt(sp_judge4.getSelectedItem().toString());

                    // Judge 3
                    Spinner sp_judge5 = (Spinner)findViewById(R.id.judge5_score);
                    judgeScores[4] = Integer.parseInt(sp_judge5.getSelectedItem().toString());

                    // Judge 3
                    Spinner sp_judge6 = (Spinner)findViewById(R.id.judge6_score);
                    judgeScores[5] = Integer.parseInt(sp_judge6.getSelectedItem().toString());

                    // Judge 7
                    Spinner sp_judge7 = (Spinner)findViewById(R.id.judge7_score);
                    judgeScores[6] = Integer.parseInt(sp_judge7.getSelectedItem().toString());


                    // Total Score
                    float total_score = 0;

                    // Arrange score in ascending order and remove the last array
                    bubbleSort(judgeScores);
                    judgeScores = Arrays.copyOf(judgeScores, judgeScores.length - 1);

                    // Arrange score in descending order and remove the last array
                    reverseBubbleSort(judgeScores);
                    judgeScores = Arrays.copyOf(judgeScores, judgeScores.length - 1);

                    // Add the remaining scores and average it
                    for (int i : judgeScores)
                        total_score += i;
                    total_score /= judgeScores.length;

                    // Display the score in decimal format to tenth place
                    TextView tv_totalscore = (TextView)findViewById(R.id.total_score);
                    tv_totalscore.setText(String.format("%.1f", total_score));
                }

                // Sort in ascending order
                // Ex: 0, 5, 6, 9, 10
                private void bubbleSort(int[] input)
                {
                    int length = input.length;
                    int temp = 0;
                    for (int i = 0; i < length; i++)
                    {
                        for (int j = 1; j < (length - i); j++)
                        {
                            if (input[j-1] > input[j])
                            {
                                temp = input[j-1];
                                input[j-1] = input[j];
                                input[j] = temp;
                            }
                        }
                    }
                }

                // Sort in descending order
                // Ex: 19, 10, 4, 3, 2, 2
                private void reverseBubbleSort(int[] input)
                {
                    int length = input.length;
                    int temp = 0;
                    for (int i = 0; i < length; i++)
                    {
                        for (int j = 1; j < (length - i); j++)
                        {
                            if (input[j-1] < input[j])
                            {
                                temp = input[j-1];
                                input[j-1] = input[j];
                                input[j] = temp;
                            }
                        }
                    }
                }
            };
}
