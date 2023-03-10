package com.katyandleo.lifty.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.katyandleo.lifty.R
import com.katyandleo.lifty.createProgram.WorkoutsFragment
import com.katyandleo.lifty.data.Lift
import com.katyandleo.lifty.data.Program
import com.katyandleo.lifty.dialog.LiftDialogFragment


class NoCheckLiftAdapter(private val dataList: List<Lift>, private val activity: FragmentActivity): RecyclerView.Adapter<NoCheckLiftAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view: View = LayoutInflater.from(parent.context)
           .inflate(R.layout.no_check_lift_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NoCheckLiftAdapter.ViewHolder, position: Int) {
       val data = dataList[position]
        holder.title.text = data.name
        holder.sets.text = Html.fromHtml("<b>" +activity.getText(R.string.sets_d) + "</b> " +  data.sets.toString())
        holder.reps.text = Html.fromHtml("<b>" +activity.getText(R.string.reps) + "</b> " + data.reps.toString())
        holder.RPE.text = Html.fromHtml("<b>" +activity.getText(R.string.rpe_d) + "</b> " + data.rpe.toString())
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = ItemView.findViewById(R.id.no_check_title)
        val sets: TextView = ItemView.findViewById(R.id.sets)
        val reps: TextView = ItemView.findViewById(R.id.reps)
        val RPE: TextView = ItemView.findViewById(R.id.RPE)
    }



}

