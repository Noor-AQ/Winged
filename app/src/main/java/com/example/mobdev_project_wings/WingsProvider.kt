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
        val URL = "content://" + PROVIDER_NAME + "/orders"
        val CONTENT_URI = Uri.parse(URL)
        val _ID = "_id"
        val PRICE = "price"
        val QUANTITY = "quantity"
        val TYPES = "types"
        val SAUCES = "sauces"
        val DRINKS = "drinks"
//        private val STUDENTS_PROJECTION_MAP: HashMap<String, String>? = null
        val ORDERS = 1
        val ORDERS_ID = 2
        val uriMatcher: UriMatcher? = null
        val DATABASE_NAME = "restaurant"
        val TABLE_NAME = "orders"
        val DATABASE_VERSION = 1
        //can have without name column maybe??
        val CREATE_DB_TABLE =
            " CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ " quantity INTEGER, "  +" types TEXT, " + " sauces TEXT, " + "drinks TEXT," +
                    " price INTEGER);"
        private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH);
        init
        {
            sUriMatcher.addURI(PROVIDER_NAME, "orders", ORDERS);
            sUriMatcher.addURI(PROVIDER_NAME, "orders/#", ORDERS_ID);
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
            val dbHelper = DatabaseHelper(context)
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
        val rowID = db!!.insert(TABLE_NAME, "", values)
        if (rowID > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }
        throw SQLException("Failed to add a record into $uri")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0
        when (uriMatcher!!.match(uri)) {
            ORDERS -> count = db!!.delete(
                TABLE_NAME, selection,
                selectionArgs
            )
            ORDERS_ID -> {
                val id = uri.pathSegments[1]
                count = db!!.delete(
                    TABLE_NAME,
                    _ID + " = " + id +
                            if (!TextUtils.isEmpty(selection)) " AND ($selection)" else "",
                    selectionArgs
                )
            }
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        var count = 0
        when (uriMatcher!!.match(uri)) {
            ORDERS -> count = db!!.update(
                TABLE_NAME, values, selection,
                selectionArgs
            )
            ORDERS_ID -> count = db!!.update(
                TABLE_NAME,
                values,
                _ID + " = " + uri.pathSegments[1] + (if (!TextUtils.isEmpty(selection)) " AND ($selection)" else ""),
                selectionArgs
            )
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

}