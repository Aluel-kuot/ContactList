package com.example.contactlist


    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import androidx.recyclerview.widget.RecyclerView.ViewHolder
    import com.example.contactlist.databinding.ContactListItemBinding
    import com.squareup.picasso.Picasso
    import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ContactAdapter(var ContactList:List<ContactData>) : RecyclerView.Adapter<ContactViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val binding =
                ContactListItemBinding .inflate(LayoutInflater.from(parent.context),parent ,false)
            return ContactViewHolder(binding)
        }


        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            var currentContact =ContactList[position]
            var  binding=holder.binding
            binding.tvName.text=currentContact.name
            binding.tvPhoneNumber.text=currentContact.phoneNumber.toString()
            binding.tvEmailAddress.text =currentContact.emailAddress
            Picasso
                .get()
                .load(currentContact.image)
//                .resize(50,50)
//                .centerCrop()
                .placeholder(R.drawable.mary)
                .error(R.drawable.mary)
                .transform(CropCircleTransformation())
                .into(binding.ivImage)

        }
        override fun getItemCount(): Int {
            return ContactList.size
        }

    }
    class ContactViewHolder( var binding:ContactListItemBinding):ViewHolder(binding.root)





