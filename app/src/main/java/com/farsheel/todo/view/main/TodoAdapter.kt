
/*
 * *
 *  * Created by Farsheel on 1/2/19 11:23 AM
 *  *
 *  * Last modified 1/2/19 11:23 AM
 *
 */

package com.farsheel.todo.view.main

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farsheel.todo.R
import com.farsheel.todo.data.model.Todo
import com.farsheel.todo.data.viewmodel.CreateViewModel
import kotlinx.android.synthetic.main.layout_todo_item.view.*





class TodoAdapter(val context: Context) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var todoList: List<Todo> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val itemView = mInflater.inflate(R.layout.layout_todo_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {

        holder.setData(position)

    }

    internal fun setTodos(list: List<Todo>) {
        this.todoList = list
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

            val viewModel = CreateViewModel().setContext(context)

            itemView.readCb.setOnClickListener {

                val todo = todoList[adapterPosition]
                todo.done = itemView.readCb.isChecked

                viewModel.addTodo(todo)

            }

            itemView.deleteIv.setOnClickListener {

                val todo = todoList[adapterPosition]
                viewModel.deleteTodo(todo)

            }

        }


        fun setData(position: Int) {


            val todo = todoList[position]

            itemView.titleTv.text = todo.title
            itemView.noteTv.text = todo.note

            itemView.readCb.isChecked = todo.done

            if (todo.done){

                itemView.titleTv.paintFlags = itemView.titleTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                itemView.noteTv.paintFlags = itemView.titleTv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }else{
                itemView.titleTv.paintFlags = itemView.titleTv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                itemView.noteTv.paintFlags = itemView.noteTv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

        }


    }
}