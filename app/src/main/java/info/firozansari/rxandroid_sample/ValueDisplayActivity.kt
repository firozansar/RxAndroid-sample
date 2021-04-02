package info.firozansari.rxandroid_sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

class ValueDisplayActivity : AppCompatActivity() {
    private var mValueDisplay: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
        createObservable()
    }

    @SuppressLint("CheckResult")
    private fun createObservable() {
        val list = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
        list.toObservable() // extension function for Iterables
            .filter { it.length >= 5 }
            .subscribeBy(  // named arguments for lambda Subscribers
                onNext = { mValueDisplay?.text = it },
                onError = { it.printStackTrace() },
                onComplete = { println("Done!") }
            )
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_value_display)
        mValueDisplay = findViewById<View>(R.id.value_display) as TextView
    }
}