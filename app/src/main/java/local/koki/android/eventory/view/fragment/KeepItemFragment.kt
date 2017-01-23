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
    // TODO: Customize parameters
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


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteractionAtKeep(item: EventoryCardAdapter.ViewHolder)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): KeepItemFragment {
            val fragment = KeepItemFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
