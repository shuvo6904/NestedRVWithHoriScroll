package com.example.nestedrvwithhoriscroll.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import coil.load
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.nestedrvwithhoriscroll.databinding.ItemAnimalBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AnimalViewItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val binding = ItemAnimalBinding.bind(this)

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


}