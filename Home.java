package com.utd.breakout;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class Home extends AppCompatActivity {

    ArrayList<String> data1 = new ArrayList<String>();  // To store the data to be displayed in the list
    ArrayList<String> all_data = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FloatingActionButton play = (FloatingActionButton) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BreakoutGame.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        writetofile();
        final ListView lv = (ListView)findViewById(R.id.highscores);
        final ArrayList<String> lv1 = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lv1);
        lv.setAdapter(adapter);
        /////////////
        try {
            // Create an input stream to open the file
            InputStream inputStream = openFileInput("highscores3.txt");
            if ( inputStream != null ) {    // If the file is opened
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  // A reader
                String receiveString = "";  // String received from reading each line
                while ( (receiveString = bufferedReader.readLine()) != null ) { // To read all lines
                    lv1.add(receiveString);  // Add to the array adapter to show in the list
                    // First name is data2[0] and then comes Last Name and finaly the Phone number
                    //all_data.add(receiveString);
                    // Add the received string to all the data
                }
                Collections.sort(lv1,String.CASE_INSENSITIVE_ORDER);  // Sort both the arraylists
                Collections.reverse(lv1);
                //Collections.sort(all_data,String.CASE_INSENSITIVE_ORDER);
                //sortFile(); // Call to the method
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        adapter.notifyDataSetChanged();
        /////////////
    }

    public void writetofile() {
        String string = 32002 + "\t\t\t\t:\t\t\t\t" + "Aditya2" + "\n";
        String string1 = 32001 + "\t\t\t\t:\t\t\t\t" + "Aditya1" + "\n";
        String string2 = 32000 + "\t\t\t\t:\t\t\t\t" + "Aditya" + "\n";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("highscores3.txt", Context.MODE_APPEND);
            // Write to the file
            fos.write(string.getBytes());
            fos.write(string1.getBytes());
            fos.write(string2.getBytes());
        } catch (IOException e) {
            Toast.makeText(this, "Failed to create a file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


}