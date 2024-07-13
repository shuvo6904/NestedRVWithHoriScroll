package com.example.nestedrvwithhoriscroll.presentation

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R2.layout.item_animal)
abstract class AnimalItemModel: EpoxyModelWithHolder<AnimalItemModel.ItemHolder>()  {
    @EpoxyAttribute
    lateinit var title: String
    @EpoxyAttribute
    lateinit var image: String
    override fun bind(holder: ItemHolder) {
        holder.animalViewItem.title = title
        holder.animalViewItem.image = image
    }
    class ItemHolder : EpoxyHolder() {
        lateinit var animalViewItem: AnimalViewItem
        override fun bindView(itemView: View) {
            animalViewItem = itemView as AnimalViewItem
        }
    }
}