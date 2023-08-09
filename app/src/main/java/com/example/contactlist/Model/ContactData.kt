package com.example.contactlist.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Contacts")
class ContactData (
    @PrimaryKey(autoGenerate = true)
    var contactId:Int,
    var name:String,
    var phoneNumber:String,
    var emailAddress:String,
    var imageUrl:String
)

