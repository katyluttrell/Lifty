package com.katyandleo.lifty.createProgram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import com.katyandleo.lifty.R
import com.katyandleo.lifty.adapters.ProgramAdapter
import com.katyandleo.lifty.data.Lift
import com.katyandleo.lifty.data.Program
import com.katyandleo.lifty.data.Workout
import com.katyandleo.lifty.databinding.FragmentProgramsBinding
import com.katyandleo.lifty.dialog.LiftDialogFragment
import com.katyandleo.lifty.dialog.NewProgramDialogFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProgramsFragment : Fragment() {

    private var _binding: FragmentProgramsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var programsList: List<Program>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProgramsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.programRecycler.layoutManager = LinearLayoutManager(activity)
        val lifts =  listOf(
            Lift(
                "Squat",
                5,
                4,
                6,
                135F,
                null,
                null,
                "notes"
            ),
            Lift(
                "Bench",
                5,
                4,
                6,
                135F,
                null,
                null,
                "notes"
            ),
            Lift(
                "Deadlift",
                5,
                4,
                6,
                135F,
                null,
                null,
                "notes"
            )
        )
        val workouts = listOf(
            Workout(
                "A Day",
                lifts,
                "notes2"
            ),
            Workout(
                "B Day",
                lifts,
                "notes5"
            )
        )
        programsList = listOf<Program>(Program(
            "Test Program",
            10,
            2,
            workouts),
            Program(
                "Test Program 2",
                10,
                2,
                workouts))

        val database = FirebaseDatabase.getInstance().getReference("programs")

        val programId = database.push().key
        val programJson = Gson().toJson(programsList)
        programId?.let { database.child(it).setValue(programJson) }
        binding.programRecycler.layoutManager = LinearLayoutManager(activity)
        binding.programRecycler.adapter = activity?.let { ProgramAdapter(programsList, it) }
        binding.newProgramFab.setOnClickListener{newProgram()}

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    internal fun newProgram(){
        activity?.let { NewProgramDialogFragment(this).show(it.supportFragmentManager, "") }
    }

    internal fun addNewProgram(program: Program){
        programsList = programsList + program
        binding.programRecycler.adapter = activity?.let { ProgramAdapter(programsList, it) }
        binding.programRecycler.adapter?.notifyItemInserted(programsList.size-1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}