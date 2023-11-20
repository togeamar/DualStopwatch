package com.example.dualstopwatch.vm

import androidx.compose.ui.graphics.Color

data class uistate(
    var seconds:String="00",
    var seconds2:String="00",
    var minutes:String="00",
    var minutes2:String="00",
    var hours:String="00",
    var hours2:String="00",
    var start:Boolean=true,
    var start2:Boolean=true,
    var pause:Boolean=false,
    var pause2:Boolean=false,
    var isplaying:Boolean=false,
    var isplaying2:Boolean=false,
    var reset:Boolean=false,
    var centcolor:Color= Color.White
)
