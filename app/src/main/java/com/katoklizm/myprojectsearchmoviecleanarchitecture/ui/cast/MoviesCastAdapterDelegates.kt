package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.cast

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.katoklizm.myprojectsearchmoviecleanarchitecture.core.ui.RVItem
import com.katoklizm.myprojectsearchmoviecleanarchitecture.databinding.ListItemCastBinding
import com.katoklizm.myprojectsearchmoviecleanarchitecture.databinding.ListItemHeaderBinding
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.cast.MoviesCastRVItem

// Делегат для заголовков на экране состава участников
fun movieCastHeaderDelegate() = adapterDelegateViewBinding<MoviesCastRVItem.HeaderItem, RVItem, ListItemHeaderBinding>(
    { layoutInflater, root -> ListItemHeaderBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.headerTextView.text = item.headerText
    }
}

// Делегат для отображения участников на соответствующем экране
fun movieCastPersonDelegate() = adapterDelegateViewBinding<MoviesCastRVItem.PersonItem, RVItem, ListItemCastBinding>(
    { layoutInflater, root -> ListItemCastBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        if (item.data.image == null) {
            binding.actorImageView.isVisible = false
        } else {
            Glide.with(itemView)
                .load(item.data.image)
                .into(binding.actorImageView)
            binding.actorImageView.isVisible = true
        }

        binding.actorNameTextView.text = item.data.name
        binding.actorDescriptionTextView.text = item.data.description
    }
}