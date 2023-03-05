package com.katyandleo.lifty.createProgram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.katyandleo.lifty.R
import com.katyandleo.lifty.adapters.LiftAdapter
import com.katyandleo.lifty.adapters.WorkoutAdapter
import com.katyandleo.lifty.data.Workout
import com.katyandleo.lifty.databinding.FragmentWorkoutsBinding
import com.katyandleo.lifty.dialog.NewProgramDialogFragment
import com.katyandleo.lifty.dialog.NewWorkoutDialogFragment

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.s
    private val binding get() = _binding!!
    var workouts: List<Workout>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         workouts = arguments?.getParcelableArray("workoutsList")?.toList() as List<Workout>?
        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.workoutRecycler.layoutManager = LinearLayoutManager(activity)
        binding.workoutRecycler.adapter = activity?.let { workouts?.let { it1 -> WorkoutAdapter(it1, it) } }
        binding.newWoroutFab.setOnClickListener {
            newWorkout()
        }
 //       binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    internal fun newWorkout(){
        activity?.let { NewWorkoutDialogFragment(this).show(it.supportFragmentManager, "") }
    }

    internal fun addNewWorkout(workout: Workout){

        if(workouts == null){
            workouts = listOf(workout)
        }else{
            workouts  = workouts!! + workout
        }
        binding.workoutRecycler.adapter = activity?.let { workouts?.let { it1 -> WorkoutAdapter(it1, it) } }
        binding.workoutRecycler.adapter?.notifyDataSetChanged()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}