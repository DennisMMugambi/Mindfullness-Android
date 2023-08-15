package com.example.mindfullnessapp.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mindfullnessapp.Domain.Models.MeditationItem
import com.example.mindfullnessapp.R


class MeditationsAdapter(private var itemList: List<MeditationItem>) : RecyclerView.Adapter<MeditationsAdapter.ViewHolder>() {
    // Define the click listener interface
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    // Member variable to hold the click listener
    private var clickListener: OnItemClickListener? = null

    // Setter method to set the click listener from outside the adapter
    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }
    // Create ViewHolder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Declare views from the item layout
        private val itemNameTextView : TextView = itemView.findViewById(R.id.item_title)
        private val itemImageView : ImageView = itemView.findViewById(R.id.meditation_image)

        init {
            // Set click listener on the item view
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Call the click listener callback method when an item is clicked
                    clickListener?.onItemClick(position)
                }
            }
        }


        fun bind(item: MeditationItem) {
            // Bind data to views

                itemNameTextView.text = item.title
                itemImageView.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the item layout
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_meditation, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to views
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList?.size!!
    }

    // Method to update data and refresh the RecyclerView
    fun updateList(newList: List<MeditationItem>) {
        val diffResult = itemList?.let { ItemDiffCallback(it, newList) }
            ?.let { DiffUtil.calculateDiff(it) }
        itemList = newList
        if (diffResult != null) {
            diffResult.dispatchUpdatesTo(this)
        }
    }
}

class ItemDiffCallback(private val oldList: List<MeditationItem>, private val newList: List<MeditationItem>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        // Compare the contents of items to determine if they are the same
        return oldItem.title == newItem.title
    }
}
