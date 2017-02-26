package local.koki.android.eventory.viewController

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import local.koki.android.eventory.view.listener.RecyclerItemClickListener

import local.koki.android.eventory.R
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
        };
        abstract val value:String
        abstract val code:Int
    }

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
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

            var list : ArrayList<String> = ArrayList()

            for(config: CONFIG in CONFIG.values()){
                list.add(config.value)
            }
            mRecyclerView!!.addItemDecoration(DividerItemDecoration(view.context))
            mAdapter= ConfigRecyclerAdapter(list,object : ConfigRecyclerAdapter.OnListItemClickListener {
                override fun onItemClickListenerAtConfig(holder: ConfigRecyclerAdapter.ViewHolder) {
                    when(holder.mValue){
                        CONFIG.PLACE.value ->{
                            //開催地
                            startActivity(Intent(context, ConfigAtPlaceActivity::class.java))
                        }
                        CONFIG.GENRE.value ->{
                            //ジャンル
                            startActivity(Intent(context, ConfigAtJenreAcitivty::class.java))
                        }
                        else ->{
                            //その他：特になし

                        }
                    }
                }
            })
            mRecyclerView!!.adapter=mAdapter

        }
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)


    }

    override fun onDetach() {
        super.onDetach()
    }
}
