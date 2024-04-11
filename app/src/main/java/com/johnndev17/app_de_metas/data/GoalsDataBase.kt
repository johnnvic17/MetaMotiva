package com.johnndev17.app_de_metas.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.johnndev17.app_de_metas.model.GoalsConstants

class GoalsDataBase(context: Context): SQLiteOpenHelper(context, TABLE_NAME, null, VERSION_DB){

    companion object{

        const val TABLE_NAME = "GoalsDB"
        const val VERSION_DB = 1

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE " + GoalsConstants.KEY.TABLE_NAME + "(" + GoalsConstants.KEY.COLUMNS.ID +
                " integer primary key autoincrement, "+ GoalsConstants.KEY.COLUMNS.NAME + " string," +
                GoalsConstants.KEY.COLUMNS.PROGRESS + " integer);")

        db.execSQL("CREATE TABLE LoginDB(id integer primary key autoincrement, userName string);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}