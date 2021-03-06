package local.koki.android.eventory.view.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.j256.ormlite.logger.Log
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmRecyclerViewAdapter
import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.model.EventRealm
import local.koki.android.eventory.common.Colors
import local.koki.android.eventory.model.EventManager.CheckStatus
import java.text.SimpleDateFormat

/**
 * Created by 浩生 on 2017/01/28.
 */

class RealmEventCardAdapter(context: Context, data: OrderedRealmCollection<EventRealm>?) : RealmRecyclerViewAdapter<EventRealm, RealmEventCardAdapter.ViewHolder>(context, data, true) {
    var onClickKeep: ViewHolder.OnClickKeepListener? = null
    var onClickNotKeep: ViewHolder.OnClickNoKeepListener? = null
    var onClickTitle: ViewHolder.OnClickTitleListener? = null
    override fun onBindViewHolder(holder: RealmEventCardAdapter.ViewHolder, position: Int) {
        val event = getItem(holder.layoutPosition)
        holder.eventTitleTextView!!.text = event!!.title
        holder.eventTitleTextView!!.setOnClickListener { v ->
            onClickTitle?.let { it.onClickTitle(event) }
        }
        holder.eventAddressTextView?.let { it.text = event.address }
        val startFormat = SimpleDateFormat("yyyy年MM月dd日 HH:mm")
        val endFormat = SimpleDateFormat("MM月dd日 HH:mm")
        val startAt = startFormat.format(event.startAt)
        val endAt = endFormat.format(event.endAt)
        holder.eventAtTextView?.let { it.text = startAt + "〜" + endAt }
        holder.providerTextView?.let { it.text = "提供元" + EventManager.Api.indexOf(event.apiId).out }
        holder.keepButton?.let {
            it.setOnClickListener { v ->
                onClickKeep?.let {
                    it.onClickKeep(event, position)
                    holder.statusButton(CheckStatus.Keep.code)
                }
            }
        }
        holder.notKeepButton?.let {
            it.setOnClickListener { v ->
                onClickNotKeep?.let {
                    it.onClickNotKeep(event, position)
                    holder.statusButton(CheckStatus.NoKeep.code)
                }
            }
        }
        holder.statusButton(event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealmEventCardAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val v = layoutInflater.inflate(R.layout.eventory_item, parent, false)
        val viewHolder = ViewHolder(v)
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

        fun statusButton(status: Int) {
            when (status) {
                CheckStatus.Keep.code -> {
                    keepOn()
                    notKeepOff()
                }
                CheckStatus.NoKeep.code -> {
                    keepOff()
                    notKeepOn()
                }
                CheckStatus.NoCheck.code -> {
                    keepOff()
                    notKeepOff()
                }
            }
        }

        fun statusButton(eventRealm: EventRealm) {
            statusButton(eventRealm.status)
        }

        private fun keepOn() {
            keepButton?.let { it.setBackgroundResource(R.drawable.keep_on_background) }
            keepButton?.let { it.setTextColor(Color.WHITE) }
            eventTitleTextView?.let { it.setTextColor(Colors.STATE_ON.color) }
        }

        private fun keepOff() {
            keepButton?.let { it.setBackgroundResource(R.drawable.keep_off_background) }
            keepButton?.let { it.setTextColor(Colors.STATE_ON.color) }
            eventTitleTextView?.let { it.setTextColor(Colors.STATE_OFF.color) }
        }

        private fun notKeepOn() {
            notKeepButton?.let { it.setBackgroundResource(R.drawable.not_keep_on_background) }
            notKeepButton?.let { it.setTextColor(Color.WHITE) }
        }

        private fun notKeepOff() {
            notKeepButton?.let { it.setBackgroundResource(R.drawable.not_keep_off_background) }
            notKeepButton?.let { it.setTextColor(Colors.STATE_OFF.color) }
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
            fun onClickNotKeep(eventRealm: EventRealm, position: Int)
        }

        interface OnClickTitleListener {
            fun onClickTitle(eventRealm: EventRealm)
        }
    }

    override fun getItemId(index: Int): Long {
        return data?.let { it.get(index).id as Long }!!
    }

    private fun getGradientDrawable(size: Int, ratio: Int): GradientDrawable {
        var drawable = GradientDrawable()
        drawable.mutate()
        drawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
        var colors = IntArray(size)
        var count = 0
        while (count < size) {
            val red = if (count < ratio) 255 / ratio * count else 255
            val green = 255
            val blue = if (count < ratio) 255 / ratio * count else 255
            colors.set(count, Color.rgb(red, green, blue))
            count++
        }
        drawable.colors = colors
        return drawable
    }
}