package com.example.greencoffeeuitests.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.greencoffeeuitests.R
import com.example.greencoffeeuitests.database.ContactDatabase

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setTitle(R.string.details_title)

        val contactId = intent.getStringExtra(PARAMETER_CONTACT_ID)
        val contactDatabase = ContactDatabase()
        val contact = contactDatabase.contact(contactId!!)
        val contactName = findViewById<TextView>(R.id.contact_detail_name)
        contactName.text = contact.name()
        val contactAge = findViewById<TextView>(R.id.contact_detail_age)
        contactAge.text = contact.age()
        val contactWeight = findViewById<TextView>(R.id.contact_detail_weight)
        contactWeight.text = contact.weight()
        val contactMarried = findViewById<TextView>(R.id.contact_detail_married)
        contactMarried.text = contact.married(this)
    }

    companion object {
        private const val PARAMETER_CONTACT_ID = "parameter.contact.id"
        fun create(context: Context, contactId: String): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(PARAMETER_CONTACT_ID, contactId)
            return intent
        }
    }
}