package com.example.dualstopwatch.vm

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Timer
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class viewmodel:ViewModel() {
    private val _uistate = MutableStateFlow(uistate())
    val uistate: StateFlow<uistate> =_uistate.asStateFlow()

    private var time:Duration=Duration.ZERO
    private var time2:Duration=Duration.ZERO
    private lateinit var timer: Timer
    private lateinit var timer2:Timer

    fun start1(){
        timer= fixedRateTimer(initialDelay = 1000L, period = 1000L){
            time=time.plus(1.toDuration(DurationUnit.SECONDS))
            time.toComponents{hours, minutes, seconds,_ ->
                _uistate.update {time ->
                    time.copy(
                        seconds = seconds.pad(),
                        minutes = minutes.pad(),
                        hours = hours.toInt().pad()
                    )
                }
            }
            centcolor()
        }
        _uistate.update { playuistate ->
            playuistate.copy(
                isplaying = true,
                start = false,
                reset = true,
                pause = true)
        }
    }
    fun start2(){
        timer2= fixedRateTimer(initialDelay = 1000L, period = 1000L){
            time2=time2.plus(1.toDuration(DurationUnit.SECONDS))
            time2.toComponents{hours, minutes, seconds,_ ->
                _uistate.update {time ->
                    time.copy(
                        seconds2 = seconds.pad(),
                        minutes2 = minutes.pad(),
                        hours2 = hours.toInt().pad()
                    )
                }
            }
            centcolor()
        }
        _uistate.update { playuistate ->
            playuistate.copy(
                isplaying2 = true,
                start2 = false,
                reset = true)
        }

    }
    private fun Int.pad():String{
        return this.toString().padStart(2,'0')
    }

    private fun centcolor(){
        if (time>time2){
            _uistate.update { state ->
                state.copy(
                    centcolor = Color.Green
                )
            }
        }
        else if(time<time2){
            _uistate.update { state ->
                state.copy(
                    centcolor = Color.Red
                )
            }
        }
        else{
            _uistate.update { state ->
                state.copy(
                    centcolor = Color.White
                )
            }
        }
    }

    fun pause(){
        timer.cancel()
        start2()
        _uistate.update { state ->
            state.copy(
                pause = false,
                pause2 = true
            )
        }
    }
    fun pause2(){
        timer2.cancel()
        start1()
        _uistate.update { state ->
            state.copy(
                pause2= false,
                pause = true
            )
        }
    }

    fun reset(){
        timer.cancel()
        timer2.cancel()
        time= Duration.ZERO
        time2= Duration.ZERO
        _uistate.update { state ->
            state.copy(
                seconds = "00",
                seconds2 = "00",
                minutes = "00",
                minutes2 = "00",
                hours = "00",
                hours2 = "00",
                isplaying = false,
                isplaying2 = false,
                start = true,
                start2 = true,
                pause = false,
                pause2 = false,
                centcolor = Color.White,
                reset= false
            )
        }
    }


}