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
                    " price DOUBLE);"
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
            db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            db?.execSQL(CREATE_DB_TABLE)
        }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            onCreate(db)
        }

    }


        override fun onCreate(): Boolean {
            db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            val context = context
            val dbHelper = DatabaseHelper(context)
            db = dbHelper.writableDatabase
            return if (db == null) false else true
    }

    override fun query(
        uri: Uri, projection: Array<String>?,
        selection: String?, selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var sortOrder = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = TABLE_NAME
        if (uriMatcher != null) {
            when (uriMatcher.match(uri)) {
                /* STUDENTS -> qb.projectionMap =
                    STUDENTS_PROJECTION_MAP */
                ORDERS_ID -> qb.appendWhere(_ID + "=" + uri.pathSegments[1])
                else -> {null
                }
            }
        }


        if (sortOrder == null || sortOrder === "") {
            /**
             * By default sort on student names
             */
            sortOrder = _ID
        }
        val c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder)
        /**
         * register to watch a content URI for changes  */
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher!!.match(uri)) {
            ORDERS -> return "These info about students"
            ORDERS_ID -> return "This info about specific studentstudents"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
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
        count = db!!.delete(
                TABLE_NAME, selection,
                selectionArgs
            )
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        var count = 0
        count = db!!.update(
            TABLE_NAME, values, selection,
            selectionArgs)
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

}