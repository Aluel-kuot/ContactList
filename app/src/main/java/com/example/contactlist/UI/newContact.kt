package com.example.contactlist.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.contactlist.Model.ContactData
import com.example.contactlist.R
import com.example.contactlist.databinding.ActivityNewContactBinding
import com.example.contactlist.viewmodel.ContactViewModel

class NewContact : AppCompatActivity() {
    lateinit var binding: ActivityNewContactBinding
    val contactViewModel:ContactViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        binding.btnSave.setOnClickListener {
            validateNew()
        }
    }
    fun validateNew() {
        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        var error = false

        if (name.isBlank()) {
            binding.tilName.error = getString(R.string.name_is_required)
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilPhoneNumber.error = getString(R.string.phonenumber_is_required)
            error = true
        }
        if (email.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_is_required)
            error = true
        }
        if (!error) {
            val newContact= ContactData(contactId = 0, name = name, phoneNumber = phoneNumber, emailAddress = email, imageUrl = email )
            contactViewModel.saveContact(newContact)
            Toast.makeText(this,"Contact saved",Toast.LENGTH_LONG).show()
            finish()

        }
    }

}
