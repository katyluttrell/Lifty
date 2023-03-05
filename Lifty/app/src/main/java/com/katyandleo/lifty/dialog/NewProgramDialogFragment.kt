package com.katyandleo.lifty.dialog

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
import com.google.android.material.textfield.TextInputLayout
import com.katyandleo.lifty.R
import com.katyandleo.lifty.createProgram.ProgramsFragment
import com.katyandleo.lifty.data.Lift
import com.katyandleo.lifty.data.Program


class NewProgramDialogFragment(private val programsFragment: ProgramsFragment) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.new_program_dialog, null)
        setupView(dialogView, programsFragment, dialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        dialog.window?.attributes = layoutParams
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)
        val window = dialog.window
        val params = window?.attributes
        params?.width = (context?.resources?.displayMetrics?.widthPixels?.times(0.9))!!.toInt()
        window?.attributes = params
        dialog.create()
        dialog.show()
        return dialog
    }


    internal fun setupView(dialogView: View, programsFragment: ProgramsFragment, dialog: Dialog) {
        dialogView.findViewById<Button>(R.id.close_button).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.complete_button).setOnClickListener {
            programsFragment.addNewProgram(createNewProgram(dialogView))
            dialog.dismiss()
        }

    }

    internal fun createNewProgram(dialogView: View): Program {
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
