package com.example.guac_a_mole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val name=intent.getStringExtra("name")
        username.text=name
        val scorenum = intent.getIntExtra("score",0)

        score.text=scorenum.toString()
        start.setOnClickListener {
            val intent= Intent(this, GameActivity::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        }
    }
}
