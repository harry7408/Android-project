package com.example.myapplication.main_menu.secondpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityChooseLangBinding
import com.example.myapplication.main_menu.thirdpage.*

class ChooseLangActivity : AppCompatActivity() {

   private lateinit var binding : ActivityChooseLangBinding
    private val datalist = arrayListOf<Data>(
        Data(R.drawable.front_end, "프론트엔드","HMTL, CSS, Javascript"),
        Data(R.drawable.back_end, "백엔드/서버", " C++, Java, Python"),
        Data(R.drawable.mobile, "모바일","Kotlin, Java"),
        Data(R.drawable.game,"게임","C++, C#"),
        Data(R.drawable.embedded,"임베디드","C"),
        Data(R.drawable.algorithm,"알고리즘","C++, Java, Python"),
        Data(R.drawable.opensource,"오픈소스","Git"),
        Data(R.drawable.security,"보안","C, SQL")
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
                0-> sintent=Intent(this@ChooseLangActivity, Frontend::class.java)
                1-> sintent=Intent(this@ChooseLangActivity,Backend::class.java)
                2-> sintent=Intent(this@ChooseLangActivity,Mobile::class.java)
                3-> sintent=Intent(this@ChooseLangActivity,Game::class.java)
                4-> sintent=Intent(this@ChooseLangActivity,Embedded::class.java)
                5-> sintent=Intent(this@ChooseLangActivity,Algorithm::class.java)
                6-> sintent=Intent(this@ChooseLangActivity,Opensource::class.java)
                7-> sintent=Intent(this@ChooseLangActivity,Security::class.java)
            }
            startActivity(sintent)
        }
    })

    }


}
