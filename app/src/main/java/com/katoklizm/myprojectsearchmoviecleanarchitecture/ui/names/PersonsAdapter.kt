package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.names

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Person

class PersonsAdapter : RecyclerView.Adapter<PersonViewHolder>() {

    val person = mutableListOf<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder = PersonViewHolder(parent)

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(person[position])
    }

    override fun getItemCount(): Int = person.size

}