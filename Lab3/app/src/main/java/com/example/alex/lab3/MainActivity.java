package com.example.alex.lab3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String link) {
        DetailFragment fragment = (DetailFragment)getSupportFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
        }
    }

    public void onClick(View view){
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("Flowers.db", MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS orders (name TEXT, price TEXT,color TEXT)");

        //db.execSQL("DELETE FROM orders;");
        //db.execSQL("SELECT * FROM users;");
        //db.execSQL("INSERT INTO users VALUES ('Tom Smith', 23);");
        //db.execSQL("INSERT INTO users VALUES ('John Dow', 31);");

        Cursor cursor = db.query("orders", null, null, null, null, null,null);

        Cursor query = db.rawQuery("SELECT * FROM orders;", null);

        TextView textView = (TextView) findViewById(R.id.textView);

        //Check is database empty
        if(cursor.getCount()>0)
        {
            // Clear all previous records
            if(!textView.getText().toString().isEmpty())
                textView.setText("");
            // database not empty
            // Display all raws
            if(query.moveToFirst()){
                do{
                    String name = query.getString(0);
                    String price = query.getString(1);
                    String color = query.getString(2);
                    textView.append("Name: " + name + " Price: " + price + " Color: " + color +"\n");
                }
                while(query.moveToNext());
            }
        } else {
            // database empty
            textView.setText("Database is empty!");
        }

        query.close();
        db.close();
    }
}