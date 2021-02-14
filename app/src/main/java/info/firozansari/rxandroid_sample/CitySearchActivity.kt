package info.firozansari.rxandroid_sample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Subscription

class CitySearchActivity : AppCompatActivity() {
    private var mRestClient: RestClient? = null
    private var mSearchInput: EditText? = null
    private var mNoResultsIndicator: TextView? = null
    private var mSearchResults: RecyclerView? = null
    private var mSearchResultsAdapter: SimpleAdapter? = null
    private var mSearchResultsSubject: PublishSubject<String>? = null
    private val mTextWatchSubscription: Subscription? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRestClient = RestClient(this)
        configureLayout()
        createObservables()
        listenToSearchInput()
    }

    private fun createObservables() {
        mSearchResultsSubject = PublishSubject.create()
        //        mTextWatchSubscription = mSearchResultsSubject
//                .debounce(400, TimeUnit.MILLISECONDS)
//                .observeOn(Schedulers.io())
//                .map(new Function<String, Object>() {
//                    @Override
//                    public Object apply(String s) throws Exception {
//                        return mRestClient.searchForCity(s);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<List<String>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(List<String> cities) {
//                        handleSearchResults(cities);
//                    }
//
//                    @Override
//                    public void onError(Throwable error) {
//
//                    }
//                });
    }

    private fun handleSearchResults(cities: List<String>) {
        if (cities.isEmpty()) {
            showNoSearchResults()
        } else {
            showSearchResults(cities)
        }
    }

    private fun showNoSearchResults() {
        mNoResultsIndicator!!.visibility = View.VISIBLE
        mSearchResults!!.visibility = View.GONE
    }

    private fun showSearchResults(cities: List<String>) {
        mNoResultsIndicator!!.visibility = View.GONE
        mSearchResults!!.visibility = View.VISIBLE
        mSearchResultsAdapter!!.setStrings(cities)
    }

    private fun listenToSearchInput() {
        mSearchInput!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mSearchResultsSubject!!.onNext(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_city_search)
        mSearchInput = findViewById<View>(R.id.search_input) as EditText
        mNoResultsIndicator = findViewById<View>(R.id.no_results_indicator) as TextView
        mSearchResults = findViewById<View>(R.id.search_results) as RecyclerView
        mSearchResults!!.layoutManager = LinearLayoutManager(this)
        mSearchResultsAdapter = SimpleAdapter(this)
        mSearchResults!!.adapter = mSearchResultsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        /*if (mTextWatchSubscription != null && !mTextWatchSubscription.isUnsubscribed()) {
            mTextWatchSubscription.unsubscribe();
        }*/
    }
}