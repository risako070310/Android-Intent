package com.risako070310.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val readRequestCode: Int = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentButton.setOnClickListener {
            val toSecondActivity = Intent(this, SecondActivity::class.java)
            startActivity(toSecondActivity)
        }

        playstoreButton.setOnClickListener {
            val playStore = Intent(Intent.ACTION_VIEW)
            playStore.data = Uri.parse("https://play.google.com/store/apps")
            playStore.setPackage("com.android.vending")
            startActivity(playStore)
        }

        mapButton.setOnClickListener {
            val map = Intent(Intent.ACTION_VIEW)
            map.data = Uri.parse("geo.35.6473,139.7360")
            if(map.resolveActivity(packageManager) != null){
                startActivity(map)
            }
        }

        browserButton.setOnClickListener {
            val browser = Intent(Intent.ACTION_VIEW)
            browser.data = Uri.parse("https://life-is-tech.com/")
            if (browser.resolveActivity(packageManager) != null) {
                startActivity(browser)
            }
        }

        galleryButton.setOnClickListener {
            val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT)
            gallery.addCategory(Intent.CATEGORY_OPENABLE)
            gallery.type = "image/*"
            startActivityForResult(gallery, readRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == readRequestCode && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                imageView.setImageURI(uri)
            }
        }
    }
}