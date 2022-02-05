package com.example.myapplication.main_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityChooseLangBinding

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_lang)

      binding= ActivityChooseLangBinding.inflate(layoutInflater)
        binding.recycleView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recycleView.adapter=MyAdapter(datalist)
    setContentView(binding.root)



    }


}
