package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

val imageBackgroundColor = Color(0xFFC3ECD2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}


@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}

@Composable
fun LemonadeApp() {

    LemonadeScreen()
}

@Composable
fun LemonadeScreen(
) {
    var stage by remember {
        mutableStateOf(Stage.TREE)
    }

    val toClick = if (stage == Stage.LEMON) {
        (2..4).random()
    } else 0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        var clicked = 0
        Box(
            Modifier
                .background(imageBackgroundColor, shape = MaterialTheme.shapes.extraLarge)
                .clickable {
                    if (toClick > 0 && clicked < toClick) {
                        clicked++
                        if (clicked == toClick) {
                            stage = stage.nextStage()
                        }
                    } else stage = stage.nextStage()
                }) {
            Image(
                painter = painterResource(stage.imageResId),
                contentDescription = stringResource(stage.imageDescriptionResId),
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            stringResource(stage.instructionResId) + (if (toClick > 0) " $toClick" else ""),
            fontSize = 18.sp,
        )
    }
}
