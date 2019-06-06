package com.example.alex.lab2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.Date;


public class ListFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private OnFragmentInteractionListener mListener;

    private TextView distance;
    private int s = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Button button = (Button) view.findViewById(R.id.update_button);
        // задаем обработчик кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });



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

        seekBar.setProgress(1);
        distance.setText("1");

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
        mListener.onFragmentInteraction("You have chosen flowers: "  +color+ "  color " +name+  " , which cost: "+price+" $");
    }


}
