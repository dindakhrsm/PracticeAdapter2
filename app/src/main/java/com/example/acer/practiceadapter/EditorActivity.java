package com.example.acer.practiceadapter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.practiceadapter.data.StudentContract.StudentEntry;
import com.example.acer.practiceadapter.data.StudentDbHelper;

/**
 * Created by ACER on 15/10/2016.
 */

/**
 * Allows user to create a new student or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter the registration number */
    private EditText mNoregEditText;

    /** EditText field to enter the student's name */
    private EditText mNameEditText;

    /** EditText field to enter the student's phone */
    private EditText mPhoneEditText;

    /** EditText field to enter the student's mail */
    private EditText mMailEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        FloatingActionButton cancelButton = (FloatingActionButton) findViewById(R.id.cancelActionButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //close this activity
                finish();
            }
        });

        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.saveActionButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertStudent();
                finish();
            }
        });

        // Find all relevant views that we will need to read user input from
        mNoregEditText = (EditText) findViewById(R.id.edit_nim);
        mNameEditText = (EditText) findViewById(R.id.edit_nama);
        mPhoneEditText = (EditText) findViewById(R.id.edit_phone);
        mMailEditText = (EditText) findViewById(R.id.edit_email);
    }

    /**
     * Get user input from editor and save new student info into database.
     */
    private void insertStudent() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String noregString = mNoregEditText.getText().toString().trim();
        String nameString = mNameEditText.getText().toString().trim();
        String phoneString = mPhoneEditText.getText().toString().trim();
        String mailString = mMailEditText.getText().toString().trim();

        // Create database helper
        StudentDbHelper mDbHelper = new StudentDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and student attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(StudentEntry.COLUMN_STUDENT_NOREG, noregString);
        values.put(StudentEntry.COLUMN_STUDENT_NAME, nameString);
        values.put(StudentEntry.COLUMN_STUDENT_PHONE, phoneString);
        values.put(StudentEntry.COLUMN_STUDENT_MAIL, mailString);

        // Insert a new row for student in the database, returning the ID of that new row.
        long newRowId = db.insert(StudentEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving student", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Student saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }
*/
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save student's info to database
                // Exit activity
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.delete_item:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (StudentActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}