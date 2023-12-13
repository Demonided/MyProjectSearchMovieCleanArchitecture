package com.katoklizm.myprojectsearchmoviecleanarchitecture.ui.names

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.katoklizm.myprojectsearchmoviecleanarchitecture.databinding.FragmentNamesBinding
import com.katoklizm.myprojectsearchmoviecleanarchitecture.domain.models.Person
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.names.NamesState
import com.katoklizm.myprojectsearchmoviecleanarchitecture.presentation.names.NamesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.jar.Attributes.Name

class NamesFragment: Fragment() {

    private val viewModel by viewModel<NamesViewModel>()

    private val adapter = PersonsAdapter()

    private var _binding: FragmentNamesBinding? = null
    val binding
        get() = _binding!!

    private lateinit var textWatcher: TextWatcher

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.personsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.personsList.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchDebounce(
                    changetText = p0?.toString() ?: ""
                )
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }

        textWatcher.let { binding.queryInput.addTextChangedListener(it) }

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.observeShowToast().observe(viewLifecycleOwner) {
            showToast(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        textWatcher.let { binding.queryInput.removeTextChangedListener(it) }
    }

    private fun showToast(additionalMessage: String?) {
        Toast.makeText(requireContext(), additionalMessage, Toast.LENGTH_LONG).show()
    }

    private fun render(state: NamesState){
        when(state) {
            is NamesState.Content -> showContent(state.persons)
            is NamesState.Empty -> showEmpty(state.message)
            is NamesState.Error -> showError(state.message)
            is NamesState.Loading -> showLoading()
        }
    }

    private fun showContent(person: List<Person>) {
        with(binding) {
            personsList.visibility = View.VISIBLE
            placeholderMessage.visibility = View.GONE
            progressBar.visibility = View.GONE
        }

        adapter.person.clear()
        adapter.person.addAll(person)
        adapter.notifyDataSetChanged()
    }

    private fun showEmpty(emptyMessage: String) {
        showError(emptyMessage)
    }

    private fun showError(errorMessage: String) {
        with(binding) {
            personsList.visibility = View.GONE
            placeholderMessage.visibility = View.VISIBLE
            progressBar.visibility = View.GONE

            placeholderMessage.text = errorMessage
        }
    }

    private fun showLoading() {
        with(binding) {
            personsList.visibility = View.GONE
            placeholderMessage.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }
}