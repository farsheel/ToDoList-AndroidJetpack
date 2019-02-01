/*
 * *
 *  * Created by Farsheel on 1/2/19 11:56 AM
 *  *
 *  * Last modified 30/1/19 11:40 PM
 *
 */

package com.farsheel.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}
