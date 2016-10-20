package com.example.acer.practiceadapter;

import android.content.Intent;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ListView;

//import com.example.acer.practiceadapter.com.example.acer.adapter.StudentArrayAdapter;
//import com.example.acer.practiceadapter.com.example.acer.user.Student;
//import com.example.acer.practiceadapter.com.example.acer.user.StudentList;

import com.example.acer.practiceadapter.data.StudentContract.StudentEntry;
import com.example.acer.practiceadapter.data.StudentDbHelper;

import java.util.ArrayList;
/**
 * Created by ACER on 13/10/2016.
 */
public class StudentActivity extends AppCompatActivity {

   /* private StudentArrayAdapter studentArrayAdapter;
    private StudentList studentList;
    private ListView studentListView;
*/

    // Database helper that will provide us access to the database
    private StudentDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new StudentDbHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the students database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                StudentEntry._ID,
                StudentEntry.COLUMN_STUDENT_NOREG,
                StudentEntry.COLUMN_STUDENT_NAME,
                StudentEntry.COLUMN_STUDENT_PHONE,
                StudentEntry.COLUMN_STUDENT_MAIL };

        // Perform a query on the students table
        Cursor cursor = db.query(
                StudentEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_student);
        /*studentListView = (ListView) findViewById(R.id.list_item);
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(StudentActivity.this, EditorActivity.class);
                //we want to pass two data: action mode, and student info
                intent.putExtra("action","edit");
                Student student = studentList.get(position);
                intent.putExtra("student_info",student);
                startActivity(intent);
            }
        });*/

        try {
            // Create a header in the Text View that looks like this:
            //
            // The students table contains <number of rows in Cursor> students.
            // _id - noreg - name - phone - email
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The students table contains " + cursor.getCount() + " students.\n\n");
            displayView.append(StudentEntry._ID + " - " +
                    StudentEntry.COLUMN_STUDENT_NOREG + " - " +
                    StudentEntry.COLUMN_STUDENT_NAME + " - " +
                    StudentEntry.COLUMN_STUDENT_PHONE + " - " +
                    StudentEntry.COLUMN_STUDENT_MAIL + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(StudentEntry._ID);
            int noregColumnIndex = cursor.getColumnIndex(StudentEntry.COLUMN_STUDENT_NOREG);
            int nameColumnIndex = cursor.getColumnIndex(StudentEntry.COLUMN_STUDENT_NAME);
            int phoneColumnIndex = cursor.getColumnIndex(StudentEntry.COLUMN_STUDENT_PHONE);
            int mailColumnIndex = cursor.getColumnIndex(StudentEntry.COLUMN_STUDENT_MAIL);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentNoreg = cursor.getString(noregColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPhone = cursor.getString(phoneColumnIndex);
                String currentMail = cursor.getString(mailColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentNoreg + " - " +
                        currentName + " - " +
                        currentPhone + " - " +
                        currentMail));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded student's data into the database. For debugging purposes only.
     */
    private void insertStudent() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and attributes are the values.
        ContentValues values = new ContentValues();
        values.put(StudentEntry.COLUMN_STUDENT_NOREG, "3145136204");
        values.put(StudentEntry.COLUMN_STUDENT_NAME, "Dinda Kharisma");
        values.put(StudentEntry.COLUMN_STUDENT_PHONE, "089601676825");
        values.put(StudentEntry.COLUMN_STUDENT_MAIL, "dindakhrsm@gmail.com");

        // Insert a new row for a student in the database, returning the ID of that new row.
        // The first argument for db.insert() is the student table noreg.
        long newRowId = db.insert(StudentEntry.TABLE_NAME, null, values);
    }

    /*@Override
    public void onResume(){
        super.onResume();
        TextView mEmptyTextView =(TextView) findViewById(R.id.empty_view);
        studentListView.setEmptyView(mEmptyTextView);
        if(studentList.count()==0) {
            studentArrayAdapter = new StudentArrayAdapter(this, new ArrayList<Student>());
            mEmptyTextView.setText("No Student Found");
        } else{
            studentArrayAdapter = new StudentArrayAdapter(this, studentList.getList());
        }
        studentListView.setAdapter(studentArrayAdapter);
    }*/
/*
   private void populateStudentDummies(){
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        studentArrayList.add(new Student("1", "3145136188","TRI FEBRIANA SIAMI","0858xxxxxx","tri@mhs.unj.ac.id"));
        studentArrayList.add(new Student("2", "3145136192","Ummu Kultsum","0813xxxxxx","ummu@mhs.unj.ac.id"));
        studentList.AddStudents(studentArrayList);
        studentArrayAdapter = new StudentArrayAdapter(this,studentList.getList());
        studentListView.setAdapter(studentArrayAdapter);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu options from the res/menu/menu_student_list.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_student_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.create_dummy:
                insertStudent();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Clear List" menu option
            case R.id.clear_list:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

