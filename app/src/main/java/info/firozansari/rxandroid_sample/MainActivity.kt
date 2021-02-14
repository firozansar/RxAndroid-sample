package info.firozansari.rxandroid_sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.rxandroid_sample.ColorListActivity
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        setupExampleList()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.setTitle(R.string.example_list_title)
    }

    private fun setupExampleList() {
        val exampleList = findViewById<View>(R.id.example_list) as RecyclerView
        exampleList.setHasFixedSize(true)
        exampleList.layoutManager = LinearLayoutManager(this)
        exampleList.adapter = MainAdapter(this, examples)
    }

    companion object {
        private val examples: List<ActivityModel>
            private get() {
                val activityModels: MutableList<ActivityModel> = ArrayList()
                activityModels.add(
                    ActivityModel(
                        ColorListActivity::class.java,
                        "Example 1: Simple Color List"
                    )
                )
                activityModels.add(
                    ActivityModel(
                        TVShowsActivity::class.java,
                        "Example 2: Favorite Tv Shows"
                    )
                )
                activityModels.add(
                    ActivityModel(
                        ImprovedTVShowsActivity::class.java,
                        "Example 3: Improved Favorite Tv Shows"
                    )
                )
                activityModels.add(
                    ActivityModel(
                        ButtonCounterActivity::class.java,
                        "Example 4: Button Counter"
                    )
                )
                activityModels.add(
                    ActivityModel(
                        ValueDisplayActivity::class.java,
                        "Example 5: Value Display"
                    )
                )
                activityModels.add(
                    ActivityModel(
                        CitySearchActivity::class.java,
                        "Example 6: City Search"
                    )
                )
                return activityModels
            }
    }
}