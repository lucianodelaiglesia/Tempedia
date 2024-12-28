package com.ldelaiglesia.tempedia.presentation.temtem_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ldelaiglesia.tempedia.R
import com.ldelaiglesia.tempedia.domain.models.Temtem
import com.ldelaiglesia.tempedia.presentation.ui.theme.Background

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
                containerColor = Background
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
                        modifier = Modifier.weight(0.8f)
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
                            painter = painterResource(id = R.drawable.portrait_border),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .clickable { /*onItemClick(temtem)*/ }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "temtem.name",
                        modifier = Modifier
                            .wrapContentSize(),
                        //.align(alignment = Alignment.CenterVertically),
                        color = Color.White,
                        fontSize = 28.sp,
                        //fontFamily = FontFamily(Font(R.font.rubik_regular))
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.portrait_border),
                            contentDescription = null,
                            //modifier = Modifier.height(64.dp)
                        )
                        Image(
                            painter = rememberAsyncImagePainter(model = "temtem.portrait"),
                            contentDescription = null,
                            //modifier = Modifier.height(64.dp)
                        )
                    }
                }
            }
        }
    }
}
 */