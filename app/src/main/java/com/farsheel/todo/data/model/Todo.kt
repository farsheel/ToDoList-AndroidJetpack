/*
 * *
 *  * Created by Farsheel on 1/2/19 11:56 AM
 *  *
 *  * Last modified 31/1/19 8:50 PM
 *
 */

package com.farsheel.todo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "note") var note: String?,
    @ColumnInfo(name = "is_done") var done: Boolean = false
)