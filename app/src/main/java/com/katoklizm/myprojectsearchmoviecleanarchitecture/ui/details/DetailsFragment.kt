package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
import com.katoklizm.myprojectsearchmoviecleanarchitecture.databinding.FragmentDetailsBinding
import com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.poster.DetailsViewPagerAdapter

class DetailsFragment: Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var tabsMediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Исправить компиляцию!
        val poster = requireArguments().getString(ARGS_POSTER_URL) ?: ""
        val movieId = requireArguments().getString(ARGS_MOVIE_ID) ?: ""

        binding.viewPager.adapter = DetailsViewPagerAdapter(
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle,
            posterUrl = poster,
            movieId = movieId,
        )

        tabsMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }
        tabsMediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabsMediator.detach()
    }

    companion object {
        private const val ARGS_MOVIE_ID = "movie_id"
        private const val ARGS_POSTER_URL = "poser_url"

        const val TAG = "DetailsFragment"

//        fun newInstance(movieId: String, posterUrl: String): Fragment {
//            return DetailsFragment().apply {
//                // Пробрасываем аргументы в Bundle
//                arguments = bundleOf(
//                    ARGS_MOVIE_ID to movieId,
//                    ARGS_POSTER_URL to posterUrl
//                )
//            }
//        }

        fun createArgs(movieId: String, posterUrl: String): Bundle =
            bundleOf(ARGS_MOVIE_ID to movieId,
                ARGS_POSTER_URL to posterUrl)
    }
}
