package com.ldelaiglesia.tempedia.presentation.temtem_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.R
import com.ldelaiglesia.tempedia.domain.models.Temtem
import com.ldelaiglesia.tempedia.presentation.ui.theme.ColorPrimary

@Composable
fun TemtemListItem(
    temtem: Temtem,
    onItemClick: (Temtem) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clickable { onItemClick(temtem) }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = ColorPrimary
            ),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = temtem.name,
                        fontSize = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .weight(0.8f)
                            .padding(start = 8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .height(64.dp)
                            .weight(0.2f)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = temtem.portrait),
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
            }
        }
    }
}