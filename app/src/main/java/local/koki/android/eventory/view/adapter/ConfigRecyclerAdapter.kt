package local.koki.android.eventory.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import local.koki.android.eventory.view.listener.RecyclerItemClickListener

import local.koki.android.eventory.R
import java.util.*

class ConfigRecyclerAdapter(private val mValues: List<String>, listener: OnListItemClickListener) : RecyclerView.Adapter<ConfigRecyclerAdapter.ViewHolder>() {
    private var mClickListener: OnListItemClickListener? = listener
    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_configruration, parent, false)
        mContext = parent.context;
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTextView!!.text = mValues[position]
        holder.mValue=mValues[position]
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var mTextView: TextView? = null
        var mValue:String ?= null
        var mView: View? = view

        init {
            mTextView = view.findViewById(R.id.text) as TextView
            if (mClickListener != null) {
                mView!!.setOnClickListener(View.OnClickListener() {
                    mClickListener!!.onItemClickListenerAtConfig(this)
                })
            }
        }

        override fun toString(): String {
            return mTextView!!.text.toString();
        }
    }

    interface OnListItemClickListener {
        fun onItemClickListenerAtConfig(holder: ViewHolder)
    }

    public fun getItem(position: Int): String {
        return mValues[position]
    }


}
