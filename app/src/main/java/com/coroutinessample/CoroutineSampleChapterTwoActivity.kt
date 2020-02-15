package com.coroutinessample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_coroutine_sample_chapter_two.*
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private class CoroutineSampleChapterTwoActivity : AppCompatActivity() {

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000 //ms
    private lateinit var job: CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_sample_chapter_two)

        job_button.setOnClickListener {
            if (!::job.isInitialized){
                initJob()
            }
        }
    }

    fun initJob(){
        job_button.text = "Start Job #1"
        job_complete_text.text = ""
        job = Job()
        job.invokeOnCompletion {
            it?.message.let {
                var message = it
                if (message.isNullOrEmpty()){
                    message = "Unknown cancellation"
                }
                println("$job was cancelled. Reason: $message")
                showToast(message)
            }
        }
    }

    private fun showToast(message: String) {
        GlobalScope.launch {

        }
    }

//    fun ProgressBar
}
