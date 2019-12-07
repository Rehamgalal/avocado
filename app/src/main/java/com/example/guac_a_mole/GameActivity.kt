package com.example.guac_a_mole

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_game.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
var t:CountDownTimer?=null

    var userName:String?=null
    var views:ArrayList<ImageView>?=null
    var containers:ArrayList<LinearLayout>?=null
    var score=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
       userName= intent.getStringExtra("name")
        username.text = userName
        views= ArrayList()
        views!!.add(image_1)
        views!!.add(image_2)
        views!!.add(image_3)
        views!!.add(image_4)
        views!!.add(image_5)
        containers= ArrayList()
        containers!!.add(view_1)
        containers!!.add(view_2)
        containers!!.add(view_3)
        containers!!.add(view_4)
        containers!!.add(view_5)
        for (i in 0 until containers!!.size){
            containers!![i].setOnClickListener {
                if (views!![i].visibility== View.VISIBLE){
                    score += 25
                    score_actual.text= score.toString()
                    views!![i].setImageResource(R.drawable.guacamole)
                }
            }
        }


    }

    override fun onResume() {
        super.onResume()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
     t=  object :CountDownTimer(15000, 1000) {
            override fun onFinish() {
                val intent= Intent(this@GameActivity,ResultsActivity::class.java)
                intent.putExtra("name",userName)
                intent.putExtra("score",score)
                startActivity(intent)
            }

            override fun onTick(p0: Long) {
                if (p0 < 10000L){
                    timer.setTextColor(Color.RED)
                    if (timer.visibility== View.VISIBLE){
                        timer.visibility= View.GONE
                    }else{
                        timer.visibility=View.VISIBLE
                    }
                }
                val minutes = (p0 / 1000)  / 60
                val seconds = ((p0 / 1000) % 60)

                timer.text = minutes.toString()+":"+seconds
                val i = java.util.Random()
               val first= i.nextInt(5)
                val second= i.nextInt(5)
                for (a in views!!){
                    a.setImageResource(R.drawable.avocado)
                    a.visibility= View.GONE
                }
                views!![first].visibility= View.VISIBLE
                views!![second].visibility= View.VISIBLE
            }

        }
        t!!.start()
    }

    override fun onPause() {
        super.onPause()
        t!!.cancel()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

    }

}
