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

import local.koki.android.eventory.R
import local.koki.android.eventory.data.util.EventoryUtil
import java.util.*


class WhatNewItemFragment : Fragment() {
    private var mColumnCount = 1

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var list: List<EventoryUtil>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_what_new, container, false)

        // Set the adapter
        if (view is RecyclerView) {
           //find parent view
            mRecyclerView=view.findViewById(R.id.list) as RecyclerView
            mRecyclerView!!.setHasFixedSize(true)

            mLayoutManager= LinearLayoutManager(context)
            mRecyclerView!!.layoutManager=mLayoutManager
            var eventoryUtils= ArrayList<EventoryUtil>()
            //:TODO データ取得を行う。

            //adapter set
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

    companion object {

        private val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(columnCount: Int): WhatNewItemFragment {
            val fragment = WhatNewItemFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
