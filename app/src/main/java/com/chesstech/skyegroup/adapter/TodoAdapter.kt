package com.chesstech.skyegroup.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chesstech.skyegroup.R
import com.chesstech.skyegroup.databinding.ItemTodoBinding
import com.chesstech.skyegroup.domain.model.Todo

class TodoAdapter(
    private val onDeleteClicked: (Todo) -> Unit,
    private val onCheckClicked: (Todo) -> Unit
    //private val btnHabilitados: Boolean
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todos: List<Todo> = emptyList()
    private var buttonsEnabled: Boolean = false

    @SuppressLint("NotifyDataSetChanged")
    fun setButtonsEnabled(enabled: Boolean) {
        buttonsEnabled = enabled
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    // Crea tu propio método submitList
    fun submitList(newList: List<Todo>) {
        todos = newList
        notifyDataSetChanged() // O usa notifyItemRangeChanged para mejor performance
    }
/*
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newTodos: List<Todo>) {
        todos = newTodos
        notifyDataSetChanged()
    }*/

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(todo: Todo) {

            binding.apply {
                txtIDUser.text = todo.userId.toString()
                txtNum.text = todo.id.toString()
                txtTitle.text = todo.title

                /* Configuración del CheckBox */
                txtCheckBox.setOnCheckedChangeListener(null) // Elimina listener temporalmente
                txtCheckBox.isChecked = todo.completed

                txtCheckBox.text = if (todo.completed) {
                    binding.root.context.getString(R.string.completed)
                } else {
                    binding.root.context.getString(R.string.incomplete)
                }

                btnDelete.setOnClickListener { onDeleteClicked(todo) }

                txtCheckBox.setOnCheckedChangeListener { _, isChecked ->
                    // Actualiza el texto inmediatamente
                    txtCheckBox.text = if (isChecked) "Completo" else "Incompleto"

                    // Actualiza el modelo y notifica al ViewModel
                    val updatedTodo = todo.copy(completed = isChecked)
                    onCheckClicked(updatedTodo)
                }

                // Configuración de vistas
                btnDelete.isEnabled = buttonsEnabled
                txtCheckBox.isEnabled = buttonsEnabled

                // Cambia la opacidad para mejor feedback visual
                val alpha = if (buttonsEnabled) 1f else 0.5f
                btnDelete.alpha = alpha
                txtCheckBox.alpha = alpha
            }

            /* binding.apply {
                txtIDUser.text = todo.userId.toString()
                txtNum.text = todo.id.toString()
                txtTitle.text = todo.title

                /* Configuración del CheckBox */
                txtCheckBox.setOnCheckedChangeListener(null) // Elimina listener temporalmente
                txtCheckBox.isChecked = todo.completed

                txtCheckBox.text = if (todo.completed) {
                    binding.root.context.getString(R.string.completed)
                } else {
                    binding.root.context.getString(R.string.incomplete)
                }

                btnDelete.isEnabled = btnHabilitados
                txtCheckBox.isEnabled = btnHabilitados

                btnDelete.setOnClickListener { onDeleteClicked(todo) }

                txtCheckBox.setOnCheckedChangeListener { _, isChecked ->
                    // Actualiza el texto inmediatamente
                    txtCheckBox.text = if (isChecked) "Completo" else "Incompleto"

                    // Actualiza el modelo y notifica al ViewModel
                    val updatedTodo = todo.copy(completed = isChecked)
                    onCheckClicked(updatedTodo)
                }
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        //holder.bind(todo, btnHabilitados)
        holder.bind(todo)
    }

    override fun getItemCount(): Int = todos.size
}