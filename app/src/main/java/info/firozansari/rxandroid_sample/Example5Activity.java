package info.firozansari.rxandroid_sample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Example5Activity extends AppCompatActivity {

    private TextView mValueDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureLayout();

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

    private void configureLayout() {
        setContentView(R.layout.activity_example_5);
        mValueDisplay = (TextView) findViewById(R.id.value_display);
    }
}
