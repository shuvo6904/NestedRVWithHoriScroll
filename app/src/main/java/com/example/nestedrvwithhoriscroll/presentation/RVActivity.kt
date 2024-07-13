package com.example.nestedrvwithhoriscroll.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.util.fastForEachIndexed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nestedrvwithhoriscroll.R
import com.example.nestedrvwithhoriscroll.data.DataSource
import com.example.nestedrvwithhoriscroll.databinding.ActivityRvactivityBinding
import com.example.nestedrvwithhoriscroll.presentation.epoxy.sectionView

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

        binding.epoxyRecyclerView.withModels {
            val sections = DataSource.createSections(numberOfSections = 50, itemsPerSection = 25)
            sections.fastForEachIndexed { index, section ->
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