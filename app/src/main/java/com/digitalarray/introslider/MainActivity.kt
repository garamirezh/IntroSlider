package com.digitalarray.introslider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.digitalarray.introslider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlider(
                "Sunlight",
                "Sunlight is the light and energy that comes from the Sun.",
                R.drawable.images1
            ),
            IntroSlider(
                "Pay Online",
                "Electronic bill payment is a feature of online, mobile and telephone banking.",
                R.drawable.images2
            ),
            IntroSlider(
                "Video Streaming",
                "Streaming media is multimedia that is constantly received by and presented to an end-user.",
                R.drawable.images3
            )
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.introSliderViewPager.adapter = introSliderAdapter
        setupIndicator()
        setCurrentIndicator(0)
        binding.introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        binding.buttonNext.setOnClickListener {
            if (binding.introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                binding.introSliderViewPager.currentItem += 1
            } else {
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        binding.textSkipIntro.setOnClickListener {
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicator() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}



