package com.katyandleo.lifty.createProgram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.katyandleo.lifty.adapters.ProgramAdapter
import com.katyandleo.lifty.data.Lift
import com.katyandleo.lifty.data.Program
import com.katyandleo.lifty.data.Workout
import com.katyandleo.lifty.databinding.FragmentProgramsBinding
import com.katyandleo.lifty.dialog.NewProgramDialogFragment
import java.lang.reflect.Type

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProgramsFragment : Fragment() {

    private var _binding: FragmentProgramsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var programsList: List<Program>? = null

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
                lifts
            ),
            Workout(
                "B Day",
                lifts
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

//
//        val programJson = gson.toJson(program)
//        val programListType: Type = object : TypeToken<ArrayList<Program>?>() {}.type
//        val programList: List<Program> = Gson().fromJson(program, programListType)
//        val database = FirebaseDatabase.getInstance().getReference("programs")
//
//        database.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val programListType: Type = object : TypeToken<ArrayList<Program>?>() {}.type
//                val programList: List<Program> = Gson().fromJson(dataSnapshot.value.toString(), programListType)
//
//                binding.programRecycler.adapter = activity?.let { ProgramAdapter(programsList, it) }
//                binding.programRecycler.adapter?.notifyDataSetChanged()
//                // Do something with the list of Program objects
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle the error
//            }
//        })
//        val programId = database.push().key
        //val programJson = Gson().toJson(programsList)
//        programId?.let { database.child(it).setValue(programJson) }

        binding.programRecycler.layoutManager = LinearLayoutManager(activity)
        if(programsList != null) {
            binding.programRecycler.adapter = activity?.let { ProgramAdapter(programsList, it) }
        }
        binding.newProgramFab.setOnClickListener{newProgram()}

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    internal fun newProgram(){
        activity?.let { NewProgramDialogFragment(this).show(it.supportFragmentManager, "") }
    }

    internal fun addNewProgram(program: Program){
        programsList = if(programsList == null){
            listOf(program)
        } else {
            programsList!! + program
        }
        binding.programRecycler.adapter = activity?.let { ProgramAdapter(programsList, it) }
        binding.programRecycler.adapter?.notifyItemInserted(programsList!!.size-1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}