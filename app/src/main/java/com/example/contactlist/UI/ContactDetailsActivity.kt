package com.example.contactlist.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.contactlist.Model.ContactData
import com.example.contactlist.R
import com.example.contactlist.databinding.ActivityContactDetailsBinding
import com.example.contactlist.viewmodel.ContactViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

//class ContactDetailsActivity : AppCompatActivity() {
//    var contactId=0
//    lateinit var binding: ActivityContactDetailsBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_contact_details)
//        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        var bundle=intent.extras
//        if (bundle!=null){
//            contactId=bundle.getInt("CONTACT_ID",0)
//            Toast.makeText(this,"$contactId",Toast.LENGTH_LONG).show()
//        }
//    }
//
//}

class ContactDetailsActivity : AppCompatActivity() {
    var contactId = 0
    private lateinit var viewModel: ContactViewModel

    lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDelete.setOnClickListener {
        }

        viewModel = ContactViewModel()
        val contactId = intent.getIntExtra("CONTACT_ID", 0)
        viewModel.getContactsById(contactId).observe(this, Observer{ contact ->
            if (contact != null) {
                displayContactDetails(contact)
            } else {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun displayContactDetails(contact: ContactData) {
        binding.tvName.text = contact.name
        binding.tvPhoneNumber.text = contact.phoneNumber
        binding.tvEmail.text = contact.emailAddress
        if (!contact.imageUrl.isNullOrEmpty()) {
            Picasso
                .get()
                .load(contact.imageUrl)
                .resize(80, 80)
                .centerCrop()
                .transform(CropCircleTransformation())
                .into(binding.ivImage)
        }
    }}



