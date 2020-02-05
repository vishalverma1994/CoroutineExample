package com.coroutinessample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {


    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
        }
    }

    private suspend fun fakeApiRequest(){
        val result1 = getResult1FromApi()
        println("Debug : $result1")
        setTextONMainThread(result1)

        val result2 = getResult2FromApi(result1)
        println("Debug : $result2")
        setTextONMainThread(result2)
        logThread("fakeApiRequest")
    }

    private suspend fun setTextONMainThread(input: String){
        withContext(Main){
            setNewText(input)
            logThread("setTextONMainThread")
        }
    }

    private fun setNewText(input: String) {
        val newText = text.text.toString() + "\n$input"
        text.text = newText
    }

    private suspend fun getResult1FromApi(): String{
        logThread("getResult1FromApi")
        delay(1000)
//        Thread.sleep(1000) //This makes thread to sleep for 1 sec along with all the coroutine
        return RESULT_1
    }

    private suspend fun getResult2FromApi(result1: String): String{
        logThread("getResult2FromApi")
        delay(1000)
        return RESULT_2
    }

    private fun logThread(methodName: String){
        println("Debug : $methodName : ${Thread.currentThread().name}")
    }
}
