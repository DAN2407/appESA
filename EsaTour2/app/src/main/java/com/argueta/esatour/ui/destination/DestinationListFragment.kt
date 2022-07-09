package com.argueta.esatour.ui.destination

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.argueta.esatour.DestinationUiState
import com.argueta.esatour.EsaTourApplication
import com.argueta.esatour.R
import com.argueta.esatour.databinding.FragmentDestinationListBinding
import com.argueta.esatour.ui.ViewModelFactory

class DestinationListFragment : Fragment() {

    private val viewModelFactory by lazy {
        val application = requireActivity().application as EsaTourApplication
        ViewModelFactory(application.getDestinationRepository())
    }

    private val viewModel: DestinationViewModel by viewModels{
        viewModelFactory
    }

    private lateinit var binding: FragmentDestinationListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_destination_list, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destinationListRecyclerView = binding.destinationListRecyclerVie
        val destinationAdapter = DestinationAdapter()
        destinationListRecyclerView.apply{
            adapter = destinationAdapter
        }

        viewModel.getAllDestinations()

        viewModel.status.observe(viewLifecycleOwner) { status ->
            when(status){
                is DestinationUiState.Error -> Log.d(
                    "DestinationListFragment",
                    "Error",
                    status.exception
                )
                DestinationUiState.Loading -> Log.d(
                    "DestinationListFragment",
                    "Loading"
                )
                is DestinationUiState.Success -> status.destinations.isEmpty().let {
                    Log.d("DestinationListFragment", "Success")
                    destinationAdapter.submitList(status.destinations)
                }
            }

        }
    }


}