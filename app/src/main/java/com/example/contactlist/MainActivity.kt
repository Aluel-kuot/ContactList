package com.example.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        displayContact()
    }
    fun displayContact(){
        val contact1=ContactData("Angela","0789564352","angela@gmail.com")
        val contact2=ContactData("Peter","0789554352","peter@gmail.com")
        val contact3=ContactData("Alice","0782364352","alice@gmail.com")
        val contact4=ContactData("Joyce","0756364352","joyce@gmail.com")
        val contact5=ContactData("Paul","0782365352","paul@gmail.com")
        val contactList= listOf(contact1,contact2,contact3,contact4,contact5)
        val contactAdapter=ContactAdapter(contactList)
        binding.rvContacts.layoutManager=LinearLayoutManager(this)
        binding.rvContacts.adapter=contactAdapter
    }
}
