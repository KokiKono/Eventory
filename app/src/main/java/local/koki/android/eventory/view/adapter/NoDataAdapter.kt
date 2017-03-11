package local.koki.android.eventory.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import local.koki.android.eventory.R

/**
 * Created by kokikono on 2017/03/01.
 */

class NoDataAdapter(val context: Context) : RecyclerView.Adapter<NoDataAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NoDataAdapter.ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        val v=layoutInflater.inflate(R.layout.no_event_item,parent,false)
        val viewHolder=ViewHolder(v)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }
    override fun getItemCount(): Int {
        return 1
    }
    class ViewHolder(v:View):RecyclerView.ViewHolder(v){

    }
}
