package com.example.contactlist.repository

import android.provider.ContactsContract.Contacts
import androidx.lifecycle.LiveData
import com.example.contactlist.Model.ContactData
import com.example.contactlist.database.ContactDao
import com.example.contactlist.database.ContactsDb
import com.example.contactlist.viewmodel.MyContactsApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ContactRepository {
val database= ContactsDb.getDatabase(MyContactsApp.appContext)

    suspend fun saveContact(contacts:ContactData){
        withContext(Dispatchers.IO){
            database.getContactDao().insertContact(contacts)
        }
    }
    fun getAllContacts():LiveData<List<ContactData>>{
        return  database.getContactDao().getAllContacts()
    }
    fun getContactById(contactId: Int): LiveData<ContactData> {
        return database.getContactDao().getContactById(contactId)
    }
}