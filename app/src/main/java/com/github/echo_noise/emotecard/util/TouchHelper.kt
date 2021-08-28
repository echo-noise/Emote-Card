package com.github.echo_noise.emotecard.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.echo_noise.emotecard.ui.EmoteCardAdapter
import com.github.echo_noise.emotecard.ui.MainViewModel
import kotlin.concurrent.thread

class TouchHelper {
    class TouchHelper(private val mainViewModel: MainViewModel, private val adapter : EmoteCardAdapter) : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
            thread {
                mainViewModel.deleteCard(adapter.getItem(viewHolder.adapterPosition))
            }
        }
    }
}