package edu.eku.mrawlings.assignment7;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill
{
    public void fill(Bitmap bitmap, Point point, int targetColor, int replacementColor)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int target = targetColor;
        int replacement = replacementColor;

        if (target != replacement)
        {
            Queue<Point> queue = new LinkedList<>();
            do
            {
                int x = point.x;
                int y = point.y;

                while (x > 0 && bitmap.getPixel(x - 1, y) == target)
                {
                    x--;
                }

                boolean spanUp = false;
                boolean spanDown = false;

                while (x < width && bitmap.getPixel(x, y) == target)
                {
                    bitmap.setPixel(x, y, replacement);

                    if (!spanUp && y > 0 && bitmap.getPixel(x, y - 1) == target)
                    {
                        queue.add(new Point(x, y - 1));
                        spanUp = true;
                    }
                    else if (spanUp && y > 0 && bitmap.getPixel(x, y - 1) != target)
                    {
                        spanUp = false;
                    }

                    if (!spanDown && y < (height - 1) && bitmap.getPixel(x, y + 1) == target)
                    {
                        queue.add(new Point(x, y + 1));
                        spanDown = true;
                    }
                    else if (spanDown && y < (height - 1) && bitmap.getPixel(x, y + 1) != target)
                    {
                        spanDown = false;
                    }

                    x++;
                }
            } while ((point = queue.poll()) != null);
        }
    }
}
