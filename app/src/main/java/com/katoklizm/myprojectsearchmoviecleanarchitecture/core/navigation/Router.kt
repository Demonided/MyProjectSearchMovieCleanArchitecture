package com.katoklizm.myprojectsearchmoviecleanarchitecture.core.navigation

import androidx.fragment.app.Fragment

interface Router {
    val navigatorHolder : NavigatorHolder

    fun openFragment(fragment: Fragment)
}