package com.example.acer.practiceadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.acer.practiceadapter.data.StudentDbHelper;

/**
 * Created by ACER on 21/10/2016.
 */
public class EmptyActivity extends AppCompatActivity {

   /* private StudentArrayAdapter studentArrayAdapter;
    private StudentList studentList;
    private ListView studentListView;
*/

    // Database helper that will provide us access to the database
    //private StudentDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmptyActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu options from the res/menu/menu_student_list.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_empty_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.create_dummy:
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}