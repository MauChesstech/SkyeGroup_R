package com.chesstech.skyegroup.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.chesstech.skyegroup.databinding.ActivityTodoListBinding
import com.chesstech.skyegroup.ui.viewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoList : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding
    private val todoViewModel: TodoViewModel by viewModels() //Ciclo de vida del viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoViewModel.onCreate()

        todoViewModel.todoModel.observe(this, Observer {
            binding.txtIDUser.text = it.userId.toString()
            binding.txtNum.text = it.id.toString()
            binding.txtTitle.text = it.title
            binding.txtCheckBox.isChecked = it.completed
        })

        todoViewModel.isLoading.observe(this, Observer {
            binding.idProgress.isVisible = it
        })

        //Al tocar la pantalla se actualizará el texto
        binding.viewContainer.setOnClickListener {
            todoViewModel.randomTodo()
        }

    }
}