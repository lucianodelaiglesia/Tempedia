package com.ldelaiglesia.tempedia.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil3.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.R

@Composable
fun PortraitImage(url: String?, modifier: Modifier){
    Box(
        modifier = modifier
    ) {
        Image(
            painter = rememberAsyncImagePainter( model = url ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.portrait_border_primary),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}