package com.example.workingwithanimation

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animation_example1.*

class AnimationExample1 : AppCompatActivity() {

    val PASS_STRING = "Password".toCharArray()
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_example1)

        passwordTV.visibility = View.VISIBLE
        passwordTV.setTextColor(Color.RED)

        object : CountDownTimer(50 * PASS_STRING.size.toLong(), 50) {

            override fun onTick(p0: Long) {

                passwordTV.text = PASS_STRING[i].toString()
                i++
            }

            override fun onFinish() {

                //start another animation
            }
        }.start()

    }
}