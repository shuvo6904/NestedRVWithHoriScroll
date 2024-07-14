package com.example.nestedrvwithhoriscroll.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclersample.utils.enforceSingleScrollDirection
import com.example.nestedrvwithhoriscroll.data.DataSource
import com.example.nestedrvwithhoriscroll.data.domain.AnimalSection
import com.example.nestedrvwithhoriscroll.databinding.ActivityMainBinding
import com.example.nestedrvwithhoriscroll.presentation.adapter.AnimalSectionAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sectionAdapter: AnimalSectionAdapter
    private var sections = MutableStateFlow<List<AnimalSection>?>(null)

    companion object {
        const val sectionsKey = "sectionsKey"
    }

    private val moshi =
        Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()

    private val animalSectionJsonAdapter: JsonAdapter<List<AnimalSection>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, AnimalSection::class.java))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews(savedInstanceState)
    }

    private fun initViews(savedInstanceState: Bundle?) {
        //restore state if possible
        //IRL this would most likely be persisted inside a viewModel and you wouldn't need to worry about it
        val savedSections = savedInstanceState?.getString(sectionsKey)
        if (sections.value == null && savedSections != null) {
            sections.value = animalSectionJsonAdapter.fromJson(savedSections)
        }

        if (sections.value == null) {
            //create a populated list of sections
            //IRL you'd most likely be getting the data from a server on a background thread inside a viewModel
            lifecycleScope.launch {
                sections.value = DataSource.createSections(numberOfSections = 20, itemsPerSection = 10)
            }}
        //create an instance of ConcatAdapter
        val concatAdapter = ConcatAdapter()

        //create AnimalSectionAdapter for the sections and add to ConcatAdapter
        lifecycleScope.launch {
            sections.collect { sections ->
                sectionAdapter = AnimalSectionAdapter(sections ?: mutableListOf())
                concatAdapter.addAdapter(sectionAdapter)

                //setup the recycler
                val linearLayoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                binding.recyclerView.run {
                    layoutManager = linearLayoutManager
                    adapter = concatAdapter
                    enforceSingleScrollDirection()
                }
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(sectionsKey, animalSectionJsonAdapter.toJson(sections.value))
        super.onSaveInstanceState(outState)
    }
}