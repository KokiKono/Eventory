package local.koki.android.eventory.view.fragment

import local.koki.android.eventory.view.adapter.EventoryCardAdapter
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm

import local.koki.android.eventory.R
import local.koki.android.eventory.model.EventRealm
import java.util.*

class KeepItemFragment : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var list: List<EventRealm>? = null
    private var mRealm:Realm?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRealm= Realm.getDefaultInstance()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_interested, container, false)

        var eventoryUtils= ArrayList<EventRealm>()

        // Set the adapter
        if (view is RecyclerView) {
            //find parent view
            mRecyclerView=view.findViewById(R.id.list) as RecyclerView
            mRecyclerView!!.setHasFixedSize(true)
            mLayoutManager= LinearLayoutManager(context)
            mRecyclerView!!.layoutManager=mLayoutManager
            //setting data

            //Set adapter
            mAdapter= EventoryCardAdapter(context,eventoryUtils)
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

    override fun onDestroy() {
        super.onDestroy()
        mRealm!!.close()
    }

}
