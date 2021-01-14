package com.example.myapplication.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private void insert(String op1, String op2, String function, String result) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.OPERAND1, op1);
        contentValue.put(DatabaseHelper.OPERAND2, op2);
        contentValue.put(DatabaseHelper.FUNCTION, function);
        contentValue.put(DatabaseHelper.RESULT, result);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    private Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID,
                DatabaseHelper.OPERAND1,
                DatabaseHelper.OPERAND2,
                DatabaseHelper.FUNCTION,
                DatabaseHelper.RESULT};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    private int update(long _id, String op1, String op2, String function, String result) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.OPERAND1, op1);
        contentValues.put(DatabaseHelper.OPERAND2, op2);
        contentValues.put(DatabaseHelper.FUNCTION, function);
        contentValues.put(DatabaseHelper.RESULT, result);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    private void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }


    public void insert(HistoryItem item) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.OPERAND1, item.getOperand1());
        contentValue.put(DatabaseHelper.OPERAND2, item.getOperand2());
        contentValue.put(DatabaseHelper.FUNCTION, item.getFunction());
        contentValue.put(DatabaseHelper.RESULT, item.getResult());
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public void deleteAll() {
        database.delete(DatabaseHelper.TABLE_NAME,"",null);
    }

    public String getAllAsText(){
        String text = "";
        Cursor cursor = fetch();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String op1, op2, function, result;
            op1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.OPERAND1));
            op2 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.OPERAND2));
            function = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FUNCTION));
            result = cursor.getString(cursor.getColumnIndex(DatabaseHelper.RESULT));
            HistoryItem item = new HistoryItem(op1, op2, function, result);
            text += (item.getTextRepresentation() + "\n");
            cursor.moveToNext();
        }
        return text;
    }
}
