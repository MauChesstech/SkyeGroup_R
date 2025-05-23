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

        setupRecyclerView()
        setupObservers()
        todoViewModel.onCreate()

        binding.imgRefresh.setOnClickListener {
            refreshData()
        }
    }

    private fun refreshData() {
        val intent = intent
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun setupObservers() {
        todoViewModel.todosList.observe(this) { todos ->
            todos?.let { todoAdapter.submitList(it) }
        }

        todoViewModel.isLoading.observe(this) { isLoading ->
            binding.idProgress.isVisible = isLoading
        }

        todoViewModel.dataSource.observe(this) { source ->
            updateDataSourceUI(source)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateDataSourceUI(source: TodoViewModel.DataSource) {
        val (message, colorRes) = when (source) {
            is TodoViewModel.DataSource.API -> Pair(
                "Datos obtenidos desde la API...",
                R.color.green
            )
            is TodoViewModel.DataSource.Local -> Pair(
                "Datos almacenados localmente (Modo offline)...",
                if (isInternetAvailable(this)) {
                    R.color.green
                } else {
                    Toast.makeText(this, "Sin conexión a internet", Toast.LENGTH_SHORT).show()
                    R.color.red
                }
            )
            is TodoViewModel.DataSource.Empty -> Pair(
                "Lista vacía. No hay datos disponibles",
                R.color.red
            )
        }

        binding.txtMessage.text = message
        binding.imgWifi.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(this, colorRes)
        )

        /* Actualiza el estado de los botones según el origen */
        todoAdapter.setButtonsEnabled(source is TodoViewModel.DataSource.Local)
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(
            onDeleteClicked = { todo ->
                functionAlert(todo)
            },
            onCheckChanged = { todoId, isChecked ->
                todoViewModel.updateTodoStatus(todoId, isChecked)
            }
        )

        binding.recyclerViewTodos.apply {
            layoutManager = LinearLayoutManager(this@TodoList)
            setHasFixedSize(true)
            adapter = todoAdapter
        }
    }

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
}