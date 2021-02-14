package info.firozansari.rxandroid_sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject

class ButtonCounterActivity : AppCompatActivity() {
    private var mCounterDisplay: TextView? = null
    private var mIncrementButton: Button? = null
    private var mCounterEmitter: PublishSubject<Int>? = null
    private var mCounter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
        createCounterEmitter()
    }

    @SuppressLint("CheckResult")
    private fun createCounterEmitter() {
        mCounterEmitter = PublishSubject.create()
        mCounterEmitter?.subscribeBy(  // named arguments for lambda Subscribers
            onNext = { mCounterDisplay?.text = it.toString() },
            onError =  { it.printStackTrace() },
            onComplete = { println("Done!") }
        )
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_button_counter)
        configureCounterDisplay()
        configureIncrementButton()
    }

    private fun configureCounterDisplay() {
        mCounterDisplay = findViewById<View>(R.id.counter_display) as TextView
        mCounterDisplay?.text = mCounter.toString()
    }

    private fun configureIncrementButton() {
        mIncrementButton = findViewById<View>(R.id.increment_button) as Button
        mIncrementButton?.setOnClickListener { onIncrementButtonClick() }
    }

    private fun onIncrementButtonClick() {
        mCounter++
        mCounterEmitter?.onNext(mCounter)
    }
}