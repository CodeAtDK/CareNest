package com.example.carenest.Health_Education

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carenest.Health_Education.ViewModel.DataClass_Health_Education
import com.example.carenest.R

class Adapter_Health_Education (private val dataList:ArrayList<DataClass_Health_Education>):
    RecyclerView.Adapter<Adapter_Health_Education.MyViewHolder>()
{

    private var itemlayout = R.layout.healtheducationlayout

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val item = LayoutInflater.from(parent.context).inflate(itemlayout,parent,false)
        return MyViewHolder(item,mListener)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = dataList[position]
        // holder.Image.setImageResource(currentItem.titleImg)
        holder.schemeName.text = currentItem.BlogName




        holder.schemeDisbrtion.text = currentItem.BlogDiscription
        holder.schemeDisbrtion.paddingTop



    }

    override fun getItemCount(): Int {

        return dataList.size

    }



    class MyViewHolder (itemview: View, listener: onItemClickListener):RecyclerView.ViewHolder(itemview){

        // val Image : ShapeableImageView = itemview.findViewById<ShapeableImageView>(R.id.Image_id)
        val schemeName : TextView = itemview.findViewById(R.id.textviewgovernmentscheme)
        val schemeDisbrtion:TextView = itemview.findViewById(R.id.textviewgovernmentschemeDescription)

        init {

            itemView.setOnClickListener{

                listener.onItemClick(adapterPosition)


            }

        }

    }


}