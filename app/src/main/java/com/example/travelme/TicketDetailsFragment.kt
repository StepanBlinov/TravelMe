package com.example.travelme

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.travelme.databinding.FragmentTicketDetailsBinding
import com.example.travelme.model.TicketViewModel

class TicketDetailsFragment : Fragment() {


    private val sharedViewModel: TicketViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTicketDetailsBinding.inflate(inflater, container, false).root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTicketDetailsBinding.bind(view)

        // Attach an observer on the currentSport to update the UI automatically when the data
        // changes.
        sharedViewModel.currentTicket.observe(this.viewLifecycleOwner) {
            binding.arrival.text = it.arrivalCity
            binding.departure.text = it.departureCity
            binding.data.text = it.departureDate
            binding.time.text = requireContext().getString(R.string.flight_time).format(it.flightTime.toString())
            binding.aviaIcon.load(it.iconAvia)
        }
    }
}