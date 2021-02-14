package info.firozansari.rxandroid_sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class ColorListActivity : AppCompatActivity() {
    var mColorListView: RecyclerView? = null
    var mSimpleAdapter: SimpleAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
        createObservable()
    }

    @SuppressLint("CheckResult")
    private fun createObservable() {
        val listObservable = Observable.just(colorList)
        listObservable.observeOn(Schedulers.newThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeBy(  // named arguments for lambda Subscribers
                onNext = { mSimpleAdapter?.setStrings(it) },
                onError =  { it.printStackTrace() },
                onComplete = { println("Done!") }
            )
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_color_list)
        mColorListView = findViewById<View>(R.id.color_list) as RecyclerView
        mColorListView?.layoutManager = LinearLayoutManager(this)
        mSimpleAdapter = SimpleAdapter(this)
        mColorListView?.adapter = mSimpleAdapter
    }

    companion object {
        private val colorList: List<String>
            get() {
                val colors = ArrayList<String>()
                colors.add("blue")
                colors.add("green")
                colors.add("red")
                colors.add("chartreuse")
                colors.add("Van Dyke Brown")
                return colors
            }
    }
}