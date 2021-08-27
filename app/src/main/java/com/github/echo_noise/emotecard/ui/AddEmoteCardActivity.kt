package com.github.echo_noise.emotecard.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.echo_noise.emotecard.App
import com.github.echo_noise.emotecard.R
import com.github.echo_noise.emotecard.data.EmoteCard
import com.github.echo_noise.emotecard.databinding.ActivityAddEmoteCardBinding
import petrov.kristiyan.colorpicker.ColorPicker

class AddEmoteCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddEmoteCardBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private var bgcolor: Int = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }
        // color picker listener
        binding.btnColorPicker.setOnClickListener {
            val colorPicker = ColorPicker(this)
            val listener = object : ColorPicker.OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                    binding.root.setBackgroundColor(color)
                    bgcolor = color
                }

                override fun onCancel() {}
            }

            colorPicker.setOnChooseColorListener(listener).setTitle("Escolher cor").show()
            colorPicker.negativeButton.text = getString(R.string.button_cancel)
        }

        // save button listener
        binding.btnSave.setOnClickListener {
            val emoteCard = EmoteCard (
                title = binding.tilCardTitle.editText?.text.toString(),
                content = binding.tilCardContent.editText?.text.toString(),
                bgColor = bgcolor,
                icon = when (binding.rgEmotes.checkedRadioButtonId) {
                             binding.rbHappy.id -> R.drawable.ic_emote_happy_48
                             binding.rbVeryHappy.id -> R.drawable.ic_emote_veryhappy_48
                             binding.rbSad.id -> R.drawable.ic_emote_sad_48
                             binding.rbVerySad.id -> R.drawable.ic_emote_verysad_48
                             else -> R.drawable.ic_emote_neutral_48
                }
            )
            mainViewModel.insert(emoteCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_LONG).show()
            finish()
        }
    }
}