package com.example.onearmedbanditgame
/*
 Sites used for code
 https://semicolonspace.com/jetpack-compose-alignment-arrangement/



*/
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onearmedbanditgame.ui.theme.OneArmedBanditGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            OneArmedBanditGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    OneArmedBanditApp()
                }
            }
        }
    }
}


@Preview
@Composable
fun OneArmedBanditApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}
@Composable
inline fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
}
@Composable
inline fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
) {
}
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result1 by remember { mutableStateOf(1) }
    var result2 by remember { mutableStateOf(1) }
    var result3 by remember { mutableStateOf(1) }
    var wins by remember { mutableStateOf(0) }
    var loss by remember { mutableStateOf(0) }
    val imageResource = when (result1) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        else -> R.drawable.dice_4
    }
    val imageResource2 = when (result2) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        else -> R.drawable.dice_4
    }
    val imageResource3 = when (result3) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        else -> R.drawable.dice_4
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
     {
         if (result1 == result2 && result2 == result3) {

             Text(text =stringResource(R.string.win) , modifier = Modifier.align(Alignment.TopCenter))
             wins+1
         }
         else
         {

             Text(stringResource(R.string.loss) , modifier = Modifier.align(Alignment.TopCenter))
             loss++

         }
        Text(text ="loss Count: $loss", modifier = Modifier.align(Alignment.TopStart))
        Text(text ="win Count: $wins", modifier = Modifier.align(Alignment.TopEnd))

    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(imageResource), contentDescription = result1.toString(), modifier = Modifier
            .align(Alignment.CenterStart)
            .size(100.dp))
        Image(painter = painterResource(imageResource2), contentDescription = result2.toString(), modifier = Modifier
            .align(Alignment.Center)
            .size(100.dp))
        Image(painter = painterResource(imageResource3), contentDescription = result3.toString(), modifier = Modifier
            .align(Alignment.CenterEnd)
            .size(100.dp))

        Button(onClick =
        {
                result1 = (1..4).random()
                result2 = (1..4).random()
                result3 = (1..4).random()
        }, modifier = Modifier.align(Alignment.BottomCenter))

            {
                Text(text=stringResource(R.string.spin))
        }


    }
}

