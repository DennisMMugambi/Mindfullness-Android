package com.example.mindfullnessapp

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.ekenya.rnd.android.common.Utils.Dialogs
import com.example.mindfullnessapp.R
import com.example.mindfullnessapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var parentView : ConstraintLayout

    private val dialogs = Dialogs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        parentView = binding.container
//        val toolbar: Toolbar = binding.root.findViewById(com.ekenya.rnd.android.common.R.id.toolbar)
        //       setSupportActionBar(toolbar)
        //
        //supportActionBar?.title  = getString(R.string.title_onboarding)
        //

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        dialogs.listener = {

        }
    }

//    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
//        // Fragment Injector should use the Application class
//        // If necessary, I will use AndroidInjector as well as App class (I have not done this time)
//        return (application as DemoApplication).supportFragmentInjector()
//    }
}