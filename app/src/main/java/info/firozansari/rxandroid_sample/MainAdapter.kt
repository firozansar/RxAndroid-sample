package info.firozansari.rxandroid_sample

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for mapping a set of example activities to views.
 */
class MainAdapter(
    private val mContext: Context,
    private val ms: List<ActivityModel>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater
            .from(mContext)
            .inflate(R.layout.example_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mNameDisplay.text = ms[position].mExampleName
        holder.itemView.setOnClickListener {
            val exampleIntent = Intent(mContext, ms[position].mExampleActivityClass)
            mContext.startActivity(exampleIntent)
        }
    }

    override fun getItemCount(): Int {
        return ms.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mNameDisplay: TextView = itemView.findViewById<View>(R.id.name_display) as TextView
    }
}