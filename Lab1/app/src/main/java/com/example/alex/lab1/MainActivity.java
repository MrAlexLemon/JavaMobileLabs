package com.example.alex.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    private TextView textView;
    private SeekBar seekBar;
    private String color;
    private String price;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.txtView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView.setText(String.valueOf(progress));
                price = textView.getText().toString();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final Button button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //button.setText("O, hi!");
                TextView result = (TextView) findViewById(R.id.result);
                EditText selection = (EditText) findViewById(R.id.editName);
                name=selection.getText().toString();

                if(color==null)
                    color="none";
                if(price==null)
                    price="none";
                if(name==null)
                    name="none";
                result.setText("You chose flowers: "+name+" "+ color+"'s color"+" which cost "+price+" usd.");
            }
        });


    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        //TextView selection = (TextView) findViewById(R.id.selectionColor);
        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.red:
                if (checked){
                    //selection.setText(((RadioButton) view).getText());
                }
                break;
            case R.id.white:
                if (checked){
                    //selection.setText(((RadioButton) view).getText());
                }
                break;
            case R.id.bright:
                if (checked){
                    //selection.setText(((RadioButton) view).getText());
                }
                break;
            case R.id.dark:
                if (checked){
                    //selection.setText(((RadioButton) view).getText());
                }
                break;
        }
        //view.isChecked()=!checked;
        //selection.setText("You choose color: "+((RadioButton) view).getText());
        //color=new((RadioButton) view).getText());

        color = ((RadioButton) view).getText().toString();

}
}
