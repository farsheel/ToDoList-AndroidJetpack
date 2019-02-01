/*
 * *
 *  * Created by Farsheel on 1/2/19 11:57 AM
 *  *
 *  * Last modified 31/1/19 9:48 PM
 *
 */

package com.farsheel.todo.view.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.farsheel.todo.R
import com.farsheel.todo.data.model.Todo
import com.farsheel.todo.data.viewmodel.MainViewModel
import com.farsheel.todo.utils.ScrollAwareFABBehavior
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java).setContext(context)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addFab.setOnClickListener { v ->
            v.findNavController().navigate(R.id.toCreate)
        }

        toolbar.setupWithNavController(findNavController())


        todoRcv.layoutManager = LinearLayoutManager(context)

        val todoAdapter = TodoAdapter(context!!)
        changeStatusBarColor(android.R.color.white)


        todoRcv.adapter = todoAdapter

        viewModel.getAll().observe(this, Observer<List<Todo>> { list ->

            todoAdapter.setTodos(list)

            if (list.isEmpty()){
                addFab.show()

                emptyTv.visibility = View.VISIBLE

            }else{
                emptyTv.visibility = View.GONE
            }

        })

        ScrollAwareFABBehavior(recyclerView = todoRcv, floatingActionButton = addFab).start()

    }

    private fun changeStatusBarColor(colorResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.statusBarColor = ContextCompat.getColor(context!!, colorResId)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }


    }

}
