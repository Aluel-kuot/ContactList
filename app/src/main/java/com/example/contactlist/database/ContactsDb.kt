package com.example.contactlist.database

import android.content.Context
import android.provider.ContactsContract.Contacts
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactlist.Model.ContactData


@Database(entities = arrayOf(ContactData::class), version = 1)

    abstract class ContactsDb: RoomDatabase() {

        abstract fun getContactDao(): ContactDao
        companion object{
            private var database: ContactsDb? = null
            fun getDatabase(context:Context):ContactsDb{
                if (database==null){
                    database = Room.databaseBuilder(context,
                        ContactsDb::class.java, "ContactsDb")
                        .build()
                }
                return database as ContactsDb
            }
        }

    }


