package com.example.myapplication.main_menu.thirdpage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class DatabaseHelper6 private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object {


        const val DATABASE_NAME = "StudyList6.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Study_List6"
        const val COL1_ID = "_id"
        const val COL2_BOOK = "book"
        const val COL3_PUB = "publish"

        @Volatile
        private var instance: DatabaseHelper6? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(DatabaseHelper6::class.java) {
                instance ?: DatabaseHelper6(context).also {
                    instance = it
                }
            }

    }
    override fun onCreate(p0: SQLiteDatabase?) {
        val createQuery=
            "CREATE TABLE $TABLE_NAME ($COL1_ID INTEGER PRIMARY KEY ,$COL2_BOOK TEXT,$COL3_PUB TEXT)"
        p0?.execSQL(createQuery)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if(p1!=p2) {
            p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(p0)
        }
    }

    fun insertData(text0 : String,text1: String, text2: String ) {
        val db:SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues().apply() {
            put(COL1_ID,text0)
            put(COL2_BOOK,text1)
            put(COL3_PUB,text2)
        }
        db.insert(TABLE_NAME,null,contentValues)
    }

    fun deleteData(id: String)  {
        val db:SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME,"$COL1_ID=?", arrayOf(id))
    }

    fun read(): String {
        var result="구매할 책이 없습니다."

        val db=this.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM $TABLE_NAME",null)

        try {
            if (cursor.count!=0) {
                val stringBuffer=StringBuffer()
                while (cursor.moveToNext()) {
                    stringBuffer.append("  ")
                    stringBuffer.append(cursor.getInt(0).toString()+"          ")
                    stringBuffer.append(cursor.getString(1)+"           ")
                    stringBuffer.append(cursor.getString(2)+"\r\n")
                }
                result=stringBuffer.toString()
            }
        }catch (e:Exception) {
            e.printStackTrace()
        }finally {
            if (cursor!=null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return result
    }

}


