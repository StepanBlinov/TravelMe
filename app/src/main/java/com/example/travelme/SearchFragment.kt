package com.example.travelme

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.travelme.databinding.FragmentSearchBinding
import com.example.travelme.model.TicketViewModel


class SearchFragment : Fragment() {

    // Binding object instance corresponding to the fragment_start_order.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentSearchBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: TicketViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            searchFragment = this@SearchFragment
        }
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, sharedViewModel.cityNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        //заполняем городами спиннер 1
        val spinner = binding.firstCity
        spinner.adapter = adapter
        spinner.setSelection(sharedViewModel.departureCity.value!!)

        //заполняем городами спиннер 2
        val secondSpinner = binding.secondCity
        secondSpinner.adapter = adapter
        secondSpinner.setSelection(sharedViewModel.arrivalCity.value!!)

        //получаем данные из спиннера 1
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                sharedViewModel.chooseCity(position)
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }

        //получаем данные из спиннера 2
        secondSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                sharedViewModel.chooseCity(position, false)
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }

    //переходим к следующему фрагменту
    @RequiresApi(Build.VERSION_CODES.O)
    fun goToNextScreen(){
        if (sharedViewModel.departureCity.value == 0 && sharedViewModel.arrivalCity.value == 1){
            findNavController().navigate(R.id.action_searchFragment_to_ticketsFragment)
        }
        else{
            findNavController().navigate(R.id.action_searchFragment_to_noTicketsFragment)
        }
    }
}