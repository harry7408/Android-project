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
import com.example.myapplication.databinding.ActivityGameBinding
import java.lang.Exception

class Game : AppCompatActivity() {
    private val binding: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    private val databaseHelper3: DatabaseHelper3 by lazy {
        DatabaseHelper3.getInstance(applicationContext)
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
        databaseHelper3.close()
        super.onDestroy()
    }
    private fun showTxt(text :String) {
        binding.List3.append(text+"\r\n")
    }

    private fun clearEditText() {
        with(binding) {
            Num3.setText("")
            Book3.setText("")
            Publish3.setText("")
        }
    }
    private fun insertDB() {
        binding.Insert3.setOnClickListener {
            try {
                databaseHelper3.insertData(
                    binding.Num3.text.toString().trim(),
                    binding.Book3.text.toString().trim(),
                    binding.Publish3.text.toString().trim()
                )
                clearEditText()
                Toast.makeText(applicationContext,"데이터 입력",Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDB() {
        binding.Delete3.setOnClickListener {
            try {
                databaseHelper3.deleteData(binding.Num3.text.toString().trim())
                clearEditText()
                Toast.makeText(applicationContext,"삭제됨",Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDB() {
        binding.Show3.setOnClickListener {
            try {
                val selectResult =databaseHelper3.read()
                Toast.makeText(applicationContext,"저장된 목록입니다",Toast.LENGTH_SHORT).show()
                showTxt(selectResult)
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchWEB() {
        binding.Search3.setOnClickListener {
            val b =binding.Book3
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, b.text.toString())
            startActivity(intent)
        }

    }
}

