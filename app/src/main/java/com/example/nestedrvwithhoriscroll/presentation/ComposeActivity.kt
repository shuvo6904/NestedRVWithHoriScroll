package com.example.nestedrvwithhoriscroll.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nestedrvwithhoriscroll.data.DataSource
import com.example.nestedrvwithhoriscroll.data.domain.Animal
import com.example.nestedrvwithhoriscroll.data.domain.AnimalSection
import com.example.nestedrvwithhoriscroll.ui.theme.NestedRVWithHoriScrollTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NestedRVWithHoriScrollTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var sections by remember { mutableStateOf(listOf<AnimalSection>()) }

    LaunchedEffect(Unit) {
        sections= DataSource.createSections(numberOfSections = 20, itemsPerSection = 10).toList()
    }

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)) {
        items(sections.size, key = { sections[it].id + it }) {ind->
            val aniSec = sections[ind]
            SectionItem(aniSec)
        }
    }
}

@Composable
private fun SectionItem(aniSec: AnimalSection) {
    Text(
        text = aniSec.title,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
        fontWeight = FontWeight.Bold
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(aniSec.animals.size, key = { aniSec.animals[it].hashCode() + it }) { ind ->
            val animal = aniSec.animals[ind]
            AnimalItem(animal)
        }
    }
}

@Composable
private fun AnimalItem(animal: Animal) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
            .clickable { }
            .padding(1.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        AsyncImage(
            model = animal.image,
            modifier = Modifier
                .height(150.dp)
                .aspectRatio(1f),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = animal.name,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(6.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NestedRVWithHoriScrollTheme {
        MainScreen()
    }
}