package ch.flaewel.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.flaewel.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var lemonadeStage by remember { mutableStateOf(0) }
    var squeezeAmount by remember { mutableStateOf(0) }
    var timesSqueezed by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(28.dp)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            color = Color.Yellow,
        ) {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
        Spacer(
            modifier = Modifier.height(240.dp)
        )
        // Button in the middle
        Box(
//            modifier = Modifier.align(Alignment.Horizontal)
        ) {
            Surface() {
                Button(
                    onClick = {
                        if (lemonadeStage == 0) {
                            squeezeAmount = (2..4).random()
                            timesSqueezed = 0
                            lemonadeStage ++
                        } else if (lemonadeStage == 1) {
                            timesSqueezed ++

                            if (timesSqueezed >= squeezeAmount) {
                                lemonadeStage ++
                            }
                        } else if (lemonadeStage == 2) {
                            lemonadeStage ++
                        } else {
                            lemonadeStage = 0
                        }
                    },
                    shape = RoundedCornerShape(
                        topStartPercent = 25,
                        topEndPercent = 25,
                        bottomEndPercent = 25,
                        bottomStartPercent = 25
                    ),
                    colors = ButtonColors(
                        contentColor = Color.Black,
                        containerColor = Color.LightGray,
                        disabledContainerColor = Color.Red,
                        disabledContentColor = Color.Red)
                ) {
                    Image(
                        painter = painterResource(
                            when (lemonadeStage) {
                                0 -> R.drawable.lemon_tree
                                1 -> R.drawable.lemon_squeeze
                                2 -> R.drawable.lemon_drink
                                else -> R.drawable.lemon_restart
                            }
                        ),
                        contentDescription = stringResource(
                            when(lemonadeStage) {
                            0 -> R.string.lemon_tree_desscription
                            1 -> R.string.lemon_description
                            2 -> R.string.lemonade_description
                            else -> R.string.emtpy_glass_description
                            }
                        )
                    )
                }
            }
        }
        Text(
            text = stringResource(
                when (lemonadeStage) {
                    0 -> R.string.lemon_tree_text
                    1 -> R.string.lemon_text
                    2 -> R.string.lemonade_text
                    else -> R.string.emtpy_glass_description
                }
            ),
            modifier = Modifier.padding(28.dp),
        )
    }
}

@Preview(showSystemUi = true,
    showBackground = true)
@Composable
fun LemonadeApp() {
    Lemonade()
}


