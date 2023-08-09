package com.example.contactlist.UI


    import android.content.Context
    import android.content.Intent
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import androidx.recyclerview.widget.RecyclerView.ViewHolder
    import com.example.contactlist.Model.ContactData
    import com.example.contactlist.R
    import com.example.contactlist.databinding.ContactListItemBinding
    import com.squareup.picasso.Picasso
    import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ContactAdapter(var contactList:List<ContactData>,var context:Context ): RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding =
            ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var currentContact = contactList[position]
        var binding = holder.binding
        binding.tvName.text = currentContact.name
        binding.tvPhoneNumber.text = currentContact.phoneNumber.toString()
        binding.tvEmailAddress.text = currentContact.emailAddress

        if (currentContact.imageUrl.isNotBlank()){
        Picasso
            .get()
            .load(currentContact.imageUrl)
            .placeholder(R.drawable.mary)
            .error(R.drawable.mary)
            .transform(CropCircleTransformation())
            .into(binding.ivImage)
    }
        binding.cvContact.setOnClickListener {
            val intent = Intent(context, ContactDetailsActivity::class.java)
            intent.putExtra("CONTACT_ID",currentContact.contactId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}
    class ContactViewHolder( var binding:ContactListItemBinding):ViewHolder(binding.root)





