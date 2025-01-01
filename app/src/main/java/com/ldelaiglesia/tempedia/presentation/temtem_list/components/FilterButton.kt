package com.ldelaiglesia.tempedia.presentation.temtem_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.R
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary

@Composable
fun FilterButton() {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = ColorPrimary
            )
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.tempedia),
            contentDescription = null,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .size(48.dp)
        )
    }
}

@Preview
@Composable
fun FilterButtonPreview() {
    FilterButton()
}