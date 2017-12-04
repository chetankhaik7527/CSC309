package edu.eku.mrawlings.finalproject;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.eku.mrawlings.finalproject.db.ItemDBHelper;

public class MainActivity extends AppCompatActivity
{
    private ItemDBHelper itemDBHelper;
    private ListView itemListView;
    private ItemsAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemDBHelper = new ItemDBHelper(this);

        itemListView = (ListView) findViewById(R.id.list_items);
        updateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_add_item:
                final EditText itemEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new item")
                        .setMessage("What food do you want to add?")
                        .setView(itemEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                String item = String.valueOf(itemEditText.getText());
                                SQLiteDatabase db = itemDBHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("title", item);
                                db.insertWithOnConflict("items", null, values, SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateListView();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateListView()
    {
        ArrayList<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = itemDBHelper.getReadableDatabase();
        Cursor cursor = db.query("items", new String[] { "_id", "title", "flag" }, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            int index = cursor.getColumnIndex("title");
            String title = cursor.getString(index);

            index = cursor.getColumnIndex("flag");
            boolean flag = cursor.getInt(index) == 1;

            Item item = new Item(title, flag);
            itemList.add(item);
        }

        if (itemArrayAdapter == null)
        {
            itemArrayAdapter = new ItemsAdapter(this, itemList);
            itemListView.setAdapter(itemArrayAdapter);
        }
        else
        {
            itemArrayAdapter.clear();
            itemArrayAdapter.addAll(itemList);
            itemArrayAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    public void checkItem(View view)
    {
        System.out.println("Clicked");

        CheckBox cb = view.findViewById(R.id.cb_item);

        View parent = (View) view.getParent();
        TextView tv_title = parent.findViewById(R.id.tv_item_title);
        String title = tv_title.getText().toString();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("flag", cb.isChecked() ? 1 : 0);

        SQLiteDatabase db = itemDBHelper.getWritableDatabase();
        db.update("items", values, "title = ?", new String[] { title } );

        db.close();
        updateListView();
    }

    public void deleteItem(View view)
    {
        View parent = (View)view.getParent();
        TextView tv_title = parent.findViewById(R.id.tv_item_title);
        String title = tv_title.getText().toString();
        SQLiteDatabase db = itemDBHelper.getWritableDatabase();
        db.delete("items", "title = ?", new String[]{title});
        db.close();
        updateListView();
    }
}
