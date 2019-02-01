/*
 * *
 *  * Created by Farsheel on 1/2/19 11:57 AM
 *  *
 *  * Last modified 31/1/19 9:40 PM
 *
 */

package com.farsheel.todo.data.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.farsheel.todo.data.AppDatabase
import com.farsheel.todo.data.model.Todo

class CreateViewModel : ViewModel() {

    private lateinit var context: Context

    fun setContext(context: Context): CreateViewModel {
        this.context = context
        return this
    }

    fun addTodo(todo: Todo) {
        AppDatabase.getAppDatabase(context).todoDao().insert(todo)
    }

    fun deleteTodo(todo: Todo) {
        AppDatabase.getAppDatabase(context).todoDao().delete(todo)
    }
}
