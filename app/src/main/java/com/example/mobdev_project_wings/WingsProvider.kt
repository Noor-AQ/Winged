package com.example.mobdev_project_wings

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.text.TextUtils
import java.lang.IllegalArgumentException
import java.util.HashMap

class WingsProvider():ContentProvider() {

    companion object {
        val PROVIDER_NAME = "com.example.mobdev_project_wings.WingsProvider"
        val URL = "content://" + PROVIDER_NAME + "/order"
        val CONTENT_URI = Uri.parse(URL)
        val _ID = "_id"
        val PRICE = "price"
        val QUANTITY = "quantity"
        val TYPE = "quantity"
        val NAME = "name"
        //private val STUDENTS_PROJECTION_MAP: HashMap<String, String>? = null
        val order = 1
        val order_ID = 2
        val uriMatcher: UriMatcher? = null
        val DATABASE_NAME = "wings"
        val TABLE_NAME = "order"
        val DATABASE_VERSION = 1
        //can have without name column maybe??
        val CREATE_DB_TABLE =
            " CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY, "+ " quantity INTEGER NOT NULL, "  +" type TEXT NOT NULL, " + // " name TEXT, "
                    " price INTEGER NOT NULL);"
        private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH);
        init
        {
            sUriMatcher.addURI(PROVIDER_NAME, "order", order);
            sUriMatcher.addURI(PROVIDER_NAME, "order/#", order_ID);
        }
    }
    private var db: SQLiteDatabase? = null
    private class DatabaseHelper internal constructor(context: Context?):
        SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_DB_TABLE)
        }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            onCreate(db)
        }

    }


        override fun onCreate(): Boolean {
            val context = context
            val dbHelper = DatabaseHelper(context!!)

            db = dbHelper.writableDatabase
            return if (db == null) false else true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
//        val rowID = db!!.insert(TABLE_NAME, "", values)
//        if (rowID > 0) {
//            val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
//            context!!.contentResolver.notifyChange(_uri, null)
//            return _uri
//        }
//        throw SQLException("Failed to add a record into $uri")
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

}