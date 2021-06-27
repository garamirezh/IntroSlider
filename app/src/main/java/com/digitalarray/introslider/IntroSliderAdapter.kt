package com.digitalarray.introslider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class IntroSliderAdapter(private val introSlides: List<IntroSlider>) :
    RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textTitle =
            view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.textTitle)
        private val textDescription =
            view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.textDescription)
        private val imageIcon =
            view.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imageSlideIcon)

        fun bind(introSlider: IntroSlider) {
            textTitle.text = introSlider.title
            textDescription.text = introSlider.description
            imageIcon.setImageResource(introSlider.icon)
        }
    }
}