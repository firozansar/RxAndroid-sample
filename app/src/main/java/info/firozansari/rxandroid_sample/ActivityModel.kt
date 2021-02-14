package info.firozansari.rxandroid_sample

import android.app.Activity

/**
 * Pair consisting of the name of an example and the activity corresponding to the example.
 */
class ActivityModel(
    val mExampleActivityClass: Class<out Activity?>,
    val mExampleName: String
)