//package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.cast
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.core.view.isVisible
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.R
//import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.cast.MoviesCastRVItem
//
//class MovieCastHeaderViewHolder(parent: ViewGroup) :
//RecyclerView.ViewHolder(
//    LayoutInflater.from(parent.context)
//        .inflate(R.layout.list_item_cast, parent, false)
//){
//
//    var headerTextView: TextView = itemView.findViewById(R.id.headerTextView)
//
//    fun bind(item: MoviesCastRVItem.HeaderItem) {
//        headerTextView.text = item.headerText
//    }
//}