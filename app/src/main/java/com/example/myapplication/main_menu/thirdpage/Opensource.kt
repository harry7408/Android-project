package com.example.myapplication.main_menu.thirdpage

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteProgram
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityFrontendBinding
import com.example.myapplication.databinding.ActivityOpensourceBinding
import java.lang.Exception

class Opensource : AppCompatActivity() {
    private val binding: ActivityOpensourceBinding by lazy {
        ActivityOpensourceBinding.inflate(layoutInflater)
    }

    private val databaseHelper6: DatabaseHelper6 by lazy {
        DatabaseHelper6.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        insertDB()
        deleteDB()
        showDB()
        searchWEB()
    }

    override fun onDestroy() {
        databaseHelper6.close()
        super.onDestroy()
    }
    private fun showTxt(text :String) {
        binding.List6.append(text+"\r\n")
    }

    private fun clearEditText() {
        with(binding) {
            Num6.setText("")
            Book6.setText("")
            Publish6.setText("")
        }
    }
    private fun insertDB() {
        binding.Insert6.setOnClickListener {
            try {
                databaseHelper6.insertData(
                    binding.Num6.text.toString().trim(),
                    binding.Book6.text.toString().trim(),
                    binding.Publish6.text.toString().trim()
                )
                clearEditText()
                Toast.makeText(applicationContext,"데이터 입력",Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDB() {
        binding.Delete6.setOnClickListener {
            try {
                databaseHelper6.deleteData(binding.Num6.text.toString().trim())
                clearEditText()
                Toast.makeText(applicationContext,"삭제됨",Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDB() {
        binding.Show6.setOnClickListener {
            try {
                val selectResult =databaseHelper6.read()
                Toast.makeText(applicationContext,"저장된 목록입니다",Toast.LENGTH_SHORT).show()
                showTxt(selectResult)
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchWEB() {
        binding.Search6.setOnClickListener {
            val b =binding.Book6
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, b.text.toString())
            startActivity(intent)
        }

    }
}

