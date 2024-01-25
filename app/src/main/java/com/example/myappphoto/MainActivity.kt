package com.example.myappphoto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myappphoto.ui.theme.MyAppPhotoTheme
import androidx.compose.material3.CenterAlignedTopAppBar as CenterAlignedTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppPhotoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryApp() {
    val photos = listOf(
        PhotoData(R.string.title_photo1, R.string.description_photo1, R.drawable.pexels_david_bartus_1166209),
        PhotoData(R.string.tilte_photo2, R.string.description_photo2, R.drawable.pexels_tobi_463734),
        PhotoData(R.string.title_photo3, R.string.description_photo3, R.drawable.pexels_eberhard_grossgasteiger_1366913),
        PhotoData(R.string.title_photo4, R.string.description_photo4, R.drawable.pexels_fabiotsu_1756325)
    )

    var currentPhotoIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "My Galerie Photo",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            PhotoGallery(
                title = photos[currentPhotoIndex].title,
                descriptionPhoto = photos[currentPhotoIndex].descriptionPhoto,
                imagePainter = photos[currentPhotoIndex].imagePainter,
                modifier = Modifier.weight(1f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .wrapContentHeight(align = Alignment.Bottom)
            ) {
                ButtonGallery(
                    OnImageclick = {
                        if (currentPhotoIndex > 0) {
                            currentPhotoIndex--
                        }
                    },
                    title = R.string.previous,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                ButtonGallery(
                    OnImageclick = {
                        if (currentPhotoIndex < photos.size - 1) {
                            currentPhotoIndex++
                        }
                    },
                    title = R.string.next,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


data class PhotoData(val title: Int, val descriptionPhoto: Int, val imagePainter: Int)

@Composable
private fun PhotoGallery(
    title: Int,
    descriptionPhoto: Int,
    imagePainter: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        //modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(imagePainter),
            contentDescription = stringResource(descriptionPhoto)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(title))
    }

}


@Composable
private fun ButtonGallery(
    OnImageclick: () -> Unit,
    title: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Button(onClick = OnImageclick, modifier = Modifier.weight(1f)) {
            Text(stringResource(title))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppPhotoTheme {
        GalleryApp()
    }
}