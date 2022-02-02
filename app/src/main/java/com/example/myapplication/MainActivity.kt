package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    lateinit var button1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1=findViewById<Button>(R.id.main_button1)
        button1.setOnClickListener {
            val intent= Intent(this@MainActivity,ChooseLang::class.java)
            startActivity(intent)
        }
    }


}