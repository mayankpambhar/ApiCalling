package com.example.apicalling

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class newsAdapter(val activity: Activity, val l1 : List<ArticlesItem>?): RecyclerView.Adapter<newsAdapter.viewData>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewData {
        var view = LayoutInflater.from(activity).inflate(R.layout.check,parent,false)
        return viewData(view)
    }

    override fun onBindViewHolder(holder: viewData, position: Int) {


        holder.id_txt.text = l1?.get(position)?.author.toString()
        holder.titel_txt.text = l1?.get(position)?.title
        holder.description_txt.text = l1?.get(position)?.description
        holder.rate_txt.text = l1?.get(position)?.content.toString()

            Glide.with(activity)
                .load(l1?.get(position)!!.urlToImage)
                .into(holder.image_view)

    }

    override fun getItemCount(): Int {
        return l1!!.size
    }

    class viewData(itemView: View) :RecyclerView.ViewHolder(itemView){
       var id_txt  = itemView.findViewById<TextView>(R.id.id_txt)
       var titel_txt  = itemView.findViewById<TextView>(R.id.titel_txt)
       var rate_txt  = itemView.findViewById<TextView>(R.id.rate_txt)
       var description_txt  = itemView.findViewById<TextView>(R.id.description_txt)
       var image_view  = itemView.findViewById<ImageView>(R.id.image_view)


    }

}