package com.example.nestedrvwithhoriscroll.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.util.fastForEachIndexed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.nestedrvwithhoriscroll.R
import com.example.nestedrvwithhoriscroll.data.DataSource
import com.example.nestedrvwithhoriscroll.databinding.ActivityRvactivityBinding
import com.example.nestedrvwithhoriscroll.presentation.epoxy.sectionView
import com.example.nestedrvwithhoriscroll.presentation.epoxy.sliderView
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class RVActivity : AppCompatActivity() {
        private lateinit var binding: ActivityRvactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvactivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycleScope.launch{
            val sections = DataSource.createSections(numberOfSections = 20, itemsPerSection = 10)
            binding.epoxyRecyclerView.withModels {

                sliderView{
                    id(69)
                    sliderItems(sections.first().animals.map { CarouselItem(imageUrl= it.image) })
                }

//            animalView{
//                id(0)
//                title("jngfdjkngf")
//                image(sections.last().animals.first().image)
//            }

                sections.forEachIndexed { index, section ->
                    sectionView{
                        id(index)
                        animalSection(section)
                    }
                }
//            sections.first().animals.fastForEachIndexed { animalIndex, animal ->
//                animalView{
//                    id(animalIndex)
//                    title(animal.name)
//                    image(animal.image)
//                }
//            }
            }

        }

    }
}