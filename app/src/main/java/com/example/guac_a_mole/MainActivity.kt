package com.example.guac_a_mole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachListeners()
    }

    private fun attachListeners() {
        start.setOnClickListener {
            val name: String = if (username.text.toString().isNotEmpty()) {
                "..."
            } else {
                username.text.toString()
            }
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GameActivity.USER_NAME_KEY, name)
            startActivity(intent)
        }
    }
}
