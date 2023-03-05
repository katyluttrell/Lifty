package com.katyandleo.lifty.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.katyandleo.lifty.R
import com.katyandleo.lifty.adapters.NoCheckLiftAdapter
import com.katyandleo.lifty.createProgram.ProgramsFragment
import com.katyandleo.lifty.createProgram.WorkoutsFragment
import com.katyandleo.lifty.data.Lift
import com.katyandleo.lifty.data.Program


class NewWorkoutDialogFragment(private val workoutsFragment: WorkoutsFragment) : DialogFragment() {

    internal var liftList: List<Lift>? = null
    var dialogView: View? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialogView = layoutInflater.inflate(R.layout.new_workout_dialog, null)
        setupView(dialogView!!, workoutsFragment, dialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        dialog.window?.attributes = layoutParams
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView!!)
        val window = dialog.window
        val params = window?.attributes
        params?.width = (context?.resources?.displayMetrics?.widthPixels?.times(0.9))!!.toInt()
        window?.attributes = params
        dialog.create()
        dialog.show()
        return dialog
    }


    internal fun setupView(dialogView: View, workoutsFragment: WorkoutsFragment, dialog: Dialog) {
        setUpRecycler(dialogView)
        dialogView.findViewById<Button>(R.id.close_button).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.complete_button).setOnClickListener {
          //  workoutsFragment.addNewProgram(createNewProgram(dialogView))
            dialog.dismiss()
        }

        dialogView.findViewById<FloatingActionButton>(R.id.newLiftFab).setOnClickListener {
            activity?.let { NewLiftDialogFragment(this).show(it.supportFragmentManager, "") }
        }

    }

    private fun setUpRecycler(dialogView: View) {
       val rec = dialogView.findViewById<RecyclerView>(R.id.new_lift_recycler)
        rec.layoutManager = LinearLayoutManager(activity)
        if(liftList!=null) {
            rec.adapter = workoutsFragment?.activity?.let { NoCheckLiftAdapter(liftList!!, it) }
        }

    }

    internal fun newLift(lift: Lift){
        if(liftList== null){
            liftList = listOf(lift)
        }else{
            liftList = liftList!! + listOf(lift)
        }
        dialogView!!.findViewById<RecyclerView>(R.id.new_lift_recycler).adapter =
            activity?.let { NoCheckLiftAdapter(liftList!!, it) }
        dialogView!!.findViewById<RecyclerView>(R.id.new_lift_recycler).adapter!!.notifyDataSetChanged()
    }

    internal fun createNewWorkout(dialogView: View): Program {
        var title = dialogView.findViewById<TextInputLayout>(R.id.title_edit_text).editText?.editableText.toString()
        var weeks = Integer.parseInt(dialogView.findViewById<TextInputLayout>(R.id.n_weeks_edit_text).editText?.editableText.toString())
        if(title.isNullOrEmpty()){
            title = ""
        }
        return Program(
            title,
            weeks,
            0,
            null
        )
    }
}
