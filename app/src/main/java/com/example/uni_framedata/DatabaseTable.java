package com.example.uni_framedata;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
    public static final String COL_STARTUP = "STARTUP";
    public static final String COL_ACTIVE = "ACTIVE";
    public static final String COL_RECOVERY = "RECOVERY";
    public static final String COL_FRAMEADV = "FRAMEADV";
    public static final String COL_CANCEL = "CANCEL";
    public static final String COL_GUARD = "GUARD";
    public static final String COL_ATTRIBUTE = "ATTRIBUTE";


    private static final String DATABASE_NAME = "UNI_DB";
    private static final String FTS_VIRTUAL_TABLE = "FTS";
    private static final int DATABASE_VERSION = 1;

    private final DatabaseOpenHelper databaseOpenHelper;

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
                        COL_DAMAGE + ", " +
                        COL_STARTUP + ", " +
                        COL_ACTIVE + ", " +
                        COL_RECOVERY + ", " +
                        COL_FRAMEADV + ", " +
                        COL_CANCEL + ", " +
                        COL_GUARD + ", " +
                        COL_ATTRIBUTE + ")";

        DatabaseOpenHelper(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
            myContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            myDatabase = db;
            myDatabase.execSQL("");
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
            final Resources resources = helperContext.getResources();
            InputStream inputStream = resources.openRawResource(R.raw.definitions);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] strings = TextUtils.split(line, "-");
                    if (strings.length < 2) continue;
//                    long id = addWord(strings[0].trim(), strings[1].trim());
                    if (id < 0) {
                        Log.e(TAG, "unable to add word: " + strings[0].trim());
                    }
                }
            } finally {
                reader.close();
            }
        }

        public long addWord(String character, String input, String damage,
                            String startup, String active, String recovery
                , String frameadv, String cancel, String guard, String attribute) {
            ContentValues initialValues = new ContentValues();
            initialValues.put(COL_CHARACTER, character);
            initialValues.put(COL_INPUT, input);
            initialValues.put(COL_DAMAGE, damage);
            initialValues.put(COL_STARTUP, startup);
            initialValues.put(COL_ACTIVE, active);
            initialValues.put(COL_RECOVERY, recovery);
            initialValues.put(COL_FRAMEADV, frameadv);
            initialValues.put(COL_CANCEL, cancel);
            initialValues.put(COL_GUARD, guard);
            initialValues.put(COL_ATTRIBUTE, attribute);

            return myDatabase.insert(FTS_VIRTUAL_TABLE, null, initialValues);
        }

    }
}
