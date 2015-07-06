/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crowdlab.taskimporter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.crowdlab.taskimporter.utils.Logger;

/**
 *
 * @author anwar
 *
 * A wrapper class
 */
public abstract class CrowdlabDatabaseHelper extends SQLiteOpenHelper {

    /*
     * Name of the database
     */
    private static final String DATABASE_NAME = "taskimporter.db";

    /*
     * Database version
     * Any changes on the tables on indexes, required to increase the version number
     */
    private static final int DATABASE_VERSION = 1;

    /*
     * Statment to create table
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS %s(%s);";

    public CrowdlabDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }

    /*
     * Method to create tables on the database
     * @param db, SQLite database instance
     * @param tables, name of the table
     * @param columndefs, Array of columns
     */
    protected boolean createTable(SQLiteDatabase db, String table, String... columndefs) {
        if (table != null) {
            String columns = "";

            String columndef;
            for (int i = 0; i < columndefs.length; i++) {
                columndef = columndefs[i].trim();
                if (i < columndefs.length - 1 && !columndef.endsWith(",")) {
                    columndef += ",";
                }

                columns += columndef;
            }

            try {
                if (db == null) {
                    getWritableDatabase().execSQL(String.format(CREATE_TABLE, table, columns));
                } else {
                    String sql = "CREATE TABLE IF NOT EXISTS " + table + "(" + columns + ");";
                    db.execSQL(sql);
                }
            } catch (Exception e) {
                Logger.e(e.toString() + ":::" + table + "");
                return false;
            }

            return true;
        }

        return false;
    }
    /*
     * Method to create index for the table 
     * @param db, SQLite database instance
     * @param indexname, name of the index
     * @param on, name of the column will be effected
     * @param unique, whether the index will be unique or not
     */

    protected boolean createIndex(SQLiteDatabase db, String indexname, String on, boolean unique) {
        if (indexname == null || on == null) {
            return false;
        }

        String index;
        if (unique) {
            index = "CREATE UNIQUE INDEX IF NOT EXISTS " + indexname + " ON " + on + ";";
        } else {
            index = "CREATE INDEX IF NOT EXISTS " + indexname + " ON " + on + ";";
        }

        try {
            if (db == null) {
                getWritableDatabase().execSQL(index);
            } else {
                db.execSQL(index);
            }
        } catch (Exception ex) {
            Logger.e(ex.toString() + ":::" + indexname + "");
            return false;
        }

        return true;
    }

    protected void initialiseIndexes(SQLiteDatabase db) {
    }

    protected void initialiseTables(SQLiteDatabase db) {
    }
}
