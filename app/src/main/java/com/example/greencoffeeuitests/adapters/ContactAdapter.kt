package com.example.greencoffeeuitests.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.greencoffeeuitests.R
import com.example.greencoffeeuitests.model.Contact

class ContactAdapter(context: Context?, contacts: List<Contact?>?) : ArrayAdapter<Contact?>(context!!, R.layout.row_contact, contacts!!) {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(getContext())
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_contact, null, false)
        }
        val contact = getItem(position)
        if (contact != null) {
            val firstName = view!!.findViewById<TextView>(R.id.contact_name)
            firstName.text = contact.name()
            val age = view.findViewById<TextView>(R.id.contact_age)
            age.text = contact.age()
            val weight = view.findViewById<TextView>(R.id.contact_weight)
            weight.text = contact.weight()
            val married = view.findViewById<TextView>(R.id.contact_married)
            married.text = contact.married(context)
        }
        return view!!
    }

}