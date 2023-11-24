//package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.poster
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.google.android.material.tabs.TabLayoutMediator
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.databinding.ActivityDetailsBinding
//
////import com.katoklizm.myprojectsearchmoviecleanarchitecture.util.Creator
//
//class DetailsActivity : AppCompatActivity() {
////    private val viewModel by viewModel<PosterViewModel>()
//
//    lateinit var binding: ActivityDetailsBinding
//
//    private lateinit var tabMediator: TabLayoutMediator
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Мы не можем создать PosterPresenter раньше,
//        // потому что нам нужен imageUrl, который
//        // станет доступен только после super.onCreate
////        val imageUrl = intent.extras?.getString("poster", "") ?: ""
////        posterPresenter = Creator.providePosterPresenter(this, imageUrl)
//
////        setContentView(R.layout.activity_details)
////        poster = findViewById(R.id.poster)
//
//        val poster = intent.getStringExtra("poster") ?: ""
//        val movieId = intent.getStringExtra("id") ?: ""
//
//        binding.viewPager.adapter = DetailsViewPagerAdapter(supportFragmentManager, lifecycle,
//            poster, movieId)
//
//        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, position ->
//            when(position) {
//                0 -> tab.text = getString(R.string.poster)
//                1 -> tab.text = getString(R.string.details)
//            }
//        }
//
//        tabMediator.attach()
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        tabMediator.detach()
//    }
//
////    override fun setupPosterImage(url: String) {
////        Glide.with(applicationContext)
////            .load(url)
////            .into(poster)
////    }
//}