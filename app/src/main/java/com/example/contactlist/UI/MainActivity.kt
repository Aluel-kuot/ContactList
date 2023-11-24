package com.example.contactlist.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlist.Model.ContactData
import com.example.contactlist.databinding.ActivityMainBinding
import com.example.contactlist.viewmodel.ContactViewModel


class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    val contactViewModel:ContactViewModel by viewModels()
    lateinit var btnAddContact:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.fabNew.setOnClickListener{
            val intent=Intent(this, NewContact::class.java)
            startActivity(intent)
        }
        }


    override fun onResume() {
        super.onResume()
        contactViewModel.getContacts().observe(this, Observer { contactList-> displayContact(contactList) })
        binding.fabNew.setOnClickListener{
            startActivity(Intent(this,NewContact::class.java))
        }
    }
    fun displayContact(contactList:List<ContactData>){
        val contactAdapter= ContactAdapter(contactList,this)
        binding.rvContacts.layoutManager=LinearLayoutManager(this)
        binding.rvContacts.adapter=contactAdapter
    }
}


