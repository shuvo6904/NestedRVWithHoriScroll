package com.example.nestedrvwithhoriscroll.presentation.epoxy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import coil.load
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.airbnb.paris.annotations.Style
import com.airbnb.paris.annotations.Styleable
import com.airbnb.paris.extensions.animalViewStyle
import com.example.nestedrvwithhoriscroll.databinding.ItemAnimalBinding
import com.airbnb.paris.extensions.layoutHeight
import com.airbnb.paris.extensions.layoutWidth

@Styleable
@ModelView
class AnimalView (context: Context): FrameLayout(context) {

    private val binding = ItemAnimalBinding.inflate(LayoutInflater.from(context), this, true)

    @TextProp
    lateinit var title: CharSequence

    @TextProp
    lateinit var image: CharSequence

    @AfterPropsSet
    fun useProps() {
        binding.apply {
            titleTextView.text = title
            imageView.load(image)
        }
    }

    companion object {
        @Style(isDefault = true)
        val animalStyle: com.airbnb.paris.styles.Style = animalViewStyle {
            layoutWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

}