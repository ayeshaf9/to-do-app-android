package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ItemActivity extends AppCompatActivity {

    EditText editText;
    String sendText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Button button_cancel = (Button) findViewById(R.id.button_cancel);
        Button button_ok = (Button) findViewById(R.id.button_ok);

       //Return to Second Activity (List view) when cancel button is pressed
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivity = new Intent();
                setResult(0,secondActivity);
                finish();
            }
        });

        //Pass the entered item back to the Second Activity
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = findViewById(R.id.enter_item);
                sendText = editText.getText().toString();
                if (sendText.equals("")){
                    Toast.makeText(ItemActivity.this, "Please enter item", Toast.LENGTH_SHORT).show();
                }else {
                    Intent secondActivity = new Intent();
                    secondActivity.putExtra(Intent_values.INTENT_ITEM_NAME, sendText);
                    setResult(Intent_values.INTENT_RESULT,secondActivity);
                    finish();
                }
            }
        });
    }
}
