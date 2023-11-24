package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.navigation.NavigatorHolder
import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.navigation.NavigatorImpl
import com.katoklizm.myprojectsearchmoviecleanarchitecture.databinding.ActivityNewRootBinding
import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.movies.MoviesFragment
import org.koin.android.ext.android.inject

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

//        if (savedInstanceState == null) {
//            // Добавляем фрагмент в контейнер
//            supportFragmentManager.commit {
//                this.add(R.id.rootFragmentContainerView, MoviesFragment())
//            }
//        }
    }

    // Прикрепляем Navigator к NavigatorHolder
//    override fun onResume() {
//        super.onResume()
//        navigatorHolder.attachNavigator(navigator)
//    }
//
//    // Открепляем Navigator от NavigatorHolder
//    override fun onPause() {
//        super.onPause()
//        navigatorHolder.detachNavigator()
//    }
}