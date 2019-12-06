package com.example.guac_a_mole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
start.setOnClickListener {
    val name:String
    if(username.text.toString().equals("")){
        name= "..."
    }else{
        name= username.text.toString()
    }
    val intent= Intent(this, GameActivity::class.java)
    intent.putExtra("name",name)
    startActivity(intent)
}

    }
}
