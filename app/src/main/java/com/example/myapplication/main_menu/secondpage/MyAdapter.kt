package com.example.myapplication.main_menu.secondpage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class MyAdapter(val dataList : ArrayList<Data>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imageView)
        val text1 = itemView.findViewById<TextView>(R.id.textView)
        val text2 = itemView.findViewById<TextView>(R.id.textView2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_holder, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.img.setImageResource(dataList.get(position).img)
        holder.text1.text = dataList.get(position).text1
        holder.text2.text = dataList.get(position).text2

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }
    interface OnItemClickListener {
        fun onClick(v:View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener: OnItemClickListener
}