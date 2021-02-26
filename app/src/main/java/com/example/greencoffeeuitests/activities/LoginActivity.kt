package com.example.greencoffeeuitests.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.greencoffeeuitests.R
import com.example.greencoffeeuitests.activities.ContactListActivity.Companion.create
import com.example.greencoffeeuitests.database.UserDatabase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.login_title)

        findViewById<View>(R.id.login_button_doLogin).setOnClickListener { view: View? ->
            val username = findViewById<EditText>(R.id.login_input_username)
            val password = findViewById<EditText>(R.id.login_input_password)
            login(username.text.toString(), password.text.toString())
        }
    }

    private fun login(username: String, password: String) {
        if (validForm(username, password)) {
            if (validCredentials(username, password)) {
                startActivity(create(this, username))
                finish()
            } else {
                errorDialog(R.string.login_credentials_error)
            }
        }
    }

    private fun validForm(username: String, password: String): Boolean {
        if (TextUtils.isEmpty(username)) {
            errorDialog(R.string.login_username_error)
            return false
        } else if (TextUtils.isEmpty(password)) {
            errorDialog(R.string.login_password_error)
            return false
        }
        return true
    }

    private fun validCredentials(username: String, password: String): Boolean {
        val userDatabase = UserDatabase()
        return userDatabase.isValid(username, password)
    }

    private fun errorDialog(message: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle(message)
        builder.setPositiveButton(R.string.dialog_button_ok, null)
        builder.show()
    }
}