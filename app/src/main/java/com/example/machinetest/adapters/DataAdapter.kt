package com.example.machinetest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest.R
import com.example.machinetest.model.DataModel
import com.example.machinetest.model.DataModelGet


class DataAdpter(private var dataList: List<DataModelGet>, private val context: Context) : RecyclerView.Adapter<DataAdpter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel=dataList.get(position)

      //  holder.idTextView.text="ID "+dataModel.id.toString()
        holder.titleTextView.text="Title "+dataModel.title
        holder.descriptionTextView.text="Description "+dataModel.description
      //  holder.created_atTextView.text="Created At "+dataModel.created_at
      //  holder.updated_atTextView.text="Updated At "+dataModel.updated_at
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var idTextView: TextView
        lateinit var titleTextView: TextView
        lateinit var descriptionTextView: TextView
        lateinit var created_atTextView: TextView
        lateinit var updated_atTextView: TextView
        init {
            idTextView=itemLayoutView.findViewById(R.id.id)
            titleTextView=itemLayoutView.findViewById(R.id.title)
            descriptionTextView=itemLayoutView.findViewById(R.id.descr)
            created_atTextView=itemLayoutView.findViewById(R.id.creatat)
            updated_atTextView=itemLayoutView.findViewById(R.id.updateat)

        }

    }

}