package com.example.dualstopwatch.ui.theme

import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dualstopwatch.R
import com.example.dualstopwatch.vm.viewmodel



@Composable
fun Screen(
    viewmodel: viewmodel = viewModel()
){
    val uistate by viewmodel.uistate.collectAsState()
    KeepScreenOn()
    val config = LocalConfiguration.current
    val portmode= remember { mutableStateOf(config.orientation) }
    var centcolr=animateColorAsState(uistate.centcolor, animationSpec = tween(2000, easing = FastOutLinearInEasing )).value

    if (portmode.value == Configuration.ORIENTATION_PORTRAIT){
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxWidth()
                    .height(403.dp)
            ) {
                Text(text = "${uistate.hours}:${uistate.minutes}", fontSize = 70.sp)
                Text(text = uistate.seconds, fontSize = 80.sp)
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { viewmodel.pause() }, enabled = uistate.pause,modifier = Modifier
                        .padding(10.dp)
                        .height(40.dp)
                        .width(90.dp),
                        shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 35.dp,
                            pressedElevation = 25.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(6, 150, 212, 255)
                        )
                    ) {
                        Text(text = "pause")
                    }
                }

            }
            Row(horizontalArrangement = Arrangement.Center,modifier = Modifier
                .fillMaxWidth()
                .background(centcolr)) {
                Button(onClick = { viewmodel.start1() }, enabled = uistate.start, modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 35.dp,
                        pressedElevation = 25.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0, 34, 255, 255)
                    )) {
                    Text(text = "Start")
                }

                Button(onClick = { viewmodel.reset() }, enabled = uistate.reset,
                    shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 35.dp,
                        pressedElevation = 25.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0, 0, 0, 255)
                    )) {
                    Text(text = "Reset")
                }
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxSize()
            ) {
                Text(text = "${uistate.hours2}:${uistate.minutes2}", fontSize = 70.sp)
                Text(text = uistate.seconds2, fontSize = 80.sp)
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { viewmodel.pause2() }, enabled = uistate.pause2,modifier = Modifier.padding(10.dp),
                        shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 35.dp,
                            pressedElevation = 25.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(94, 5, 119, 255)
                        )) {
                        Text(text = "pause")
                    }
                }

            }

        }

    }
    else{
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxHeight()
                    .width(360.dp)
            ) {
                Text(text = "${uistate.hours}:${uistate.minutes}", fontSize = 70.sp)
                Text(text = uistate.seconds, fontSize = 80.sp)
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { viewmodel.pause() }, enabled = uistate.pause,modifier = Modifier.padding(10.dp),
                        shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 35.dp,
                            pressedElevation = 25.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(6, 150, 212, 255)
                        )) {
                        Text(text = "pause")
                    }
                }

            }
            Column(verticalArrangement = Arrangement.Center,modifier = Modifier
                .fillMaxHeight()
                .background(centcolr))  {
                Button(onClick = { viewmodel.start1() }, enabled = uistate.start, modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 35.dp,
                        pressedElevation = 25.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0, 64, 255, 255)
                    )) {
                    Text(text = "Start")
                }

                Button(onClick = { viewmodel.reset() }, enabled = uistate.reset,
                    shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 35.dp,
                        pressedElevation = 25.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0, 0, 0, 255)
                    )) {
                    Text(text = "Reset")
                }
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxSize()
            ) {
                Text(text = "${uistate.hours2}:${uistate.minutes2}", fontSize = 70.sp)
                Text(text = uistate.seconds2, fontSize = 80.sp)
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { viewmodel.pause2() }, enabled = uistate.pause2,modifier = Modifier.padding(10.dp),
                        shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 20.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 35.dp,
                            pressedElevation = 25.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(95, 7, 115, 255)
                        )) {
                        Text(text = "pause")
                    }
                }

            }

        }

    }


}
@Composable
fun KeepScreenOn() {
    val currentView = LocalView.current
    DisposableEffect(Unit) {
        currentView.keepScreenOn = true
        onDispose {
            currentView.keepScreenOn = false
        }
    }
}