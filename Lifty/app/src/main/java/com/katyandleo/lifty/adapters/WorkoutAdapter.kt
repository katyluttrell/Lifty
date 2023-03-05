package com.katyandleo.lifty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.katyandleo.lifty.R
import com.katyandleo.lifty.createProgram.WorkoutsFragment
import com.katyandleo.lifty.data.Program
import com.katyandleo.lifty.data.Workout


class WorkoutAdapter(private val dataList: List<Workout>, private val activity: FragmentActivity): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view: View = LayoutInflater.from(parent.context)
           .inflate(R.layout.workout_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: WorkoutAdapter.ViewHolder, position: Int) {
       val data = dataList[position]
        holder.title.text = data.name
        holder.recycler.layoutManager = LinearLayoutManager(activity)
        holder.recycler.adapter = data.lifts?.let { LiftAdapter(it, activity) }
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = ItemView.findViewById(R.id.title)
        val recycler: RecyclerView = ItemView.findViewById(R.id.lift_recycler)
    }


}

