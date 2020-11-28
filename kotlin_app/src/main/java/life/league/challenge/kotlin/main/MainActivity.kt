package life.league.challenge.kotlin.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import life.league.challenge.kotlin.R

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
