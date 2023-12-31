package com.example.onearmedbanditgame
/*
 Sites used for code
 https://semicolonspace.com/jetpack-compose-alignment-arrangement/



*/
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
        Log.d("tag","onCreate Start")
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
    )
}

@Composable
fun DiceWithButtonAndImage() {
    var result1 by rememberSaveable  { mutableStateOf(1) }
    var result2 by rememberSaveable  { mutableStateOf(1) }
    var result3 by rememberSaveable  { mutableStateOf(1) }
    var result4 by rememberSaveable  { mutableStateOf(1) }
    var wins by rememberSaveable  { mutableStateOf(0) }
    var loss by rememberSaveable  { mutableStateOf(0) }
    var spins by rememberSaveable  { mutableStateOf(0) }
    var percentage by rememberSaveable  { mutableStateOf(0) }

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
    val imageResource4 = when (result4) {
        1 -> R.drawable.winner
        else -> R.drawable.loss
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {

        if (result1 == result2 && result2 == result3) {
            Text(
                text = stringResource(R.string.win),
                modifier = Modifier.align(Alignment.TopCenter)
            )
        } else {
            Text(stringResource(R.string.loss), modifier = Modifier.align(Alignment.TopCenter))
        }


        Text(text = "loss Count: $loss", modifier = Modifier.align(Alignment.TopStart))
        Text(text = "win Count: $wins", modifier = Modifier.align(Alignment.TopEnd))
        Text(text = "spin Count: $spins", modifier = Modifier.align(Alignment.BottomStart))

        Text(text = "win spin ratio: $percentage%", modifier = Modifier.align(Alignment.Center).padding(0.dp,100.dp,0.dp, 0.dp))

    }
    Box(modifier = Modifier.fillMaxSize()) {
        Log.d("tag","create imageResource")
        Image(
            painter = painterResource(imageResource),
            contentDescription = result1.toString(),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(100.dp)
        )
        Log.d("tag","create imageResource2")
        Image(
            painter = painterResource(imageResource2),
            contentDescription = result2.toString(),
            modifier = Modifier
                .align(Alignment.Center)
                .size(100.dp)
        )
        Log.d("tag","create imageResource3")
        Image(
            painter = painterResource(imageResource3),
            contentDescription = result3.toString(),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(10.dp)
                .size(100.dp)
        )
        Log.d("tag","create imageResource4")
        Image(
            painter = painterResource(imageResource4),
            contentDescription = result3.toString(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(40.dp)
                .size(100.dp)
        )

        Button(onClick =
        {
            Log.d("tag","spin++")
            spins++
            Log.d("tag","result1: 1..4")
            result1 = (1..4).random()
            Log.d("tag","result2: 1..4")
            result2 = (1..4).random()
            Log.d("tag","result3: 1..4")
            result3 = (1..4).random()
            Log.d("tag","result4: 1..4")
            result4 = if (result1 == result2 && result2 == result3) {
                Log.d("tag","wins: add 1")
                wins++
                1
            } else {
                Log.d("tag","loss: add 1")
                loss++
                2
            }
            Log.d("tag","percentage: wins/spins *100")
            percentage = wins/spins *100
        }, modifier = Modifier.align(Alignment.BottomCenter)
        )

        {
            Text(text = stringResource(R.string.spin))
        }

        Button(onClick =
        {Log.d("tag","set spin win loss percentage to 0")
                spins=0
                wins=0
                loss=0
                percentage=0

        }, modifier = Modifier.align(Alignment.BottomEnd)
        )
        {
            Text(text = stringResource(R.string.reset))
        }
    }
}

