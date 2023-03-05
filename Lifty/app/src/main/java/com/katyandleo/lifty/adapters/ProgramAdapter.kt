package com.katyandleo.lifty.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.katyandleo.lifty.R
import com.katyandleo.lifty.data.Program

class ProgramAdapter(private val dataList: List<Program>, private val context: Context): RecyclerView.Adapter<ProgramAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view: View = LayoutInflater.from(parent.context)
           .inflate(R.layout.program_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ProgramAdapter.ViewHolder, position: Int) {
       val data = dataList[position]
        holder.title.text = data.name
        holder.nDays.text = context.getString(R.string.n_days, data.days)
        holder.nWeeks.text = context.getString(R.string.n_weeks, data.weeks)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = ItemView.findViewById(R.id.title)
        val nDays: TextView = ItemView.findViewById(R.id.n_days)
        val nWeeks: TextView = ItemView.findViewById(R.id.n_weeks)
    }


}

