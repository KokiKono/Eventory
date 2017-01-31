package local.koki.android.eventory.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.TextView
import io.realm.Realm
import local.koki.android.eventory.R
import local.koki.android.eventory.model.PrefectureRealm

/**
 * Created by 浩生 on 2017/01/22.
 */

class ConfigAtPlaceAdapter(private val values: List<PrefectureRealm>, private val listener: OnItemClickListener?) : RecyclerView.Adapter<ConfigAtPlaceAdapter.ViewHolder>() {
    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.config_tem, parent, false)
        mContext = parent!!.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var realm=values.get(position)
        holder.checkedTextView!!.isChecked=realm.status
        holder.checkedTextView!!.text=realm.name
        holder.mPrefecture=values[position]
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var mPrefecture: PrefectureRealm?=null
        var checkedTextView:CheckedTextView?=null
        init {
            checkedTextView=view.findViewById(R.id.check_text) as CheckedTextView
            if (listener != null) {
                view!!.setOnClickListener(View.OnClickListener {
                    listener.onItemClickListener(this)
                })
            }
        }
        fun reversalCheck(){
            checkedTextView!!.isChecked=!checkedTextView!!.isChecked
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(holder: ViewHolder)
    }

    override fun getItemCount(): Int {
        return values.size
    }
    public fun getItems():List<PrefectureRealm>{
        return  values
    }
}
