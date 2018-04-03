package com.example.ailan.sqlitefinalproject;

/**
 * Created by ailan on 03/04/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by ailan on 03/04/2018.
 */

public class AccountOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME = "Account.db";
    public static final String TABLE_ACCOUNT = "tblaccount";
    public static final int DATABASEVERSION = 1;

    public static final String COLUMN_ID = "AccID";
    public static final String COLUMN_FIRSTNAME = "Firstname";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_DISPLAYNAME = "Displayname";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_ACCSPECID = "AccSpecId";

    public static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE IF NOT EXISTS " +
            TABLE_ACCOUNT + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRSTNAME + " VARCHAR," + COLUMN_LASTNAME + " VARCHAR,"
            + COLUMN_DISPLAYNAME + " VARCHAR," + COLUMN_EMAIL + " VARCHAR " + COLUMN_ACCSPECID + " VARCHAR" + ");";


    String[] allColumns = {AccountOpenHelper.COLUMN_ID, AccountOpenHelper.COLUMN_FIRSTNAME, AccountOpenHelper.COLUMN_LASTNAME,
            AccountOpenHelper.COLUMN_EMAIL, AccountOpenHelper.COLUMN_DISPLAYNAME, AccountOpenHelper.COLUMN_ACCSPECID};

    SQLiteDatabase database;

    public AccountOpenHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("mosh", "Table customer created 1");

        db.execSQL(CREATE_TABLE_ACCOUNT);
        Log.d("mosh", "Table customer created 2");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        onCreate(db);
    }

    public void open() {
        database = this.getWritableDatabase();
        Log.i("data", "Database connection open");
    }

    public Account createAccount(Account c) {
        ContentValues values = new ContentValues();
        values.put(AccountOpenHelper.COLUMN_ID, c.getId());
        values.put(AccountOpenHelper.COLUMN_FIRSTNAME, c.getFirstName());
        values.put(AccountOpenHelper.COLUMN_LASTNAME, c.getLastName());
        values.put(AccountOpenHelper.COLUMN_DISPLAYNAME, c.getDisplayName());
        values.put(AccountOpenHelper.COLUMN_EMAIL, c.getEmail());
        values.put(AccountOpenHelper.COLUMN_ACCSPECID, c.getAccSpecId());

        long insertId = database.insert(AccountOpenHelper.TABLE_ACCOUNT, null, values);
        Log.i("data", "AccountId " + insertId + "insert to database");
        c.setId(insertId);
        return c;

    }
}

