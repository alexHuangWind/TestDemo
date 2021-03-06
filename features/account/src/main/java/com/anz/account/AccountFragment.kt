package com.anz.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anz.account.databinding.AccountFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val viewModel by viewModels<AccountViewModel>()
    private lateinit var binding: AccountFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AccountFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.accountBalence.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AccountState.Success -> binding.balanceText.text = "  ${state.data}$"
                is AccountState.GenericError -> {
                    //TODO Show error message
                }
                is AccountState.Loading -> {
                    //TODO Show loading view
                }
            }

        }
        viewModel.accountName.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AccountState.Success -> binding.nameText.text = state.data
                is AccountState.GenericError -> {
                    //TODO Show error message
                }
                is AccountState.Loading -> {
                    //TODO Show loading view
                }
            }

        }
    }

}