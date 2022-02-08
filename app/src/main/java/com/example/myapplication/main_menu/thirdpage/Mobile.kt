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
import com.example.myapplication.databinding.ActivityMobileBinding
import java.lang.Exception

class Mobile : AppCompatActivity() {
    private val binding: ActivityMobileBinding by lazy {
        ActivityMobileBinding.inflate(layoutInflater)
    }

    private val databaseHelper2: DatabaseHelper2 by lazy {
        DatabaseHelper2.getInstance(applicationContext)
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
        databaseHelper2.close()
        super.onDestroy()
    }
    private fun showTxt(text :String) {
        binding.List2.append(text+"\r\n")
    }

    private fun clearEditText() {
        with(binding) {
            Num2.setText("")
            Book2.setText("")
            Publish2.setText("")
        }
    }
    private fun insertDB() {
        binding.Insert2.setOnClickListener {
            try {
                databaseHelper2.insertData(
                    binding.Num2.text.toString().trim(),
                    binding.Book2.text.toString().trim(),
                    binding.Publish2.text.toString().trim()
                )
                clearEditText()
                Toast.makeText(applicationContext,"데이터 입력",Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDB() {
        binding.Delete2.setOnClickListener {
            try {
                databaseHelper2.deleteData(binding.Num2.text.toString().trim())
                clearEditText()
                Toast.makeText(applicationContext,"삭제됨",Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDB() {
        binding.Show2.setOnClickListener {
            try {
                val selectResult =databaseHelper2.read()
                Toast.makeText(applicationContext,"저장된 목록입니다",Toast.LENGTH_SHORT).show()
                showTxt(selectResult)
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchWEB() {
        binding.Search2.setOnClickListener {
            val b =binding.Book2
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, b.text.toString())
            startActivity(intent)
        }

    }
}

