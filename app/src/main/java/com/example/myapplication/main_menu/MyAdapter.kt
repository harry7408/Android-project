package com.example.myapplication.main_menu

import android.preference.ListPreference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class MyAdapter(val dataList : ArrayList<Data>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val img =itemView.findViewById<ImageView>(R.id.imageView)
        val text1=itemView.findViewById<TextView>(R.id.textView)
        val text2=itemView.findViewById<TextView>(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycle_holder,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.img.setImageResource(dataList.get(position).img)
        holder.text1.text=dataList.get(position).text1
        holder.text2.text=dataList.get(position).text2
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}