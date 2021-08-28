package com.github.echo_noise.emotecard.ui

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.ItemTouchHelper
import com.github.echo_noise.emotecard.App
import com.github.echo_noise.emotecard.databinding.ActivityMainBinding
import com.github.echo_noise.emotecard.util.Image
import com.github.echo_noise.emotecard.util.TouchHelper

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { EmoteCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpPermissions()
        binding.rvCardList.adapter = adapter
        setUpRecyclerView()
        getAllEmoteCards()
        setListeners()
    }

    private fun setUpRecyclerView() {
        val touchHelper = ItemTouchHelper(TouchHelper.TouchHelper(mainViewModel, adapter))
        touchHelper.attachToRecyclerView(binding.rvCardList)
    }

    private fun setUpPermissions() {
        // write permission to access the storage
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
    }

    private fun setListeners() {
        binding.fabNewEntry.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEmoteCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

   private fun getAllEmoteCards() {
       mainViewModel.getAll().observe(this, { emoteCards ->
           adapter.submitList(emoteCards)
       })
   }
}