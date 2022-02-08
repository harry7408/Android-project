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
import com.example.myapplication.databinding.ActivityBackendBinding
import com.example.myapplication.databinding.ActivityFrontendBinding
import java.lang.Exception

class Backend : AppCompatActivity() {
    private val binding: ActivityBackendBinding by lazy {
        ActivityBackendBinding.inflate(layoutInflater)
    }

    private val databaseHelper1: DatabaseHelper1 by lazy {
        DatabaseHelper1.getInstance(applicationContext)
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
        databaseHelper1.close()
        super.onDestroy()
    }
    private fun showTxt(text :String) {
        binding.List1.append(text+"\r\n")
    }

    private fun clearEditText() {
        with(binding) {
            Num1.setText("")
            Book1.setText("")
            Publish1.setText("")
        }
    }
    private fun insertDB() {
        binding.Insert1.setOnClickListener {
            try {
                databaseHelper1.insertData(
                    binding.Num1.text.toString().trim(),
                    binding.Book1.text.toString().trim(),
                    binding.Publish1.text.toString().trim()
                )
                clearEditText()
                Toast.makeText(applicationContext,"데이터 입력",Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDB() {
        binding.Delete1.setOnClickListener {
            try {
                databaseHelper1.deleteData(binding.Num1.text.toString().trim())
                clearEditText()
                Toast.makeText(applicationContext,"삭제됨",Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDB() {
        binding.Show1.setOnClickListener {
            try {
                val selectResult =databaseHelper1.read()
                Toast.makeText(applicationContext,"저장된 목록입니다",Toast.LENGTH_SHORT).show()
                showTxt(selectResult)
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchWEB() {
        binding.Search1.setOnClickListener {
            val b =binding.Book1
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, b.text.toString())
            startActivity(intent)
        }

    }
}

