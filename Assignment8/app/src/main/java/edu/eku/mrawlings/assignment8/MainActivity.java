package edu.eku.mrawlings.assignment8;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
    private RelativeLayout relativeLayout;
    private ImageView pig;
    private LayoutInflater layoutInflater;
    private int xPos;
    private int yPos;
    private MazeCanvas maze;
    RelativeLayout.LayoutParams params;
    private int cellId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CONSTRUCT THE MAZE AND ADD IT TO THE RELATIVE LAYOUT
        maze = new MazeCanvas(this);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.addView(maze, 0);

        // put pig in two down, two right from corner
        xPos = 2;
        yPos = 2;
        cellId = 22;

        // CREATE A LAYOUT INFLATER
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // SET THE BACKGROUND OF THE IMAGEVIEW TO THE PIG ANIMATION
        pig = (ImageView) layoutInflater.inflate(R.layout.pig_view, null);
//        pig.setBackgroundResource(R.anim.pig_animation);
         pig.setBackgroundResource( R.transition.pig_animation );

        // CREATE AN ANIMATION DRAWABLE OBJECT BASED ON THIS BACKGROUND
        AnimationDrawable manAnimate = (AnimationDrawable) pig.getBackground();
        manAnimate.start();

        //pig.setX(xPos);
        //pig.setY(yPos);

        // use layout params to set the position
        params = new RelativeLayout.LayoutParams(maze.getSize(),maze.getSize());
        params.leftMargin = xPos*maze.getSize() + maze.getOffset();
        params.topMargin = yPos*maze.getSize() + maze.getOffset();

        //pig.setScaleX(.10f);
        //pig.setScaleY(.10f);
        //relativeLayout.addView(pig, 1);
        relativeLayout.addView(pig, params);

        // update size if things change
        pig.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
             @Override
             public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                 movePig();
             }
        });
    }

    // move the pig to the new location
    public void movePig() {
        // reset size and position based on new size
        params = new RelativeLayout.LayoutParams(maze.getSize(),maze.getSize());
        params.leftMargin = xPos*maze.getSize() + maze.getOffset();
        params.topMargin = yPos*maze.getSize() + maze.getOffset();
        // pig.setLayoutParams( params );
        relativeLayout.removeView(pig);
        relativeLayout.addView(pig, params);
    }

    public void goUp(View view) {
        if (maze.board[cellId].north == false){
            //yPos -= maze.getSize();
            yPos--;

            //pig.setY(yPos);
            cellId -= maze.COLS;

            movePig();
        }
    }

    public void goLeft(View view) {
        if (maze.board[cellId].west == false){
            xPos --;
            //pig.setX(xPos);
            cellId--;

            movePig();
        }
    }

    public void goRight(View view) {
        if (maze.board[cellId].east == false){
            xPos ++;
            //pig.setX(xPos);
            cellId++;

            movePig();
        }
    }

    public void goDown(View view) {
        if (maze.board[cellId].south == false){
            yPos ++;
            //pig.setY(yPos);
            cellId += maze.COLS;

            movePig();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
