package com.example.proyekakhircompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyekakhircompose.ui.theme.ProyekAkhirComposeTheme

@Composable
fun PlayerItem (
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier
    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlayerItemPreview() {
    ProyekAkhirComposeTheme {
        PlayerItem(
            "David Johan", "https://static.foxnews.com/foxnews.com/content/uploads/2022/06/Stephen-Curry10.jpg"
        )
    }
}