package com.ryvk.jetpackcompose

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ryvk.jetpackcompose.ui.theme.JetpackComposeTheme

class UserInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                Ui()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Ui() {

    val context = LocalContext.current

    Scaffold (content = { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val cameraPainter = painterResource(R.drawable.ic_camera)
            val dogBitmap = ImageBitmap.imageResource(R.drawable.dog)


            ConstraintLayout (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (button, image) = createRefs()

                Canvas(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .constrainAs(image) {
                            top.linkTo(parent.top, margin = 24.dp)
                            centerHorizontallyTo(parent)
                        }
                ) {
                    drawImage(
                        image = dogBitmap,
                        dstSize = IntSize(size.width.toInt(), size.height.toInt())
                    )
                }

                IconButton (
                    onClick = {
                    Toast.makeText(context,"Changing image", Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier
                        .constrainAs(button){
                            bottom.linkTo(image.bottom)
                            end.linkTo(image.end)
                        }
                ) {
                    Image(
                        painter = cameraPainter,
                        contentDescription = "Image picker",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)

                    )
                }

            }
        }
    })
}