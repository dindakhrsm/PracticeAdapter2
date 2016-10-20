package com.example.acer.practiceadapter.data;

/**
 * Created by ACER on 20/10/2016.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.acer.practiceadapter.data.StudentContract.StudentEntry;

/**
 * Database helper for Students app. Manages database creation and version management.
 */
public class StudentDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = StudentDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "shelter.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link StudentDbHelper}.
     *
     * @param context of the app
     */
    public StudentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the students table
        String SQL_CREATE_STUDENTS_TABLE =  "CREATE TABLE " + StudentEntry.TABLE_NAME + " ("
                + StudentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StudentEntry.COLUMN_STUDENT_NOREG + " TEXT NOT NULL, "
                + StudentEntry.COLUMN_STUDENT_NAME + " TEXT, "
                + StudentEntry.COLUMN_STUDENT_PHONE + " TEXT NOT NULL, "
                + StudentEntry.COLUMN_STUDENT_MAIL + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_STUDENTS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}