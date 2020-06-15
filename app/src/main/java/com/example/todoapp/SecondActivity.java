package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SecondActivity extends AppCompatActivity {

    ArrayList<String> toDoList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    String editText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toDoList = new ArrayList<>();
        Button addItemButton = (Button) findViewById(R.id.button_addItem);
        listView = findViewById(R.id.listViewId);
        arrayAdapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_multiple_choice, toDoList);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        final Intent itemActivity = new Intent(this, ItemActivity.class);

        //Load the items in the list from saved file in storage at start up each time
        try {
            Scanner scan = new Scanner(openFileInput("ToDo.txt"));
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                arrayAdapter.add(data);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

         //Long press an item to either edit or delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                   if(listView.getCheckedItemCount()>1) {

                    //Alert message to confirm if items must be deleted
                    AlertDialog dialog2 = new AlertDialog.Builder(SecondActivity.this)
                            .setTitle("Are you sure you want to delete selected items?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog2, int which) {
                                    SparseBooleanArray positionChecker = listView.getCheckedItemPositions();
                                    int count = listView.getCount();
                                    for (int items = count - 1; items >= 0; items--) {
                                        if (positionChecker.get(items)) {
                                            arrayAdapter.remove(toDoList.get(items));
                                        }
                                    }
                                    positionChecker.clear();
                                    arrayAdapter.notifyDataSetChanged();
                                    Toast.makeText(SecondActivity.this, "Items deleted", Toast.LENGTH_SHORT).show();
                                    saveData();
                            }
                            })
                            .setNegativeButton("No", null)
                            .show();

                    return true;
                } else {
                    AlertDialog dialog = new AlertDialog.Builder(SecondActivity.this)
                            .setTitle("Choose an action:")
                            .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //Alert message to confirm if item must be deleted
                                    AlertDialog dialog2 = new AlertDialog.Builder(SecondActivity.this)
                                            .setTitle("Are you sure you want to delete item?")
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog2, int which) {
                                                    toDoList.remove(position);
                                                    arrayAdapter.notifyDataSetChanged();
                                                    saveData();
                                                }
                                            })
                                            .setNegativeButton("No", null)
                                            .show();
                                }
                            })

                            //Edit button calling the Edit Activity when pressed
                            .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent();
                                    intent.setClass(SecondActivity.this, EditActivity.class);
                                    intent.putExtra(Intent_values.INTENT_DATA, toDoList.get(position).toString());
                                    intent.putExtra(Intent_values.INTENT_POSITION, position);
                                    startActivityForResult(intent, Intent_values.INTENT_CODE_2);
                                }
                            })
                            .show();
                    return true;

                }
            }
        });

        //When the Add item button is pressed, call the Item Activity
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemActivity.setClass(SecondActivity.this, ItemActivity.class);
                startActivityForResult(itemActivity, Intent_values.INTENT_C0DE);
            }
        });

        saveData();
    }

    //Item entered by user in Item and Edit Activities returned to be added to the List View
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Intent_values.INTENT_C0DE) {
            editText = data.getStringExtra(Intent_values.INTENT_ITEM_NAME);
            toDoList.add(editText);
            arrayAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
            saveData();
        }
        else if (resultCode==Intent_values.INTENT_CODE_2){
            editText= data.getStringExtra(Intent_values.INTENT_UPDATE);
            position= data.getIntExtra(Intent_values.INTENT_POSITION,-1);
            toDoList.remove(position);
            toDoList.add(position,editText);
            arrayAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Item has been updated", Toast.LENGTH_SHORT).show();
            saveData();
        }
    }

   //Save the to do list view in a file when pressing back to exit the application
    @Override
    public void onBackPressed() {
        try {
            PrintWriter pWriter = new PrintWriter(openFileOutput("ToDo.txt", Context.MODE_PRIVATE));
            for (String item : toDoList) {
                pWriter.println(item);
            }
            pWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

    //Save data in storage function
    public void saveData(){
        try {
            PrintWriter pWriter = new PrintWriter(openFileOutput("ToDo.txt", Context.MODE_PRIVATE));
            for (String item : toDoList) {
                pWriter.println(item);
            }
            pWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
