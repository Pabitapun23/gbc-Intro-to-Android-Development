package com.pp.multiscreendemo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pp.multiscreendemo.databinding.ActivityYellowBinding

class YellowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYellowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_yellow)

        this.binding = ActivityYellowBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.binding.btnCall.setOnClickListener {
            // using implicit intent, make call

            val callIntent = Intent(Intent.ACTION_CALL).apply {
                // Uri - user resource identifier
                // parse() - specify the telephone number
                data = Uri.parse("tel:0123123879")
            }

            if(callIntent.resolveActivity(packageManager) != null) {
                startActivity(callIntent)
            }
        }

        this.binding.btnEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")  // look for only applications that can send emails
                putExtra(Intent.EXTRA_EMAIL, "user@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "test email")
                putExtra(Intent.EXTRA_TEXT, "This is a test message from Android App")
                setPackage("com.google.android.gms") // Set the package explicitly to Gmail

            }

            if (emailIntent.resolveActivity(packageManager) != null) {
                startActivity(emailIntent)
            } else {
                // Log a message if no email app is available to handle the intent
                Log.e("EmailIntent", "No email app found")
                // If no email app is found, prompt the user to install or choose an app
                Toast.makeText(this, "Please install or select an email app.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}