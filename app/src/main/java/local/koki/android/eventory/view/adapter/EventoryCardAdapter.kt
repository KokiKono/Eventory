package local.koki.android.eventory.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import java.util.ArrayList

import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventRealm

/**
 * Created by 浩生 on 2017/01/19.
 */

class EventoryCardAdapter(private val mContext: Context, private val mEventorys: ArrayList<EventRealm>) : RecyclerView.Adapter<EventoryCardAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return mEventorys.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val util = mEventorys[position]
        holder.mEventoryTitle_text.text = util.title
        holder.mEventoryAddress_text.text = util.address
        holder.mEventoryAt_text.text = util.address
        var capacity = ""
        if (util.accepted > 0) {
            capacity += util.accepted.toString() + "/"
        }
        capacity += "定員" + util.limit + "人"
        holder.mEventoryCapacoty_text.text = capacity
        // :TODO 提供元の取得方法を聞いて設定
        holder.mProvider_text.text = "test"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val v = layoutInflater.inflate(R.layout.eventory_item, parent, false)
        val viewHolder = ViewHolder(v)
        return viewHolder
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var mLayout: LinearLayout
        var mEventoryTitle_text: TextView
        var mEventoryAddress_text: TextView
        var mEventoryAt_text: TextView
        var mEventoryCapacoty_text: TextView
        var mProvider_text: TextView

        init {
            mLayout = v.findViewById(R.id.card_layout) as LinearLayout
            mEventoryTitle_text = v.findViewById(R.id.eventory_title) as TextView
            mEventoryAddress_text = v.findViewById(R.id.eventory_address) as TextView
            mEventoryAt_text = v.findViewById(R.id.eventory_at) as TextView
            mEventoryCapacoty_text = v.findViewById(R.id.eventory_capacity) as TextView
            mProvider_text = v.findViewById(R.id.eventory_provider) as TextView
        }
    }
}
