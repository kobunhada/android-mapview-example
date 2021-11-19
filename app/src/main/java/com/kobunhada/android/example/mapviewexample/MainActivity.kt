package com.kobunhada.android.example.mapviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = MapsFragment()
        val transaction =
            supportFragmentManager.beginTransaction()
        transaction.add(com.google.android.material.R.id.container, mapFragment)
        transaction.commit()
    }
}