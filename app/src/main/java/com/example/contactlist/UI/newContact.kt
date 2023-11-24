package com.example.contactlist.UI

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.Contacts
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.example.contactlist.Manifest
import com.example.contactlist.Model.ContactData
import com.example.contactlist.R
import com.example.contactlist.databinding.ActivityNewContactBinding
import com.example.contactlist.viewmodel.ContactViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File

class NewContact : AppCompatActivity() {
    private lateinit var binding: ActivityNewContactBinding
    private val contactViewModel:ContactViewModel by viewModels()
    private lateinit var photoFile: File
lateinit var fusedLocationClient :FusedLocationProviderClient
lateinit var myLocation:Location



    val cameraLauncher=
        registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                Picasso.get()
                    .load(File(photoFile.absolutePath))
                    .resize(80, 80)
                    .centerInside()
                    .transform(CropCircleTransformation())
                    .into(binding.ivImage)

            }
            val locationRequest =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                    if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        getLastKnownLocation()
                    } else {
                        Toast.makeText(
                            this, "Please grant location permission",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityNewContactBinding.inflate(layoutInflater)
                setContentView(binding.root)

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            }
            binding.btnBack.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            binding.btnSave.setOnClickListener {
                validateNew()
            }
        }
    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            validateNew()
        }
        binding.ivImage.setOnClickListener {
            capturePhoto()
        }

        getLastKnownLocation()
    }
    fun getLastKnownLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                myLocation = location
                Toast.makeText(
                    this,
                    "Lat:${location.latitude},Long:${location.longitude}",
                    Toast.LENGTH_LONG
                ).show()
            }

        } else {
            locationRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        fun locationUpdates() {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                val locationRequest = LocationRequest.Builder(10000)
                    .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                    .build()

                val locationCallBack = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        super.onLocationResult(p0)
                        for (location in p0.locations) {
                            Toast.makeText(
                                baseContext,
                                "Lat:${location.latitude},Long:${location.longitude}",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                }
                fusedLocationClient.requestLocationUpdates()

            }

        }


        private fun capturePhoto() {
            val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile("photo_${System.currentTimeMillis()}")
            val fileUri = FileProvider.getUriForFile(this, "com.example.contactlist.UI", photoFile)
            photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            cameraLauncher.launch(photoIntent)
        }



        private fun getPhotoFile(fileName: String): File {
            val folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            var tempFile = File.createTempFile(fileName, "jpeg", folder)
            return tempFile
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
            val newContact = ContactData(
                contactId = 0,
                name = name,
                phoneNumber = phoneNumber,
                emailAddress = email,
                imageUrl = photoFile.absolutePath
            )
            contactViewModel.saveContact(newContact)
            Toast.makeText(this, "Contact saved", Toast.LENGTH_LONG).show()
            finish()

        }}}



