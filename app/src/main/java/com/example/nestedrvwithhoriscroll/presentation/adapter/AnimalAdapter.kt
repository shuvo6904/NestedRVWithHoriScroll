package com.example.nestedrvwithhoriscroll.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.nestedrvwithhoriscroll.R
import com.example.nestedrvwithhoriscroll.data.domain.Animal
import com.example.nestedrvwithhoriscroll.presentation.adapter.base.BaseAdapter

class AnimalAdapter(
    items: List<Animal> = emptyList(),
) : BaseAdapter<Animal>(
    R.layout.item_animal,
    items,
) {
    override fun bind(itemView: View, item: Animal, position: Int, viewHolder: BaseViewHolderImp) {
        itemView.run {
            findViewById<TextView>(R.id.titleTextView)?.text = item.name
            findViewById<ImageView>(R.id.imageView)?.load(item.image)
        }
    }
}
