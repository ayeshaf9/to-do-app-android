package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    String itemText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        itemText = intent.getStringExtra(Intent_values.INTENT_DATA);
        position = intent.getIntExtra(Intent_values.INTENT_POSITION, -1);
        EditText editedItem = (EditText) findViewById(R.id.enter_item);
        editedItem.setText(itemText);
        Button button_ok = (Button) findViewById(R.id.button_save_changes);
    }

    //Send the edited item back to the Second Activity
    public void saveChanges(View v){
        String editedItemText = ((EditText) findViewById(R.id.enter_item)).getText().toString();
        Intent secondActivity = new Intent();
        secondActivity.putExtra(Intent_values.INTENT_UPDATE, editedItemText);
        secondActivity.putExtra(Intent_values.INTENT_POSITION, position);
        setResult(Intent_values.INTENT_RESULT_2, secondActivity);
        finish();
    }
}
