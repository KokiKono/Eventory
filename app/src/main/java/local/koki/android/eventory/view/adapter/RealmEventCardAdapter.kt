package local.koki.android.eventory.view.adapter

import android.app.usage.UsageEvents
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.view.util.Colors
import local.koki.android.eventory.model.EventManager.CheckStatus

/**
 * Created by 浩生 on 2017/01/28.
 */

class RealmEventCardAdapter(context: Context, data: OrderedRealmCollection<EventRealm>?) : RealmRecyclerViewAdapter<EventRealm, RealmEventCardAdapter.ViewHolder>(context, data, true) {
    var onClickKeep: ViewHolder.OnClickKeepListener? = null
    var onClickNotKeep: ViewHolder.OnClickNoKeepListener? = null
    var nowPage: Int? = null
    override fun onBindViewHolder(holder: RealmEventCardAdapter.ViewHolder, position: Int) {
        val event = getItem(holder.layoutPosition)
        holder.eventTitleTextView!!.text = event!!.title
        holder.eventAddressTextView!!.text = event!!.address
        holder.eventAtTextView!!.text = event!!.startAt + "～" + event!!.endAt
        var capacity = ""
        if (event!!.accepted > 0) {
            capacity += event!!.accepted.toString() + "/"
        }
        capacity += "定員" + event!!.limit + "人"
        holder.eventCapacotyTextView!!.text = capacity
        // :TODO 提供元の取得方法を聞いて設定
        holder.providerTextView!!.text = "提供元：" + EventManager.Api.indexOf(event.apiId).out
        holder.keepButton!!.setOnClickListener { v ->
            onClickKeep!!.onClickKeep(event, position)
            holder.statusButton(CheckStatus.Keep.code)
        }
        holder.notKeepButton!!.setOnClickListener { v ->
            onClickNotKeep!!.onClickNotKeep(event,position)
            holder.statusButton(CheckStatus.NotKeep.code)
        }
        holder.statusButton(event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealmEventCardAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val v = layoutInflater.inflate(R.layout.eventory_item, parent, false)
        val viewHolder = RealmEventCardAdapter.ViewHolder(v)
        return viewHolder
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var layoutView: LinearLayout? = null
        var eventTitleTextView: TextView? = null
        var eventAddressTextView: TextView? = null
        var eventAtTextView: TextView? = null
        var eventCapacotyTextView: TextView? = null
        var providerTextView: TextView? = null
        var keepButton: Button? = null
        var notKeepButton: Button? = null

        init {
            layoutView = v.findViewById(R.id.card_layout) as LinearLayout
            eventTitleTextView = v.findViewById(R.id.eventory_title) as TextView
            eventAddressTextView = v.findViewById(R.id.eventory_address) as TextView
            eventAtTextView = v.findViewById(R.id.eventory_at) as TextView
            eventCapacotyTextView = v.findViewById(R.id.eventory_capacity) as TextView
            providerTextView = v.findViewById(R.id.eventory_provider) as TextView
            keepButton = v.findViewById(R.id.keep_button) as Button
            notKeepButton = v.findViewById(R.id.not_keep_button) as Button
        }

        fun statusButton(status:Int) {
            when (status) {
                CheckStatus.Keep.code -> {
                    keepButton!!.setTextColor(Color.WHITE)
                    keepButton!!.setBackgroundColor(Colors.STATE_ON.color)
                    notKeepButton!!.setTextColor(Color.BLACK)
                    notKeepButton!!.setBackgroundColor(Color.GRAY)
                }
                CheckStatus.NotKeep.code -> {
                    keepButton!!.setTextColor(Color.BLACK)
                    keepButton!!.setBackgroundColor(Color.GRAY)
                    notKeepButton!!.setTextColor(Color.WHITE)
                    notKeepButton!!.setBackgroundColor(Color.GRAY)
                }
                CheckStatus.NoCheck.code->{
                    keepButton!!.setTextColor(Color.BLACK)
                    keepButton!!.setBackgroundColor(Color.GRAY)
                    notKeepButton!!.setTextColor(Color.BLACK)
                    notKeepButton!!.setBackgroundColor(Color.GRAY)
                }
            }
        }
        fun statusButton(eventRealm: EventRealm){
            statusButton(eventRealm.status)
        }

        /**
         * implement processing to change realm object.
         */
        interface OnClickKeepListener {
            fun onClickKeep(eventRealm: EventRealm, position: Int)
        }

        /**
         * implement processing to change realm object.
         */
        interface OnClickNoKeepListener {
            fun onClickNotKeep(eventRealm: EventRealm,position: Int)
        }
    }

    override fun getItemId(index: Int): Long {
        return data!!.get(index).id as Long
    }
}