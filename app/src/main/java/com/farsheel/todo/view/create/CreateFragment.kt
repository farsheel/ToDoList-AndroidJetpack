/*
 * *
 *  * Created by Farsheel on 1/2/19 11:57 AM
 *  *
 *  * Last modified 31/1/19 9:40 PM
 *
 */

package com.farsheel.todo.view.create

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.farsheel.todo.R
import com.farsheel.todo.data.model.Todo
import com.farsheel.todo.data.viewmodel.CreateViewModel
import kotlinx.android.synthetic.main.create_fragment.*



class CreateFragment : Fragment() {

    private lateinit var viewModel: CreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_fragment, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(CreateViewModel::class.java).setContext(context)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.setupWithNavController(findNavController())
        toolbar.setNavigationIcon(R.drawable.ic_close)

        changeStatusBarColor(android.R.color.white)


        saveBtn.setOnClickListener { view ->
            val todo = Todo(
                id = 0, title = titleEt.text.toString(),
                note = noteEt.text.toString()
            )

            viewModel.addTodo(todo)

            hideKeyboard(view)
            view.findNavController().navigateUp()

        }
    }

    private fun changeStatusBarColor(colorResId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.window?.statusBarColor = getColor(context!!, colorResId)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

    }


    private fun hideKeyboard(view: View) {
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
