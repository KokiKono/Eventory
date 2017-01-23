package local.koki.android.eventory.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import local.koki.android.eventory.R
import local.koki.android.eventory.data.util.PrefectureInterface

/**
 * Created by 浩生 on 2017/01/22.
 */

class ConfigAtPlaceAdapter(private val values: List<PrefectureInterface>, private val listener: OnItemClickListener?) : RecyclerView.Adapter<ConfigAtPlaceAdapter.ViewHolder>() {
    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.config_at_place_item, parent, false)
        mContext = parent!!.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTextView!!.text = values[position].getName()
        holder.mCheckBox!!.isChecked = values[position].getFlg() as Boolean
        holder.mPrefecture=values[position]
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var mView: View? = view
        var mTextView: TextView? = null
        var mCheckBox: CheckBox? = null
        var mPrefecture:PrefectureInterface ?=null

        init {
            mTextView = view.findViewById(R.id.text) as TextView
            mCheckBox = view.findViewById(R.id.checkBox) as CheckBox
            if (listener != null) {
                mView!!.setOnClickListener(View.OnClickListener {
                    listener.onItemClickListener(this)
                })
            }
        }
        fun reversalCheck(){
            mCheckBox!!.isChecked=!mCheckBox!!.isChecked
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(holder: ViewHolder)
    }

    override fun getItemCount(): Int {
        return values.size
    }
    public fun getItems():List<PrefectureInterface>{
        return  values
    }
}
