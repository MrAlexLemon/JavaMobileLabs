package com.example.alex.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Console;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class ListFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private OnFragmentInteractionListener mListener;

    private TextView distance;
    private int s = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        Button button = (Button) view.findViewById(R.id.update_button);
        // задаем обработчик кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
                //insertData();
            }
        });


        //Check, which radio button is enable
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radios);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected


                switch (checkedId) {
                    case R.id.red:
                        s = R.id.red;
                        break;
                    case R.id.white:
                        s = R.id.white;
                        break;
                    case R.id.bright:
                        s = R.id.bright;
                        break;
                    case R.id.dark:
                        s = R.id.dark;
                        break;
                    default:
                        s=0;
                        break;
                }
            }
            //RadioButton rb =  view.findViewById(s);
        });
        //View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        final SeekBar seekBar  = (SeekBar) view.findViewById(R.id.seekBar);

        distance = (TextView) view.findViewById(R.id.txtView);

        seekBar.setProgress(0);
        distance.setText("0");

        seekBar.setOnSeekBarChangeListener(this);
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        distance.setText("" + i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    //input data and save it in database
    public void updateDetail() {
        // генерируем некоторые данные
        //String curDate = new Date().toString();
        //TextView tt = (TextView)getActivity().findViewById(R.id.txt);
        EditText textName = (EditText) getActivity().findViewById(R.id.editName);
        TextView textPrice = (TextView) getActivity().findViewById(R.id.txtView);
        String color;
        if(s!=0) {
            RadioButton rb = getActivity().findViewById(s);
            color = rb.getText().toString();
        }
        else
            color = "nothing";

        String name = textName.getText().toString();
        String price = textPrice.getText().toString();
        // данные Activity
        //Check for empty value
        if( !name.isEmpty()/*&&(name!="" && name!=null)*/ && price!="0" && color!="nothing") {
            mListener.onFragmentInteraction("Operation completed successfully(Data were saved in db)! You have chosen flowers: " + color + "  color " + name + " , which cost: " + price + " $");
            insertData(name,price,color);
        }
        else {
            mListener.onFragmentInteraction("Operation wasnt completed successfully(Data were not saved in db)! Fill al fields!");
        }
    }


    //Insert data in data base
    public void insertData(String name,String price,String color){
        //inflater.inflate(R.layout.fragment_list, container, false);
        SQLiteDatabase db = getActivity().getBaseContext().openOrCreateDatabase("Flowers.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS orders (name TEXT, price TEXT,color TEXT)");
        //db.execSQL("INSERT INTO orders VALUES ("+name+", "+ price+", "+color+");");
        //db.execSQL("INSERT INTO orders VALUES (name,price,color);");
        //db.execSQL("INSERT INTO orders VALUES ('name','price','color');");
        ContentValues values = new ContentValues();
        values.put("name",name);
        //db.insert("orders",null,values);
        values.put("price",price);
        //db.insert("orders",null,values);
        values.put("color",color);
        db.insert("orders",null,values);
        //db.execSQL("INSERT INTO orders VALUES ("+ name.toCharArray() +", "+ price+", " + color +");");
        //db.execSQL("insert into orders (name,price,color)" + "values("+name.toCharArray()+","+price.toCharArray()+"," + color.toCharArray()+");");

        //db.execSQL("INSERT INTO users VALUES ('John Dow', 31);");

        /*Cursor query = db.rawQuery("SELECT * FROM users;", null);
        TextView textView = (TextView)getActivity().findViewById(R.id.textView);
        if(query.moveToFirst()){
            do{
                String name = query.getString(0);
                int age = query.getInt(1);
                textView.append("Name: " + name + " Age: " + age + "\n");
            }
            while(query.moveToNext());
        }
        query.close();*/
        db.close();
    }
}
