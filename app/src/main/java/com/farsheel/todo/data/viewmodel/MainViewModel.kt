/*
 * *
 *  * Created by Farsheel on 1/2/19 11:57 AM
 *  *
 *  * Last modified 31/1/19 9:40 PM
 *
 */

package com.farsheel.todo.data.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farsheel.todo.data.AppDatabase
import com.farsheel.todo.data.model.Todo

class MainViewModel : ViewModel() {

    private lateinit var context: Context

    fun setContext(context: Context): MainViewModel {
        this.context = context
        return this
    }

    fun getAll(): LiveData<List<Todo>> {

        return AppDatabase.getAppDatabase(context).todoDao().getAll()

    }


}
