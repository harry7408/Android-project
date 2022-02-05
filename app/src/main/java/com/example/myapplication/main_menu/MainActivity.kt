package com.example.myapplication.main_menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R


class MainActivity : AppCompatActivity() {
    lateinit var button1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1=findViewById<Button>(R.id.main_button1)
        button1.setOnClickListener {
            val intent= Intent(this@MainActivity, ChooseLangActivity::class.java)
            startActivity(intent)
        }
    }


}