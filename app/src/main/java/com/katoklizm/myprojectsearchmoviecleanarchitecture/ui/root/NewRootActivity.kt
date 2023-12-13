package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
//import androidx.fragment.app.commit
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.navigation.NavigatorHolder
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.navigation.NavigatorImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.databinding.ActivityNewRootBinding

//import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.movies.MoviesFragment
//import org.koin.android.ext.android.inject

class NewRootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewRootBinding

    // Заинжектили NavigatorHolder,
    // чтобы прикрепить к нему Navigator
//    private val navigatorHolder: NavigatorHolder by inject()
//
//    // Создали Navigator
//    private val navigator = NavigatorImpl(
//        fragmentContainerViewId = R.id.rootFragmentContainerView,
//        fragmentManager = supportFragmentManager
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailsFragment, R.id.moviesCastFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }

                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }

    fun animateBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }
}