package com.mikelcalvo.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mikelcalvo.passwordgenerator.databinding.ActivityMainBinding
import com.mikelcalvo.passwordgenerator.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        setUp()
    }

    fun setUp() {
        val initialLength = binding.lengthSeekBar.progress
        binding.lengthLabelTextView.text = getString(R.string.length_label, initialLength)


        binding.lengthSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.lengthLabelTextView.text = getString(R.string.length_label, progress)

                generatePassword()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.useUppercaseCheckBox.setOnCheckedChangeListener { _, _ ->
            generatePassword()
        }

        binding.useNumbersCheckBox.setOnCheckedChangeListener { _, _ ->
            generatePassword()
        }

        binding.useSpecialCharsCheckBox.setOnCheckedChangeListener { _, isChecked ->
            val length = binding.lengthSeekBar.progress
            val useUppercase = binding.useUppercaseCheckBox.isChecked
            val useNumbers = binding.useNumbersCheckBox.isChecked

            val password = viewModel.generatePassword(length, useUppercase, useNumbers, isChecked)
            binding.passwordTextView.text = password
        }

        binding.generateButton.setOnClickListener {
            generatePassword()
        }

        binding.copyButton.setOnClickListener {
            val password = binding.passwordTextView.text.toString()
            if (password.isNotEmpty()) {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Generated Password", password)
                clipboard.setPrimaryClip(clip)

                Toast.makeText(this, getString(R.string.password_copied), Toast.LENGTH_SHORT).show()
            }
        }

        generatePassword()
    }

    fun generatePassword() {
        val length = binding.lengthSeekBar.progress
        val useUppercase = binding.useUppercaseCheckBox.isChecked
        val useNumbers = binding.useNumbersCheckBox.isChecked
        val useSpecialChars = binding.useSpecialCharsCheckBox.isChecked

        val password = viewModel.generatePassword(length, useUppercase, useNumbers, useSpecialChars)
        binding.passwordTextView.text = password
    }
}