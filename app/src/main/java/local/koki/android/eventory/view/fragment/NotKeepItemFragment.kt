package local.koki.android.eventory.view.fragment

import local.koki.android.eventory.view.adapter.EventoryCardAdapter
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import local.koki.android.eventory.R
import local.koki.android.eventory.data.util.EventoryUtil
import java.util.*

/**
 * This fragment is interested Eventory list
 *
 */
class NotKeepItemFragment : Fragment() {

    private var mRecyclerView: RecyclerView?= null
    private var mAdapter : RecyclerView.Adapter<*>?= null
    private var mLayoutManager: RecyclerView.LayoutManager?= null
    private var list : List<EventoryUtil> ?= null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_not_interested, container, false)

        // data setting
        val eventoryUtils= ArrayList<EventoryUtil>()
        if (view is RecyclerView) {
            //find parent view
            mRecyclerView = view .findViewById(R.id.list) as RecyclerView
            mRecyclerView!!.setHasFixedSize(true)

            mLayoutManager= LinearLayoutManager(context)
            mRecyclerView!!.layoutManager=mLayoutManager
            //setting data

            //adapter setting
            mAdapter= EventoryCardAdapter(context,eventoryUtils)
            mRecyclerView!!.adapter=mAdapter;
        }


        return view
    }

}
