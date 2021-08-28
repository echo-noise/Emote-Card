package com.github.echo_noise.emotecard.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.echo_noise.emotecard.data.EmoteCard
import com.github.echo_noise.emotecard.databinding.ItemEmoteCardBinding

class EmoteCardAdapter :
    ListAdapter<EmoteCard, EmoteCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmoteCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    public override fun getItem(position: Int): EmoteCard {
        return super.getItem(position)
    }

    inner class ViewHolder( private val binding: ItemEmoteCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EmoteCard) {
            binding.ivCardIcon.setImageDrawable(null)
            binding.tvCardTitle.text = item.title
            binding.tvCardContent.text = item.content
            binding.mcvContent.setCardBackgroundColor(item.bgColor)
            binding.mcvContent.setOnLongClickListener {
                listenerShare(it)
                return@setOnLongClickListener true
            }
            binding.ivCardIcon.setImageResource(item.icon)
        }

    }
}

class DiffCallback: DiffUtil.ItemCallback<EmoteCard>() {
    override fun areItemsTheSame(oldItem: EmoteCard, newItem: EmoteCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: EmoteCard, newItem: EmoteCard) = oldItem.id == newItem.id
}