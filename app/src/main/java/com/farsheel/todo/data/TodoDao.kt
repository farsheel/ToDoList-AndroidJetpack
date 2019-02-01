/*
 * *
 *  * Created by Farsheel on 1/2/19 11:57 AM
 *  *
 *  * Last modified 31/1/19 8:50 PM
 *
 */

package com.farsheel.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.farsheel.todo.data.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo ORDER BY id DESC")
    fun getAll(): LiveData<List<Todo>>

    @Query("SELECT * FROM Todo WHERE id = :id")
    fun getById(id: Int): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg todo: Todo)

    @Delete
    fun delete(todo: Todo)
}