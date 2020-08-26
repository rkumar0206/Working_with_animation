package com.example.workingwithanimation

import android.animation.*
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
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
        showerBtn.setOnClickListener(this)
        skyColorBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v?.id) {

            rotateBtn.id -> {

                rotater()
            }

            translateBtn.id -> {

                translater()
            }

            scaleBtn.id -> {

                scaler()
            }

            fadeBtn.id -> {

                fader()
            }

            skyColorBtn.id -> {

                colorizer()
            }

            showerBtn.id -> {

                shower()
            }

        }
    }

    private fun rotater() {

        val animator = ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f)
            .setDuration(1000)

        animator.disableViewDuringAnimation(rotateBtn)

        animator.start()
    }

    private fun translater() {

        val animator = ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f)
            .setDuration(1000)

        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE


        animator.disableViewDuringAnimation(translateBtn)

        animator.start()
    }

    private fun scaler() {

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)

        val animator = ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE

        animator.duration = 1000

        animator.disableViewDuringAnimation(scaleBtn)

        animator.start()
    }

    private fun fader() {

        val animator = ObjectAnimator.ofFloat(star, View.ALPHA, 0f)
            .setDuration(1000)

        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(fadeBtn)

        animator.start()
    }

    private fun colorizer() {

        val animator =
            ObjectAnimator.ofArgb(star.parent, "backgroundColor", Color.BLACK, Color.RED)
                .setDuration(600)

        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(skyColorBtn)
        animator.start()
    }

    private fun shower() {

        val container = star.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height

        var starW = star.width.toFloat()
        var starH = star.height.toFloat()

        val newStar = AppCompatImageView(this)
        newStar.setImageResource(R.drawable.ic_baseline_star_24)
        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        container.addView(newStar)

        newStar.scaleX = Math.random().toFloat() * 1.5f + 0.1f
        newStar.scaleY = newStar.scaleX
        starW *= newStar.scaleX
        starH *= newStar.scaleY

        newStar.translationX = Math.random().toFloat() * containerW - starW / 2

        val mover = ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y, -starH, containerH + starH)

        mover.interpolator = AccelerateInterpolator(1f)
        val rotator =
            ObjectAnimator.ofFloat(newStar, View.ROTATION, (Math.random() * 1080).toFloat())

        rotator.interpolator = LinearInterpolator()

        val set = AnimatorSet()
        set.playTogether(mover, rotator)
        set.duration = (Math.random() * 1500 + 500).toLong()

        set.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {

                container.removeView(newStar)
            }
        })
    }


    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {

        addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator?) {

                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {

                view.isEnabled = true
            }
        })

    }

}