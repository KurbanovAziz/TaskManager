package com.example.taskmanager.ui.task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.HomeFragment
import com.example.taskmanager.utils.showToast
import com.google.firebase.firestore.FirebaseFirestore


class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        task = arguments?.getSerializable(HomeFragment.KEY_FOR_TASK) as Task?

        if (task == null) {
            binding.btnSave.text = getString(R.string.save)
        } else {
            binding.edTitle.setText(task?.title.toString())
            binding.edDesc.setText(task?.desc.toString())
            binding.btnSave.text = getString(R.string.update)

        }
        binding.btnSave.setOnClickListener {
            if (task == null) {
                save()
                saveDataToFb()
            } else {
                update()
            }

            findNavController().navigateUp()
        }
    }

    private fun save() {
        val data = Task(
            title = binding.edTitle.text.toString(),
            desc = binding.edDesc.text.toString()
        )
        App.db.dao().insert(data)
    }

    private fun update() {
        task?.title = binding.edTitle.text.toString()
        task?.desc = binding.edDesc.text.toString()
        task?.let { App.db.dao().update(it) }
    }

    private fun saveDataToFb() {
        val data =
            Task(
                title = binding.edTitle.text.toString(),
                desc = binding.edDesc.text.toString()
            )
        App.firebaseDB?.collection("tasks")
            ?.add(data)
            ?.addOnSuccessListener {
                Log.e("ololo", " sdgkdgkdf")
            }?.addOnFailureListener {
                showToast(it.message.toString())
            }


    }


}







