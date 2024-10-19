package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val firstArtwork = R.drawable.traveler
    val secondArtwork = R.drawable.sayu
    val thirdArtwork = R.drawable.gaming
    val fourthArtwork = R.drawable.barbara

    var title by remember {
        mutableStateOf(R.string.traveler)
    }

    var year by remember {
        mutableStateOf(R.string.traveler_year)
    }

    var currentArtwork by remember {
        mutableStateOf(firstArtwork)
    }

    var imageResource by remember {
        mutableStateOf(currentArtwork)
    }


    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkDisplay(currentArtwork = currentArtwork)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkTitle(title = title, year = year)
        Spacer(modifier = modifier.size(25.dp))
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFeba443)
                ),
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.sayu
                            year = R.string.sayu_year
                        }
                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.gaming
                            year = R.string.gaming_year
                        }
                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.barbara
                            year = R.string.barbara_year
                        }
                        else -> {
                            currentArtwork = thirdArtwork
                            title = R.string.traveler
                            year = R.string.traveler_year
                        }
                    }
                },


            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,

                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFeba443)
                ),
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.sayu
                            year = R.string.sayu_year
                        }
                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.gaming
                            year = R.string.gaming_year
                        }
                        thirdArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.barbara
                            year = R.string.barbara_year
                        }
                        else -> {
                            currentArtwork = firstArtwork
                            title = R.string.traveler
                            year = R.string.traveler_year
                        }
                    }
                },


            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Image(
        painter = painterResource(currentArtwork),
        contentDescription = stringResource(id = R.string.sayu),
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFeba443))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,

            fontSize = 32.sp
        )


        Text(
            text = "— ${stringResource(id = year)} —",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}