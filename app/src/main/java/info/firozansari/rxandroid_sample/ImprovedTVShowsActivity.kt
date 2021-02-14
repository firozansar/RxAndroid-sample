package info.firozansari.rxandroid_sample

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ImprovedTVShowsActivity : AppCompatActivity() {
    private var mTvShowListView: RecyclerView? = null
    private var mProgressBar: ProgressBar? = null
    private var mErrorMessage: TextView? = null
    private var mSimpleAdapter: SimpleAdapter? = null
    private var mRestClient: RestClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRestClient = RestClient(this)
        configureLayout()
        createSingle()
    }

    private fun createSingle() {
        val tvShowSingle = Single.fromCallable {
            /**
             * Uncomment me (and comment out the line below) to see what happens when an error occurs.
             *
             * return RestClient.getFavoriteTvShowsWithException();
             */
            /**
             * Uncomment me (and comment out the line below) to see what happens when an error occurs.
             *
             * return RestClient.getFavoriteTvShowsWithException();
             */
            mRestClient!!.favoriteTvShows
        }
        tvShowSingle
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<String>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(tvShows: List<String>) {
                    displayTvShows(tvShows)
                }

                override fun onError(error: Throwable) {
                    displayErrorMessage()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun displayTvShows(tvShows: List<String>) {
        mSimpleAdapter!!.setStrings(tvShows)
        mProgressBar!!.visibility = View.GONE
        mTvShowListView!!.visibility = View.VISIBLE
    }

    private fun displayErrorMessage() {
        mProgressBar!!.visibility = View.GONE
        mErrorMessage!!.visibility = View.VISIBLE
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_improv_tvshow)
        mErrorMessage = findViewById<View>(R.id.error_message) as TextView
        mProgressBar = findViewById<View>(R.id.loader) as ProgressBar
        mTvShowListView = findViewById<View>(R.id.tv_show_list) as RecyclerView
        mTvShowListView!!.layoutManager = LinearLayoutManager(this)
        mSimpleAdapter = SimpleAdapter(this)
        mTvShowListView!!.adapter = mSimpleAdapter
    }
}