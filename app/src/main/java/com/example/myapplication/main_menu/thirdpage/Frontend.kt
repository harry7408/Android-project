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
import java.lang.Exception

class Frontend : AppCompatActivity() {
    private val binding: ActivityFrontendBinding by lazy {
        ActivityFrontendBinding.inflate(layoutInflater)
    }

    private val databaseHelper0: DatabaseHelper0 by lazy {
        DatabaseHelper0.getInstance(applicationContext)
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
        databaseHelper0.close()
        super.onDestroy()
    }
    private fun showTxt(text :String) {
        binding.List0.append(text+"\r\n")
    }

    private fun clearEditText() {
        with(binding) {
            Num0.setText("")
            Book0.setText("")
            Publish0.setText("")
        }
    }
    private fun insertDB() {
        binding.Insert0.setOnClickListener {
            try {
                databaseHelper0.insertData(
                   binding.Num0.text.toString().trim(),
                    binding.Book0.text.toString().trim(),
                    binding.Publish0.text.toString().trim()
                )
                clearEditText()
                Toast.makeText(applicationContext,"데이터 입력",Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDB() {
        binding.Delete0.setOnClickListener {
            try {
                databaseHelper0.deleteData(binding.Num0.text.toString().trim())
                clearEditText()
                Toast.makeText(applicationContext,"삭제됨",Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDB() {
        binding.Show0.setOnClickListener {
            try {
                val selectResult =databaseHelper0.read()
                Toast.makeText(applicationContext,"저장된 목록입니다",Toast.LENGTH_SHORT).show()
                showTxt(selectResult)
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchWEB() {
        binding.Search0.setOnClickListener {
            val b =binding.Book0
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, b.text.toString())
            startActivity(intent)
        }

    }
}

