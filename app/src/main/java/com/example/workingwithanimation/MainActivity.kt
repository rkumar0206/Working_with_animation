package com.example.workingwithanimation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
    }

    private fun initListeners() {

        rotateBtn.setOnClickListener(this)
        translateBtn.setOnClickListener(this)
        scaleBtn.setOnClickListener(this)
        fadeBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v?.id) {


        }
    }
}