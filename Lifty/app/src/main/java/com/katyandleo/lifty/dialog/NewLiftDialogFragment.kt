package com.katyandleo.lifty.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Half.toFloat
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.katyandleo.lifty.R
import com.katyandleo.lifty.adapters.NoCheckLiftAdapter
import com.katyandleo.lifty.data.Lift
import com.katyandleo.lifty.data.Program


class NewLiftDialogFragment(private val newWorkoutDialogFragment: NewWorkoutDialogFragment) : DialogFragment() {

    var dialogView: View? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialogView = layoutInflater.inflate(R.layout.new_lift_dialog, null)
        setupView(dialogView!!, newWorkoutDialogFragment, dialog)
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


    internal fun setupView(dialogView: View, newWorkoutFragment: NewWorkoutDialogFragment, dialog: Dialog) {
        dialogView.findViewById<Button>(R.id.close_button).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.complete_button).setOnClickListener {
          newWorkoutFragment.newLift(createNewLift(dialogView))
            dialog.dismiss()
        }

    }

    internal fun createNewLift(dialogView: View): Lift {
        val name = dialogView.findViewById<TextInputLayout>(R.id.title_edit_text).editText?.editableText.toString() ?: ""
        var set = Integer.parseInt(dialogView.findViewById<TextInputLayout>(R.id.sets_edit_text).editText?.editableText.toString())
        var rep = Integer.parseInt(dialogView.findViewById<TextInputLayout>(R.id.reps_edit_text).editText?.editableText.toString())
        var weight = dialogView.findViewById<TextInputLayout>(R.id.weight_edit_text).editText?.editableText.toString().toFloat()
        var rpe = dialogView.findViewById<TextInputLayout>(R.id.rpe_edit_text).editText?.editableText.toString().toInt()
        var notes = dialogView.findViewById<TextInputLayout>(R.id.notes_edit_text).editText?.editableText.toString()
        return Lift(
            name,
            rep,
            set,
            rpe,
            weight,
            null,
            null,
            notes,
            false
        )
    }
}
