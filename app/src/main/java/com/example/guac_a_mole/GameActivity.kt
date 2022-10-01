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

class GameActivity : AppCompatActivity() {

    var userName: String? = null
    lateinit var views: ArrayList<ImageView>
    lateinit var containers: ArrayList<LinearLayout>
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setData()
        initUi()
        attachListeners()
    }

    private val countDownTimer =
        object : CountDownTimer(GAME_TIME_IN_MILLIS, GAME_INTERVAL_TIME_IN_MILLIS) {
            override fun onFinish() {
                val intent = Intent(this@GameActivity, ResultsActivity::class.java)
                intent.putExtra(USER_NAME_KEY, userName)
                intent.putExtra(ResultsActivity.PLAYER_SCORE_KEY, score)
                startActivity(intent)
            }

            override fun onTick(p0: Long) {
                warnEndOfGame(p0)
                updatePlayingTime(p0)
                updateAvocadoViews()
            }

        }

    private fun setData() {
        userName = intent.getStringExtra(USER_NAME_KEY)
    }

    private fun initUi() {
        username.text = userName
        views = arrayListOf(image_1, image_2, image_3, image_4, image_5)
        containers = arrayListOf(view_1, view_2, view_3, view_4, view_5)
    }

    private fun attachListeners() {
        for (i in 0 until containers.size) {
            containers[i].setOnClickListener {
                if (views[i].visibility == View.VISIBLE) {
                    score += 25
                    score_actual.text = score.toString()
                    views[i].setImageResource(R.drawable.guacamole)
                }
            }
        }
    }

    private fun warnEndOfGame(elapsedTimeInMillis: Long) {
        if (elapsedTimeInMillis < END_OF_GAME_WARNING_TIME) {
            timer.setTextColor(Color.RED)
            if (timer.visibility == View.VISIBLE) {
                timer.visibility = View.GONE
            } else {
                timer.visibility = View.VISIBLE
            }
        }
    }

    private fun updatePlayingTime(elapsedTimeInMillis: Long) {
        val minutes = (elapsedTimeInMillis / 1000) / 60
        val seconds = ((elapsedTimeInMillis / 1000) % 60)
        timer.text = "$minutes:$seconds"
    }

    private fun updateAvocadoViews() {
        val i = java.util.Random()
        val first = i.nextInt(5)
        val second = i.nextInt(5)
        for (a in views) {
            a.setImageResource(R.drawable.avocado)
            a.visibility = View.GONE
        }
        views[first].visibility = View.VISIBLE
        views[second].visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        countDownTimer.start()
    }

    override fun onPause() {
        super.onPause()
        countDownTimer.cancel()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

    }

    companion object {
        const val USER_NAME_KEY = "username"
        const val GAME_TIME_IN_MILLIS = 15000L
        const val GAME_INTERVAL_TIME_IN_MILLIS = 1000L
        const val END_OF_GAME_WARNING_TIME = 10000L
    }

}
