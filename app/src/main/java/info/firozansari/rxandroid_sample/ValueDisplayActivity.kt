package info.firozansari.rxandroid_sample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//import io.reactivex.SingleSubscriber;
//import io.reactivex.functions.Func1;
class ValueDisplayActivity : AppCompatActivity() {
    private var mValueDisplay: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()

        /*Single.just(4).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer);
            }
        }).subscribe(new SingleSubscriber<String>() {
            @Override
            public void onSuccess(String value) {
                  mValueDisplay.setText(value);
            }

            @Override
            public void onError(Throwable error) {

            }
        });*/
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_value_display)
        mValueDisplay = findViewById<View>(R.id.value_display) as TextView
    }
}