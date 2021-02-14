package info.firozansari.rxandroid_sample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TVShowsActivity : AppCompatActivity() {
    private val mTvShowSubscription: Disposable? = null
    private var mTvShowListView: RecyclerView? = null
    private var mProgressBar: ProgressBar? = null
    private var mSimpleAdapter: SimpleAdapter? = null
    private var mRestClient: RestClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRestClient = RestClient(this)
        configureLayout()
        createObservable()
    }

    private fun createObservable() {
        val tvShowObservable = Observable.fromCallable { mRestClient!!.favoriteTvShows }
        tvShowObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<List<String>> {
                    override fun onError(e: Throwable) {}
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(tvShows: List<String>) {
                        displayTvShows(tvShows)
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mTvShowSubscription != null && !mTvShowSubscription.isDisposed) {
            mTvShowSubscription.dispose()
        }
    }

    private fun displayTvShows(tvShows: List<String>) {
        mSimpleAdapter!!.setStrings(tvShows)
        mProgressBar!!.visibility = View.GONE
        mTvShowListView!!.visibility = View.VISIBLE
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_tvshow)
        mProgressBar = findViewById<View>(R.id.loader) as ProgressBar
        mTvShowListView = findViewById<View>(R.id.tv_show_list) as RecyclerView
        mTvShowListView!!.layoutManager = LinearLayoutManager(this)
        mSimpleAdapter = SimpleAdapter(this)
        mTvShowListView!!.adapter = mSimpleAdapter
    }
}