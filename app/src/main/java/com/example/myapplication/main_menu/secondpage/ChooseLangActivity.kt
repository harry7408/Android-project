package com.example.myapplication.main_menu.secondpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityChooseLangBinding
import com.example.myapplication.main_menu.firstpage.MainActivity
import com.example.myapplication.main_menu.thirdpage.*

class ChooseLangActivity : AppCompatActivity() {

   private lateinit var binding : ActivityChooseLangBinding
    private val datalist = arrayListOf<Data>(
        Data(R.drawable.ic_launcher_background, "프론트엔드","HMTL, CSS, Javascript"),
        Data(R.drawable.ic_launcher_background, "백엔드/서버", " C++, Java, Python"),
        Data(R.drawable.ic_launcher_background, "모바일","Kotlin, Java"),
        Data(R.drawable.ic_launcher_background,"게임","C++, C#"),
        Data(R.drawable.ic_launcher_background,"임베디드","C"),
        Data(R.drawable.ic_launcher_background,"알고리즘","C++, Java, Python"),
        Data(R.drawable.ic_launcher_background,"오픈소스","Git"),
        Data(R.drawable.ic_launcher_background,"보안","C, SQL")
    )
    val listAdapter = MyAdapter(datalist)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_lang)

      binding= ActivityChooseLangBinding.inflate(layoutInflater)
        binding.recycleView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recycleView.adapter= listAdapter
    setContentView(binding.root)

    listAdapter.setItemClickListener(object: MyAdapter.OnItemClickListener{
        override fun onClick(v: View, position: Int) {
          lateinit var sintent :Intent
            var num :Int = position
            when (num) {
                0-> sintent=Intent(this@ChooseLangActivity, frontend::class.java)
                1-> sintent=Intent(this@ChooseLangActivity,backend::class.java)
                2-> sintent=Intent(this@ChooseLangActivity,mobile::class.java)
                3-> sintent=Intent(this@ChooseLangActivity,game::class.java)
                4-> sintent=Intent(this@ChooseLangActivity,embedded::class.java)
                5-> sintent=Intent(this@ChooseLangActivity,algorithm::class.java)
                6-> sintent=Intent(this@ChooseLangActivity,opensource::class.java)
                7-> sintent=Intent(this@ChooseLangActivity,security::class.java)
            }
            startActivity(sintent)
        }
    })

    }


}
