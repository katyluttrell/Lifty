package com.katyandleo.lifty.createProgram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.katyandleo.lifty.R
import com.katyandleo.lifty.databinding.FragmentWorkoutsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.s
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}