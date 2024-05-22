package com.example.reader;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "reader";     // or reader.db
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "url_list";
    private static final String SRL_NO = "sno";
    private static final String WEB_NAME = "website_name";
    private static final String WEB_LINK = "web_link";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + SRL_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WEB_NAME + " TEXT, "
                + WEB_LINK + " TEXT)";
        db.execSQL(query);
    }

    public void addUrl(String website_name, String website_link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WEB_NAME, website_name);
        values.put(WEB_LINK, website_link);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void deleteUrl(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, SRL_NO + "=?", new String[]{String.valueOf(id)});
        db.close();
    }


    @SuppressLint("Range")
    public List<UrlData> getAllUrls() {
        List<UrlData> urlList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{SRL_NO, WEB_NAME, WEB_LINK},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                UrlData urlData = new UrlData();
                urlData.setId(cursor.getInt(cursor.getColumnIndex(SRL_NO)));
                urlData.setWebsiteName(cursor.getString(cursor.getColumnIndex(WEB_NAME)));
                urlData.setWebsiteLink(cursor.getString(cursor.getColumnIndex(WEB_LINK)));
                urlList.add(urlData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return urlList;
    }
}
