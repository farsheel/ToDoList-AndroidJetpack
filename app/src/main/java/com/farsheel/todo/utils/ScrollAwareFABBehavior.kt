/*
 * *
 *  * Created by Farsheel on 1/2/19 11:56 AM
 *  *
 *  * Last modified 31/1/19 9:40 PM
 *
 */

package com.farsheel.todo.utils

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScrollAwareFABBehavior (private val recyclerView: RecyclerView, val floatingActionButton: FloatingActionButton) {
    fun start() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    if (floatingActionButton.isShown) {
                        floatingActionButton.hide()
                    }
                } else if (dy < 0) {
                    if (!floatingActionButton.isShown) {
                        floatingActionButton.show()
                    }
                }
            }
        })
    }
}