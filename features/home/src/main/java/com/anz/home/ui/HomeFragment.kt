package com.anz.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anz.home.HomeState
import com.anz.home.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.accountName.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeState.Success -> binding.nameText.text = state.name
                is HomeState.GenericError -> {
                    //TODO Show error message
                }
                is HomeState.Loading -> {
                    //TODO Show loading view
                }
            }

        }
        binding.termsAndConditionsText.setOnClickListener {
            viewModel.showTermsAndConditions()
        }
    }

}