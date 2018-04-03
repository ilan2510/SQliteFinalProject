package com.example.ailan.sqlitefinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ailan on 03/04/2018.
 */

public class MessageOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME = "message.db2";
    public static final String TABLE_MESSAGE = "tblmessage";
    public static final int DATABASEVERSION = 1;

    public static final String COLUMN_ID = "messageID";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_SAVEDAY = "msgSaveDay";
    public static final String COLUMN_SAVEHOUR = "msgSaveHour";
    public static final String COLUMN_FROMCHAT = "fromWho";

    public static final String CREATE_TABLE_MESSAGE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_MESSAGE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MESSAGE + " VARCHAR," + COLUMN_SAVEDAY + " VARCHAR,"
            + COLUMN_SAVEHOUR + " VARCHAR," + COLUMN_FROMCHAT + " VARCHAR " + ");";


    String[] allColumns = {MessageOpenHelper.COLUMN_ID, MessageOpenHelper.COLUMN_MESSAGE, MessageOpenHelper.COLUMN_SAVEDAY,
            MessageOpenHelper.COLUMN_SAVEHOUR, MessageOpenHelper.COLUMN_FROMCHAT};

    SQLiteDatabase database;

    public MessageOpenHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("mosh", "Table customer created 1");

        db.execSQL(CREATE_TABLE_MESSAGE);
        Log.d("mosh", "Table customer created 2");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGE);
        onCreate(db);
    }

    public void open() {
        database = this.getWritableDatabase();
        Log.i("data", "Database connection open");
    }

    public Message createMessage(Message c) {
        ContentValues values = new ContentValues();
        values.put(MessageOpenHelper.COLUMN_ID, c.getmessageId());
        values.put(MessageOpenHelper.COLUMN_MESSAGE, c.getMessage());
        values.put(MessageOpenHelper.COLUMN_SAVEDAY, c.getMsgSaveDay());
        values.put(MessageOpenHelper.COLUMN_SAVEHOUR, c.getMsgSaveHour());
        values.put(MessageOpenHelper.COLUMN_FROMCHAT, c.getFromWhichChat());

        long insertId = database.insert(MessageOpenHelper.TABLE_MESSAGE, null, values);
        Log.i("data", "Customer " + insertId + "insert to database");
        c.setmessageId(insertId);
        return c;

    }
}