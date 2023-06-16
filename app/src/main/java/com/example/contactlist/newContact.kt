package com.example.contactlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactlist.databinding.ActivityNewContactBinding

class NewContact : AppCompatActivity() {
    lateinit var binding: ActivityNewContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        binding.btnSave.setOnClickListener {
            clearErrors()
            validateNew()
        }
    }

    fun validateNew() {
        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        var error = false

        if (name.isBlank()) {
            binding.tilName.error = "name is required"
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilPhoneNumber.error = "phoneNumber is required"
            error = true
        }
        if (email.isBlank()) {
            binding.tilEmail.error = "email is required"
            error = true
        }

        if (!error) {
            Toast.makeText(
                this, "$name $phoneNumber $email",
                Toast.LENGTH_LONG
            ).show()

        }
    }

    fun clearErrors() {
        binding.tilName.error = null
        binding.tilPhoneNumber.error = null
        binding.tilEmail.error = null

    }
}
