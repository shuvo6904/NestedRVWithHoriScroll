package com.example.nestedrvwithhoriscroll.presentation

import com.airbnb.epoxy.EpoxyController

class AnimalEpoxyController  : EpoxyController() {
    var items: List<Pair<String, String>> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }
    override fun buildModels() {
        items.forEach { (title, subtitle) ->
            headerView{

            }
            customItem {
                id(title)
                title(title)
                subtitle(subtitle)
            }
        }
    }
}