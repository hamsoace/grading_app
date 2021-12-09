package com.hamsoace.AppForAll.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hamsoace.AppForAll.models.Marks;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "appforall.db";
    public static final String TABLE_MARKS = "marks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STUDENT_FIRST_NAME = "studentFirstName";
    public static final String COLUMN_STUDENT_LAST_NAME = "studentLastName";
    public static final String COURSE = "COURSE";
    public static final String CREDIT = "CREDIT";
    public static final String TOTAL_MARKS = "totalMarks";



    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_MARKS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STUDENT_FIRST_NAME + " TEXT , "
                + COLUMN_STUDENT_LAST_NAME + " TEXT , "
                + COURSE + " TEXT , "
                + CREDIT + " TEXT , "
                + TOTAL_MARKS + " TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKS);
        onCreate(db);
    }

    public void addMarks(Marks marks) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_STUDENT_FIRST_NAME, marks.getFirstName());
        values.put(COLUMN_STUDENT_LAST_NAME, marks.getLastName());
        values.put(COURSE, marks.getMarks());
        values.put(CREDIT, marks.getCredits());
        values.put(TOTAL_MARKS, marks.getMarks());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_MARKS, null, values);
        db.close();
    }


    public void deleteMarks(String studentFirstName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MARKS + " WHERE " + COLUMN_STUDENT_FIRST_NAME + "=\"" + studentFirstName + "\"");
//        db.execSQL("DELETE FROM " + TABLE_MARKS + " WHERE " + COLUMN_STUDENT_LAST_NAME + "=\"" + studentLastName + "\"");
    }

    public String databaseToString() {
        String DBString = "";
        String course;
        Integer average, credit, totalMarks;
        Float percentage;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MARKS;

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_STUDENT_FIRST_NAME)) != null) {
                DBString += String.format("%10.10s", c.getString(c.getColumnIndex(COLUMN_STUDENT_FIRST_NAME)));
                DBString += "   ";

                DBString += String.format("%10.10s", c.getString(c.getColumnIndex(COLUMN_STUDENT_LAST_NAME)));
                DBString += "   ";

                // get all subject marks
                credit = Integer.valueOf(c.getString(c.getColumnIndex(CREDIT)));
                totalMarks = Integer.valueOf(c.getString(c.getColumnIndex(TOTAL_MARKS)));


                DBString += (c.getString(c.getColumnIndex(CREDIT)) + " ");
                DBString += (c.getString(c.getColumnIndex(TOTAL_MARKS)) + " ");


//                // add percentage without credits
//                average = credit + totalMarks;
//                percentage = average.floatValue() / 4;
//                DBString += (String.format("%.2f", percentage) + " ");
//
//                // add percentage with credits
//                average = 4 * credit;
//                percentage = average.floatValue() / 15;
//                DBString += String.format("%.2f", percentage);
//                DBString += "\n";

            }
            c.moveToNext();
        }
        return DBString;
    }
}
