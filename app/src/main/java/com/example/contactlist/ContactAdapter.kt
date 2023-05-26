package com.example.contactlist


    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import androidx.recyclerview.widget.RecyclerView.ViewHolder
    import com.example.contactlist.databinding.ContactListItemBinding

    class ContactAdapter(var ContactList:List<ContactData>) : RecyclerView.Adapter<ContactViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
            val binding =
                ContactListItemBinding .inflate(LayoutInflater.from(parent.context),parent ,false)
            return ContactViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return ContactList.size
        }

        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            var currentContact =ContactList.get(position)
            var  binding=holder.binding
            binding.tvName.text=currentContact.name
            binding.tvPhoneNumber.text=currentContact.phoneNumber.toString()
            binding.tvEmailAddress.text =currentContact.emailAddress

        }
    }
    class ContactViewHolder( var binding:ContactListItemBinding):ViewHolder(binding.root)





