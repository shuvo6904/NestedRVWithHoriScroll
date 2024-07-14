package com.example.nestedrvwithhoriscroll.presentation.epoxy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.paris.annotations.Style
import com.airbnb.paris.annotations.Styleable
import com.airbnb.paris.extensions.sliderViewStyle
import com.airbnb.paris.extensions.layoutHeight
import com.airbnb.paris.extensions.layoutWidth
import com.example.nestedrvwithhoriscroll.data.domain.AnimalSection
import com.example.nestedrvwithhoriscroll.databinding.ItemSliderBinding
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@Styleable
@ModelView
class SliderView (context: Context): FrameLayout(context) {

    private val binding = ItemSliderBinding.inflate(LayoutInflater.from(context), this, true)

    @ModelProp
    lateinit var sliderItems: List<CarouselItem>

    @AfterPropsSet
    fun useProps() {
        binding.apply {
            carousel.apply {
                autoPlay = true
                autoPlayDelay = 4000
                imageScaleType = ImageView.ScaleType.FIT_XY
                infiniteCarousel = true
                showBottomShadow = false
                showCaption = false
                showIndicator = false
                showNavigationButtons = false
                showTopShadow = false
            }
            carousel.carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    Toast.makeText(context, "hiiiiiiiiii", Toast.LENGTH_SHORT).show()
                }
            }

//            binding.carousel.registerLifecycle(fragment.lifecycle)
            carousel.setIndicator(binding.customIndicator)
            customIndicator.visibility = View.VISIBLE
            carousel.setData(sliderItems)
        }
    }

    companion object {
        @Style(isDefault = true)
        val sliderStyle: com.airbnb.paris.styles.Style = sliderViewStyle {
            layoutWidth(ViewGroup.LayoutParams.MATCH_PARENT)
            layoutHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

}