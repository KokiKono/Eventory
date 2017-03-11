package local.koki.android.eventory.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.viewController.ConfigurationFragment

class ConfigRecyclerAdapter(private val mValues: List<ConfigurationFragment.CONFIG>, listener: OnListItemClickListener) : RecyclerView.Adapter<ConfigRecyclerAdapter.ViewHolder>() {
    private var mClickListener: OnListItemClickListener? = listener
    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_configruration, parent, false)
        mContext = parent.context;
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTextView?.let { it.text = getItem(position).value }
        holder.mConfig = getItem(position)
        holder.initView()
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var mTextView: TextView? = null
        var mView: View? = view
        var mConfig: ConfigurationFragment.CONFIG? = null

        init {
            mTextView = view.findViewById(R.id.text) as TextView
            if (mClickListener != null) {
                mView?.let {
                    it.setOnClickListener(View.OnClickListener() {
                        mClickListener?.let { it.onItemClickListenerAtConfig(this) }
                    })
                }
            }
        }

        fun initView(){
            mConfig?.let {
                when(it){
                    ConfigurationFragment.CONFIG.NewEventSize ->{
                        mView?.let { it.setBackgroundResource(R.color.keep) }
                        mTextView?.let { it.text="新着情報："+EventManager.fetchEvent(EventManager.CheckStatus.NoCheck).size+"件" }
                    }
                    else ->{}
                }
            }
        }

        override fun toString(): String {
            return mTextView!!.text.toString();
        }
    }

    interface OnListItemClickListener {
        fun onItemClickListenerAtConfig(holder: ViewHolder)
    }

    public fun getItem(position: Int): ConfigurationFragment.CONFIG {
        return mValues[position]
    }


}
