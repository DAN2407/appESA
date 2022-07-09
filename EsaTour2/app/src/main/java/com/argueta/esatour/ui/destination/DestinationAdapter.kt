package com.argueta.esatour.ui.destination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.argueta.esatour.R
import com.argueta.esatour.databinding.ItemDestinationBinding
import com.argueta.esatour.models.Destination

class DestinationAdapter : RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder> (){
    inner class DestinationViewHolder(private val binding: ItemDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: Destination) {
            binding.destination = destination
            binding.executePendingBindings()
        }
    }

    private val destinations = mutableListOf<Destination>()

    fun setData(data: List<Destination>) {
        destinations.clear()
        destinations.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DestinationViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_destination,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.bind(destinations[position])
    }

    override fun getItemCount() = destinations.size
    fun submitList(destinations: List<Destination>) {
        this.destinations.clear()
        this.destinations.addAll(destinations)
        notifyDataSetChanged()
    }

}