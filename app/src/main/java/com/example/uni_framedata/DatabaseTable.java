package com.example.uni_framedata;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatabaseTable {

    private static final String TAG = "UNIDatabase";
    // COLUMNS:
    public static final String COL_CHARACTER = "CHARACTER";
    public static final String COL_NAME = "NAME";
    public static final String COL_INPUT = "INPUT";
    public static final String COL_DAMAGE = "DAMAGE";
    public static final String COL_GUARD = "GUARD";
    public static final String COL_STARTUP = "STARTUP";
    public static final String COL_ACTIVE = "ACTIVE";
    public static final String COL_RECOVERY = "RECOVERY";
    public static final String COL_OVERALLFRAME = "OVERALLFRAME";
    public static final String COL_FRAMEADV = "FRAMEADV";
    public static final String COL_CANCEL = "CANCEL";
    public static final String COL_INVICIBILITY = "INVICIBILITY";
    public static final String COL_ATTRIBUTE = "ATTRIBUTE";


    private static final String DATABASE_NAME = "UNI_DB";
    private static final String FTS_VIRTUAL_TABLE = "FTS";
    private static final int DATABASE_VERSION = 1;

    private final DatabaseOpenHelper databaseOpenHelper;

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(FTS_VIRTUAL_TABLE);

        Cursor cursor = builder.query(databaseOpenHelper.getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }
    public Cursor getCharacterMatches(String query, String[] columns) {
        String selection = COL_CHARACTER + " MATCH ?";
        String[] selectionArgs = new String[] {query+"*"};

        return query(selection, selectionArgs, columns);
    }

    public DatabaseTable(Context context) {
        databaseOpenHelper = new DatabaseOpenHelper(context);
    }

    private static class DatabaseOpenHelper extends SQLiteOpenHelper {

        private final Context myContext;
        private SQLiteDatabase myDatabase;

        private static final String FTS_TABLE_CREATE =
                "CREATE VIRTUAL TABLE " + FTS_VIRTUAL_TABLE +
                        " USING fts3 (" +
                        COL_CHARACTER + ", " +
                        COL_NAME + ", " +
                        COL_INPUT + ", " +
                        COL_DAMAGE + ", " +
                        COL_GUARD + ", " +
                        COL_STARTUP + ", " +
                        COL_ACTIVE + ", " +
                        COL_RECOVERY + ", " +
                        COL_OVERALLFRAME + ", " +
                        COL_FRAMEADV + ", " +
                        COL_CANCEL + ", " +
                        COL_INVICIBILITY + ", " +
                        COL_ATTRIBUTE + ")";

        DatabaseOpenHelper(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
            myContext = context;
            getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            myDatabase = db;
            myDatabase.execSQL(FTS_TABLE_CREATE);
            loadDictionary();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
            onCreate(db);
        }

        private void loadDictionary() {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        loadWords();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }

        private void loadWords() throws IOException {
            final Resources resources = myContext.getResources();
            InputStream inputStream = resources.openRawResource(R.raw.framedata);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] strings = TextUtils.split(line, "  ");
                    if (strings.length < 2) continue;
                    String character,name, input, damage, startup, active, recovery, frameadv,cancel,guard,attribute,invincibility;
                    int i = 0;
                    character = strings[i++];
                    name = strings[i++];
                    if (isNumeric(strings[i+1])) {
                        input = "";
                    } else {
                        input = strings[i++];
                    }
                    damage = strings[i++];
                    guard = strings[i++];
                    startup = strings[i++];
                    active = strings[i++];
                    recovery = strings[i++];
                    frameadv = strings[i++];
                    cancel = strings[i++];
                    if(i+1 < strings.length && isNumeric(strings[i+1].substring(0,1))) {
                        invincibility = strings[i++];
                    } else {
                        invincibility = "None";
                    }
                    if(i+1 < strings.length){
                        attribute = strings[i++];
                    } else {
                        attribute = "None";
                    }
                    long id = addWord(character,name,input,damage,startup,active,recovery,frameadv,cancel,guard,attribute,invincibility);
                    if (id < 0) {
                        Log.e(TAG, "unable to add word: " + strings[0].trim());
                    }
                }
            } finally {
                reader.close();
            }
        }

        public long addWord(String character, String name, String input, String damage,
                            String startup, String active, String recovery
                , String frameadv, String cancel, String guard, String attribute, String invincibility) {
            ContentValues initialValues = new ContentValues();
            initialValues.put(COL_CHARACTER, character);
            initialValues.put(COL_NAME, name);
            initialValues.put(COL_INPUT, input);
            initialValues.put(COL_DAMAGE, damage);
            initialValues.put(COL_STARTUP, startup);
            initialValues.put(COL_ACTIVE, active);
            initialValues.put(COL_RECOVERY, recovery);
            initialValues.put(COL_FRAMEADV, frameadv);
            initialValues.put(COL_CANCEL, cancel);
            initialValues.put(COL_GUARD, guard);
            initialValues.put(COL_ATTRIBUTE, attribute);
            initialValues.put(COL_INVICIBILITY, invincibility);

            return myDatabase.insert(FTS_VIRTUAL_TABLE, null, initialValues);
        }

        public static boolean isNumeric(String strNum) {
            return strNum.matches("-?\\d+(\\.\\d+)?");
        }



    }
}
