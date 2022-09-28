
package com.begin.todolist


import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    private val todos: MutableList<Todo>
):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent, false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
        
    }
    fun deleteTodo(){
        todos.removeAll { todo->
            todo.isChecked
        }
        notifyDataSetChanged()
    }
    private fun toggleStrikeTrough(tvTodoTitle: TextView, isChecked: Boolean){
        if (isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
      var curTodos = todos[position]
       holder.itemView.apply {
        tvTodoTitle.text = curTodos.title
           ckDone.isChecked = curTodos.isChecked
           toggleStrikeTrough(tvTodoTitle, curTodos.isChecked)
           ckDone.setOnCheckedChangeListener { _, isChecked ->
               toggleStrikeTrough(tvTodoTitle, isChecked)
               curTodos.isChecked = !curTodos.isChecked
           }
       }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}