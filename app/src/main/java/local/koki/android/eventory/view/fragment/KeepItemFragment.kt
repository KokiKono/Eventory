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

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class KeepItemFragment : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var list: List<EventoryUtil>? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list_interested, container, false)

        var eventoryUtils= ArrayList<EventoryUtil>()

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
}
