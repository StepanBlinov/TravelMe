package com.example.travelme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import androidx.recyclerview.widget.RecyclerView
import com.example.travelme.R
import com.example.travelme.databinding.ListItemBinding
import com.example.travelme.model.TicketsItem

class TicketsAdapter(private val onItemClicked: (TicketsItem) -> Unit) :
    ListAdapter<TicketsItem, TicketsAdapter.TicketsViewHolder>(DiffCallback) {


    private lateinit var context: Context

    class TicketsViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //устанавливаем атрибуты для каждого билета в ячейке
        fun bind(ticket: TicketsItem, context: Context) {
            binding.icon.load(ticket.iconAvia)
            binding.departure.text = ticket.departureCity
            binding.arrival.text = ticket.arrivalCity
            binding.arrowIcon.load(R.drawable.down_arrow_icon)
            binding.date.text =
                context.getString(R.string.day_of_departure).format(ticket.departureDate)
            binding.time.text =
                context.getString(R.string.flight_time).format(ticket.flightTime.toString())

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        context = parent.context
        return TicketsViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        //передаем ид каждой позиции в метод bind
        holder.bind(current, context)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TicketsItem>() {
            override fun areItemsTheSame(oldItem: TicketsItem, newItem: TicketsItem): Boolean {
                return (oldItem.id == newItem.id ||
                        oldItem.departureCity == newItem.departureCity ||
                        oldItem.arrivalCity == newItem.arrivalCity)
            }

            override fun areContentsTheSame(oldItem: TicketsItem, newItem: TicketsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}