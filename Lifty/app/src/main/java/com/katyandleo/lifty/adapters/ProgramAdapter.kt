package com.katyandleo.lifty.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.katyandleo.lifty.R
import com.katyandleo.lifty.createProgram.WorkoutsFragment
import com.katyandleo.lifty.data.Program
import com.katyandleo.lifty.data.Workout


class ProgramAdapter(private val dataList: List<Program>?, private val activity: FragmentActivity): RecyclerView.Adapter<ProgramAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view: View = LayoutInflater.from(parent.context)
           .inflate(R.layout.program_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ProgramAdapter.ViewHolder, position: Int) {
      if(dataList != null) {
          val data = dataList[position]
          holder.title.text = data.name
          holder.nDays.text = activity.getString(R.string.n_days, data.days)
          holder.nWeeks.text = activity.getString(R.string.n_weeks, data.weeks)
          holder.itemView.setOnClickListener { onClick(data.workouts) }
      }
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = ItemView.findViewById(R.id.title)
        val nDays: TextView = ItemView.findViewById(R.id.n_days)
        val nWeeks: TextView = ItemView.findViewById(R.id.n_weeks)
    }

    fun onClick(workouts: List<Workout>?){
        val bundle = Bundle().apply {
            putParcelableArray("workoutsList", workouts?.toTypedArray())
        }
        findNavController(activity, R.id.nav_host_fragment_content_create_program).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }


}

