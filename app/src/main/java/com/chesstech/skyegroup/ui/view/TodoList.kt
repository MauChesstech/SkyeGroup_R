package com.chesstech.skyegroup.ui.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.chesstech.skyegroup.R
import com.chesstech.skyegroup.adapter.TodoAdapter
import com.chesstech.skyegroup.data.model.network.NetworkUtils.isInternetAvailable
import com.chesstech.skyegroup.databinding.ActivityTodoListBinding
import com.chesstech.skyegroup.domain.model.Todo
import com.chesstech.skyegroup.ui.viewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoList : AppCompatActivity() {

    private lateinit var binding: ActivityTodoListBinding
    private val todoViewModel: TodoViewModel by viewModels() //Ciclo de vida del viewModel
    private lateinit var todoAdapter: TodoAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUI()
        setupRecyclerView()
        setupObservers()
        todoViewModel.onCreate()

        binding.imgRefresh.setOnClickListener {
            val intent = intent
            finish()
            overridePendingTransition(0, 0) // Sin animación de cierre
            startActivity(intent)
            overridePendingTransition(0, 0) // Sin animación de inicio
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI() {
        if (isInternetAvailable(this)) {   /* Avisar que no hay conexión a internet */
            binding.txtMessage.text = "Datos obtenidos de la API pública..."
            binding.imgWifi.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green))
        } else {
            Toast.makeText(this, "No hay conexión a internet", Toast.LENGTH_SHORT).show()
            binding.txtMessage.text = "Datos almacenados localmente y editables (Offline)..."
            binding.imgWifi.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))
        }
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(
            onDeleteClicked = { todo ->
                functionAlert(todo)
            },
            onCheckClicked = { updatedTodo ->
                todoViewModel.updateTodo(updatedTodo)
            },
            btnHabilitados = !isInternetAvailable(this) /* Control de botones de acuerdo al estado del Internet */
        )

        binding.recyclerViewTodos.apply {
            layoutManager = LinearLayoutManager(this@TodoList)
            setHasFixedSize(true)
            adapter = todoAdapter
        }
    }
    /*
        private fun setupRecyclerView() {
            todoAdapter = TodoAdapter(
                onDeleteClicked = { todo ->
                    functionAlert(todo)
                },
                onCheckClicked = { updatedTodo, isChecked ->
                    // Solo actualiza el estado en el ViewModel
                    todoViewModel.updateTodoStatus(updatedTodo, isChecked)
                },
                btnHabilitados = !isInternetAvailable(this) /* Control de botones de acuerdo al estado del Internet */
            )

            binding.recyclerViewTodos.apply {
                layoutManager = LinearLayoutManager(this@TodoList)
                adapter = todoAdapter
            }
        }
     */
    private fun functionAlert(todo: Todo) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage("¿Seguro que deseas eliminar este registro?")
            .setPositiveButton("Sí") { _, _ ->
                todoViewModel.deleteTodo(todo)
                Toast.makeText(this, "Eliminado!", Toast.LENGTH_SHORT).show()
                todoViewModel.onCreate()
            }
            .setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

    private fun setupObservers() {
        todoViewModel.todosList.observe(this) { todos ->
            todos?.let { todoAdapter.submitList(it) }
        }

        todoViewModel.isLoading.observe(this) { isLoading ->
            binding.idProgress.isVisible = isLoading
        }
    }
}