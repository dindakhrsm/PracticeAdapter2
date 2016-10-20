package com.example.acer.practiceadapter.data;

/**
 * Created by ACER on 20/10/2016.
 */

import android.provider.BaseColumns;

/**
 * API Contract for the Students app.
 */
public final class StudentContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private StudentContract() {}

    /**
     * Inner class that defines constant values for the students database table.
     * Each entry in the table represents a student's information.
     */
    public static final class StudentEntry implements BaseColumns {

        /** Name of database table for students */
        public final static String TABLE_NAME = "students";

        /**
         * Unique ID number for the student (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Registration Number of the student.
         *
         * Type: TEXT
         */
        public final static String COLUMN_STUDENT_NOREG ="noreg";

        /**
         * student's name.
         *
         * Type: TEXT
         */
        public final static String COLUMN_STUDENT_NAME = "name";

        /**
         * Student's phone number.
         *
         * Type: TEXT
         */
        public final static String COLUMN_STUDENT_PHONE = "phone";

        /**
         *Student's mail.
         *
         * Type: TEXT
         */
        public final static String COLUMN_STUDENT_MAIL = "mail";

    }

}

