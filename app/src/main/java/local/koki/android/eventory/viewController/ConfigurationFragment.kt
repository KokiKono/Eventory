package local.koki.android.eventory.viewController

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import local.koki.android.eventory.view.listener.RecyclerItemClickListener

import local.koki.android.eventory.R
import local.koki.android.eventory.common.FragmentRouter
import local.koki.android.eventory.model.EventManager
import local.koki.android.eventory.view.adapter.ConfigRecyclerAdapter
import local.koki.android.eventory.viewController.ConfigAtJenreAcitivty
import local.koki.android.eventory.viewController.ConfigAtPlaceActivity
import local.koki.android.eventory.view.receycler.DividerItemDecoration
import java.util.*

class ConfigurationFragment : Fragment() {
    private var mItemClickListener : RecyclerItemClickListener.OnItemClickListener?=null
    enum class CONFIG{
        GENRE{
            override val value:String ="興味のあるジャンル"
            override val code:Int=1

        },
        PLACE{
            override val value:String="開催地"
            override val code=2
        }
        ,NewEventSize{
            override val value: String="新着情報："
            override val code: Int=3
        }
        ,Review{
            override val value: String="レビューする。"
            override val code: Int=4
        };
        abstract val value:String
        abstract val code:Int
    }

    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_configuration, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            mRecyclerView=view.findViewById(R.id.list)as RecyclerView
            mRecyclerView!!.setHasFixedSize(true)

            mLayoutManager= LinearLayoutManager(context)
            mRecyclerView!!.layoutManager=mLayoutManager

            var list : ArrayList<CONFIG> = ArrayList()

            for(config: CONFIG in CONFIG.values()){
                list.add(config)
            }
            mRecyclerView!!.addItemDecoration(DividerItemDecoration(view.context))
            var adapter = ConfigRecyclerAdapter(list,object : ConfigRecyclerAdapter.OnListItemClickListener {
                override fun onItemClickListenerAtConfig(holder: ConfigRecyclerAdapter.ViewHolder) {
                    when(holder.mConfig){
                        CONFIG.PLACE ->{
                            //開催地
                            startActivity(Intent(context, ConfigAtPlaceActivity::class.java))
                        }
                        CONFIG.GENRE ->{
                            //ジャンル
                            startActivity(Intent(context, ConfigAtJenreAcitivty::class.java))
                        }
                        CONFIG.NewEventSize ->{
                            val tabs=activity.findViewById(android.R.id.tabs) as TabLayout
                            val tabGroup=tabs.getChildAt(0) as ViewGroup
                            val newEventTab=tabGroup.getChildAt(FragmentRouter.Tag.indexOf(FragmentRouter.Tag.New))
                            newEventTab.performClick()
                        }
                        CONFIG.Review ->{
                            val playStore=Intent(Intent.ACTION_VIEW)
                            playStore.setData(Uri.parse("https://play.google.com/store/apps/details?id=local.android.eventory&hl=ja"))
                            startActivity(playStore)
                        }
                        else ->{
                            //その他：特になし

                        }
                    }
                }
            })
            mRecyclerView?.let { it.adapter=adapter }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        mRecyclerView?.let {
            it.adapter.notifyDataSetChanged()
        }
    }
}
