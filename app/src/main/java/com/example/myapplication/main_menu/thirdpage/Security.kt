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
import com.example.myapplication.databinding.ActivitySecurityBinding
import java.lang.Exception

class Security : AppCompatActivity() {
    private val binding: ActivitySecurityBinding by lazy {
        ActivitySecurityBinding.inflate(layoutInflater)
    }

    private val databaseHelper7: DatabaseHelper7 by lazy {
        DatabaseHelper7.getInstance(applicationContext)
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
        databaseHelper7.close()
        super.onDestroy()
    }
    private fun showTxt(text :String) {
        binding.List7.append(text+"\r\n")
    }

    private fun clearEditText() {
        with(binding) {
            Num7.setText("")
            Book7.setText("")
            Publish7.setText("")
        }
    }
    private fun insertDB() {
        binding.Insert7.setOnClickListener {
            try {
                databaseHelper7.insertData(
                    binding.Num7.text.toString().trim(),
                    binding.Book7.text.toString().trim(),
                    binding.Publish7.text.toString().trim()
                )
                clearEditText()
                Toast.makeText(applicationContext,"데이터 입력",Toast.LENGTH_SHORT).show()
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteDB() {
        binding.Delete7.setOnClickListener {
            try {
                databaseHelper7.deleteData(binding.Num7.text.toString().trim())
                clearEditText()
                Toast.makeText(applicationContext,"삭제됨",Toast.LENGTH_SHORT).show()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showDB() {
        binding.Show7.setOnClickListener {
            try {
                val selectResult =databaseHelper7.read()
                Toast.makeText(applicationContext,"저장된 목록입니다",Toast.LENGTH_SHORT).show()
                showTxt(selectResult)
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun searchWEB() {
        binding.Search7.setOnClickListener {
            val b =binding.Book7
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, b.text.toString())
            startActivity(intent)
        }

    }
}

