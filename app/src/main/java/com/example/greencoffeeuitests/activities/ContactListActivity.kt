package com.example.greencoffeeuitests.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.greencoffeeuitests.R
import com.example.greencoffeeuitests.adapters.ContactAdapter
import com.example.greencoffeeuitests.database.ContactDatabase
import com.example.greencoffeeuitests.model.Contact

class ContactListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        title = "People"

        val contactDatabase = ContactDatabase()
        val contacts = contactDatabase.contacts("admin")
        val listView = findViewById<ListView>(R.id.contacts_list)
        if (!contacts.isEmpty()) {
            listView.adapter = ContactAdapter(this, contacts)
            listView.onItemClickListener = OnItemClickListener { parent: AdapterView<*>, view: View?, position: Int, id: Long ->
                val contact = parent.getItemAtPosition(position) as Contact
                onContactSelected(contact)
            }
        } else {
            listView.visibility = View.GONE
            val labelEmptyList = findViewById<TextView>(R.id.contacts_label_emptyList)
            labelEmptyList.visibility = View.VISIBLE
        }
    }

    private fun onContactSelected(contact: Contact) {
        startActivity(DetailsActivity.create(this, contact.id()))
    }

    companion object {
        private const val PARAMETER_USERNAME = "parameter.username"
        @JvmStatic
        fun create(context: Context?, username: String): Intent {
            val intent = Intent(context, ContactListActivity::class.java)
            intent.putExtra(PARAMETER_USERNAME, username)
            return intent
        }
    }
}