package com.ldelaiglesia.tempedia.presentation.temtem_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.presentation.ui.theme.Background
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary

@Composable
fun ImgToGif(
    temtem: TemtemDetail
) {
    var isGifVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (android.os.Build.VERSION.SDK_INT >= 28) {
                add(AnimatedImageDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val imageUrl = if (isGifVisible) {
        temtem.portraitGif
    } else {
        temtem.portrait
    }
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(Background)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            imageLoader = imageLoader,
            modifier = Modifier
                .scale(0.7f)
                .align(Alignment.Center)
        )
        Button(
            onClick = { isGifVisible = !isGifVisible },
            modifier = Modifier
                .alpha(if (isGifVisible) 1f else 0.5f)
                .align(Alignment.BottomStart)
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ColorPrimary)
        ) {
            Text(text = "GIF", color = Color.White)
        }
    }
}