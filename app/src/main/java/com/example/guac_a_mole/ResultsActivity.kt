package com.example.guac_a_mole

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {

    var userName: String? = null
    var playerScore: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        setData()
        setupView()
        attachListeners()
    }

    private fun setData() {
        userName = intent.getStringExtra(GameActivity.USER_NAME_KEY)
        playerScore = intent.getIntExtra(PLAYER_SCORE_KEY, 0)
    }

    private fun setupView() {
        username.text = userName
        score.text = playerScore.toString()
    }

    private fun attachListeners() {
        start.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GameActivity.USER_NAME_KEY, playerScore)
            startActivity(intent)
        }
    }

    companion object {
        const val PLAYER_SCORE_KEY = "score"
    }
}
