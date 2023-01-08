package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter
import com.example.taskmanager.utils.isNetworkConnected
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter
    private val binding get() = _binding!!
    private lateinit var data: List<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            adapter = TaskAdapter(this::onLongClick, this::onCLick)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        setData()

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setData() {
        if (requireContext().isNetworkConnected()) {
            getData()
        } else {
            data = App.db.dao().getAll()
            adapter.addTasks(data)
        }

    }

    private fun getData() {
        App.firebaseDB?.collection("tasks")?.get()?.addOnCompleteListener {
            if (it.isSuccessful) {
                val data = arrayListOf<Task>()
                for (i in it.result) {
                    val task = i.toObject(Task::class.java)
                    data.add(task)
                }
                adapter.addTasks(data)
            }
        }?.addOnFailureListener {
            Log.e("ololo", "getData: " + it.message)
        }
    }


    private fun onCLick(task: Task) {
        if (requireContext().isNetworkConnected()){}
        else{
        findNavController().navigate(R.id.taskFragment, bundleOf(KEY_FOR_TASK to task))}
    }

    private fun onLongClick(position: Int) {
       if (requireContext().isNetworkConnected()){}
        else {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Вы уверенны что хотите удалить?")
        builder.setMessage("Если вы удалите данную строку его нельзя будет восстановить!")
        builder.setPositiveButton("Да") { dialogInterface: DialogInterface, i: Int ->
            App.db.dao().delete(data[position])
            setData()
        }
        builder.setNegativeButton("Нет") { dialogInterface: DialogInterface, i: Int ->
        }
        builder.show()}
    }

    companion object {
        const val KEY_FOR_TASK = "task"
    }

}