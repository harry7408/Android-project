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
import com.example.myapplication.databinding.ActivityAlgorithmBinding
import com.example.myapplication.databinding.ActivityFrontendBinding
import java.lang.Exception

class Algorithm : AppCompatActivity() {
    private val binding: ActivityAlgorithmBinding by lazy {
        ActivityAlgorithmBinding.inflate(layoutInflater)
    }

    private val databaseHelper5: DatabaseHelper5 by lazy {
        DatabaseHelper5.getInstance(applicationContext)
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
        databaseHelper5.close()
        super.onDestroy()
    }
    private fun showTxt(text :String) {
        binding.List5.append(text+"\r\n")
    }

    private fun clearEditText() {
        with(binding) {
            Num5.setText("")
            Book5.setText("")
            Publish5.setText("")
        }
    }
    private fun insertDB() {
        binding.Insert5.setOnClickListener {
            try {
                databaseHelper5.insertData(
                    binding.Num5.text.toString().trim(),
                    binding.Book5.text.toString().trim(),
                    binding.Publish5.text.toString().trim()
                )
                clearEditText()
                Toast.makeText(applicationContext,"데이터 입력",Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDB() {
        binding.Delete5.setOnClickListener {
            try {
                databaseHelper5.deleteData(binding.Num5.text.toString().trim())
                clearEditText()
                Toast.makeText(applicationContext,"삭제됨",Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDB() {
        binding.Show5.setOnClickListener {
            try {
                val selectResult =databaseHelper5.read()
                Toast.makeText(applicationContext,"저장된 목록입니다",Toast.LENGTH_SHORT).show()
                showTxt(selectResult)
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchWEB() {
        binding.Search5.setOnClickListener {
            val b =binding.Book5
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, b.text.toString())
            startActivity(intent)
        }

    }
}

