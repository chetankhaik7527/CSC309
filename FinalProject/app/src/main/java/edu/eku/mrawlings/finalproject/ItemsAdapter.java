package edu.eku.mrawlings.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<Item>
{
    public ItemsAdapter(Context context, ArrayList<Item> items)
    {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        Item item = getItem(position);

        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_food, parent, false);
        }

        TextView tv_title = view.findViewById(R.id.tv_item_title);
        CheckBox cb_complete = view.findViewById(R.id.cb_item);

        tv_title.setText(item.title);
        cb_complete.setChecked(item.flag);

        return view;
    }
}
