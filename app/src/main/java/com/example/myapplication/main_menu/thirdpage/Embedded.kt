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
import com.example.myapplication.databinding.ActivityEmbeddedBinding
import com.example.myapplication.databinding.ActivityFrontendBinding
import java.lang.Exception

class Embedded : AppCompatActivity() {
    private val binding: ActivityEmbeddedBinding by lazy {
        ActivityEmbeddedBinding.inflate(layoutInflater)
    }

    private val databaseHelper4: DatabaseHelper4 by lazy {
        DatabaseHelper4.getInstance(applicationContext)
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
        databaseHelper4.close()
        super.onDestroy()
    }
    private fun showTxt(text :String) {
        binding.List4.append(text+"\r\n")
    }

    private fun clearEditText() {
        with(binding) {
            Num4.setText("")
            Book4.setText("")
            Publish4.setText("")
        }
    }
    private fun insertDB() {
        binding.Insert4.setOnClickListener {
            try {
                databaseHelper4.insertData(
                    binding.Num4.text.toString().trim(),
                    binding.Book4.text.toString().trim(),
                    binding.Publish4.text.toString().trim()
                )
                clearEditText()
                Toast.makeText(applicationContext,"데이터 입력",Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDB() {
        binding.Delete4.setOnClickListener {
            try {
                databaseHelper4.deleteData(binding.Num4.text.toString().trim())
                clearEditText()
                Toast.makeText(applicationContext,"삭제됨",Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDB() {
        binding.Show4.setOnClickListener {
            try {
                val selectResult =databaseHelper4.read()
                Toast.makeText(applicationContext,"저장된 목록입니다",Toast.LENGTH_SHORT).show()
                showTxt(selectResult)
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchWEB() {
        binding.Search4.setOnClickListener {
            val b =binding.Book4
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, b.text.toString())
            startActivity(intent)
        }

    }
}

