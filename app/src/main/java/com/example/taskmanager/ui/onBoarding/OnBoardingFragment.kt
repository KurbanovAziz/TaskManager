package com.example.taskmanager.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.Pref
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.model.OnBoard
import com.example.taskmanager.ui.onBoarding.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var adapter: OnBoardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OnBoardingAdapter(requireContext(), this::onClick)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

    }

    private fun onClick() {
        val pref = Pref(requireContext())
        pref.setOnBoardingSeen(true)
        findNavController().navigateUp()
    }

}