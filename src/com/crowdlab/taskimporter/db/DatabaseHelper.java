/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowdlab.taskimporter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *
 * @author anwar
 *
 * Singleton object
 *
 */
public class DatabaseHelper extends CrowdlabDatabaseHelper {

    private static DatabaseHelper instance;

    /*
     * Constructor
     * @param context, Android context
     */
    private DatabaseHelper(Context context) {
        super(context);
    }

    /*
     * Create singleton instance
     * @param context, Android context
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    /*
     * Will create tables and indexes
     * @param db, SQLite database instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        initialiseTables(db);
        initialiseIndexes(db);

    }

    /*
     * If any change to table or indexes, increase the database version number
     * Therefore we can perform some dirty job if required and re-create tables and indexs
     * Any app required login, donot drop any user tables
     * @param db, database instance
     * @param oldVersion, cached old version number
     * @param newVersion, new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            dirty(db);
            initialiseTables(db);
            initialiseIndexes(db);
        }

    }

    /*
     * Droping tables on database version change
     * @param db, SQLite database instance
     */
    private void dirty(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS tblTask;");
        db.execSQL("DROP TABLE IF EXISTS tblQuestion;");
        db.execSQL("DROP TABLE IF EXISTS tblOption;");
    }

    /*
     * Initialize all the tables
     * @param db, SQLite database instance
     */
    @Override
    protected void initialiseTables(SQLiteDatabase db) {
        db.beginTransaction();
        {
            /*
             * Creating 'tblTask' table, where task_id is the primary key of the table 
             */

            createTable(db, "tblTask", "task_id INTEGER PRIMARY KEY", "title TEXT", "hidden INTEGER");

            /*
             * Creating 'tblQuestion' table, where question_id is the primary key of the table 
             * and task_id is the foreign key, which create the link between
             * tblTask and tblQuestion
             */
            createTable(db, "tblQuestion", "question_id INTEGER PRIMARY KEY", "title TEXT", "summary TEXT", "task_id INTEGER");

            /*
             * Creating 'tblOption' table, where option_id is the primary key of the table
             * and question_id is the foreign key, which create the link between
             * tblQuestion and tblOption
             */
            createTable(db, "tblOption", "option_id INTEGER PRIMARY KEY", "type TEXT", "label TEXT" , "question_id INTEGER");

            /*
             * Creating 'tblOption' table, where option_id is the primary key of the table 
             */
            createTable(db, "tblLastJson", "_id INTEGER PRIMARY KEY", "last_json_txt TEXT");
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /*
     * Initialize all the indexes
     * @param db, SQLite database instance
     */
    @Override
    protected void initialiseIndexes(SQLiteDatabase db) {
        db.beginTransaction();
        {
            /*
             * Creating unique index, name = task_index on the tblTask tables
             * column name = question_id
             */

            createIndex(db, "task_index", "tblTask(question_id)", true);

            /*
             * Creating index, name = task_index on the tblQuestion table
             * Column name = option_id
             */
            createIndex(db, "question_index", "tblQuestion(option_id)", false);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }
    
    public boolean insert(String tableName, ContentValues values) {
        return this.getWritableDatabase().insert(tableName, null, values) > 0;
    }
    
    
    public boolean delete(String tableName, String whereClause) {
        return this.getWritableDatabase().delete(tableName, whereClause, null) > 0;
    }
    
    public Cursor rawQuery(String stm) {
       return this.getReadableDatabase().rawQuery(stm, null);
    }

}
