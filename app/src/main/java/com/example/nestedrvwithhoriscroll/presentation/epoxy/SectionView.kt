package com.example.nestedrvwithhoriscroll.presentation.epoxy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.paris.annotations.Style
import com.airbnb.paris.annotations.Styleable
import com.airbnb.paris.extensions.layoutHeight
import com.airbnb.paris.extensions.layoutWidth
import com.airbnb.paris.extensions.sectionViewStyle
import com.example.nestedrvwithhoriscroll.data.domain.AnimalSection
import com.example.nestedrvwithhoriscroll.databinding.ItemAnimalSectionBinding

@Styleable
@ModelView
class SectionView(context: Context): FrameLayout(context) {

    private val binding = ItemAnimalSectionBinding.inflate(LayoutInflater.from(context), this, true)

    @ModelProp
    lateinit var animalSection: AnimalSection

    @AfterPropsSet
    fun useProps() {
        binding.apply {
            sectionTitle.text = animalSection.title
            sectionRV.withModels{
                animalSection.animals.forEachIndexed { index, animal ->
                    animalView{
                        id(index)
                        title(animal.name)
                        image(animal.image)
                    }
                }
            }
        }
    }

    companion object {
        @Style(isDefault = true)
        val sectionStyle: com.airbnb.paris.styles.Style = sectionViewStyle {
            layoutWidth(ViewGroup.LayoutParams.MATCH_PARENT)
            layoutHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

}