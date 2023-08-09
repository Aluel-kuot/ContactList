package com.example.contactlist.viewmodel

import android.provider.ContactsContract.Contacts
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactlist.Model.ContactData
import com.example.contactlist.repository.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel :ViewModel(){
    val contactsRepo=ContactRepository()

    fun saveContact(contact:ContactData){
        viewModelScope.launch {
            contactsRepo.saveContact(contact)
        }
    }
     fun getContacts():LiveData<List<ContactData>>{
         return contactsRepo.getAllContacts()
     }
    fun getContactsById(contactId:Int): LiveData<ContactData> {
        return  contactsRepo.getContactById(contactId)
    }
}